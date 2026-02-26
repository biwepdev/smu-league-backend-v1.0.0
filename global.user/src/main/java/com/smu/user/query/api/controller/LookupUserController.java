package com.smu.user.query.api.controller;

import com.smu.user.core.query.GetByCodeQuery;
import com.smu.user.query.api.dto.AllLookupUserResponse;
import com.smu.user.query.api.dto.LookupUserResponse;
import com.smu.user.query.api.handler.UserQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user/user-lookup/")
@Tag(name = "user")
public class LookupUserController {
    private final UserQueryHandler userQueryHandler;

    @Operation(summary = "Retrieve data user")
    @GetMapping(path = "/get-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupUserResponse> getUser() {
        return userQueryHandler.findUsers()
                .collectList()
                .map(list ->new AllLookupUserResponse(true, list));
    }

    @Operation(summary = "Retrieve data user by code")
    @PutMapping(path = "/get-user-by-user-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupUserResponse> getUserByCode(@Valid @RequestBody GetByCodeQuery query)
    {
        return userQueryHandler.findByCode(query)
                .map(userResponse -> new LookupUserResponse(true, userResponse));
    }

    @Operation(summary = "Retrieve user by staffCode")
    @PutMapping(path = "get-user-by-staff-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LookupUserResponse> findByStaffCode(@Valid @RequestBody GetByCodeQuery query){
        return userQueryHandler.findAllUsersByStaffCode(query)
                .map(userResponse->new LookupUserResponse(true, userResponse));
    }

    @Operation(summary = "Retrieve user by roleCode")
    @PutMapping(path = "get-user-by-role-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LookupUserResponse> findByRoleCode(@Valid @RequestBody GetByCodeQuery query){
        return userQueryHandler.findAllUsersByRoleCode(query)
                .map(userResponse -> new LookupUserResponse(true, userResponse));
    }

    @Operation(summary = "Retrieve user by memberCode")
    @PutMapping(path = "get-user-by-member-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LookupUserResponse> findByMemberCode(@Valid @RequestBody GetByCodeQuery query){
        return userQueryHandler.findAllUsersByMemberCode(query)
                .map(userResponse -> new LookupUserResponse(true, userResponse));
    }
}
