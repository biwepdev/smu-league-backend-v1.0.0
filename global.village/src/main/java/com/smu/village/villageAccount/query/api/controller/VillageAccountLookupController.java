package com.smu.village.villageAccount.query.api.controller;

import com.smu.village.core.query.GetByCodeQuery;
import com.smu.village.villageAccount.query.api.dto.AllLookupVillageAccountResponse;
import com.smu.village.villageAccount.query.api.dto.LookupVillageAccountResponse;
import com.smu.village.villageAccount.query.api.handler.VillageAccountQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/village/account/lookup-village-account/")
@Tag(name="villageAccount")
public class VillageAccountLookupController {
    private final VillageAccountQueryHandler queryHandler;

    @Operation(summary = "Get all villageAccounts")
    @GetMapping(path = "get-village-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupVillageAccountResponse> getVillageAccounts() {
        return queryHandler.getVillageAccounts()
                .collectList()
                .map(list -> new AllLookupVillageAccountResponse(true, list));
    }

    @Operation(summary = "Get villageAccount by code")
    @PutMapping(path = "get-village-account-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupVillageAccountResponse> getByCode(@Valid @RequestBody GetByCodeQuery query) {
        return queryHandler.getByCode(query)
                .map(villageAccount -> new LookupVillageAccountResponse(true, villageAccount))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Village not found")));
    }

    @GetMapping(path = "get-village-account-by-date", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupVillageAccountResponse> getByDate(@RequestParam String date) {
        return queryHandler.getByDate(date)
                .map(villageAccount -> new LookupVillageAccountResponse(true, villageAccount));
    }

}
