package com.smu.Role.query.api.controller;

import com.smu.Role.core.query.GetByCodeQuery;
import com.smu.Role.query.api.dto.AllLookupRoleResponse;
import com.smu.Role.query.api.dto.LookupRoleResponse;
import com.smu.Role.query.api.handler.RoleQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/role/role-lookup/")
@Tag(name = "role")
public class LookupRoleController {
    private final RoleQueryHandler roleQueryHandler;

    @Operation(summary = "Retrieve data role")
    @GetMapping(path = "/get-role", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupRoleResponse> getRole() {
        return roleQueryHandler.findRoles()
                .collectList()
                .map(list ->new AllLookupRoleResponse(true, list));
    }

    @Operation(summary = "Retrieve data role by code")
    @PutMapping(path = "/get-role-by-role-ode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupRoleResponse> getRoleByCode(@Valid @RequestBody GetByCodeQuery query)
    {
        return roleQueryHandler.findByCode(query)
                .map(roleResponse -> new LookupRoleResponse(true, roleResponse));
    }
}
