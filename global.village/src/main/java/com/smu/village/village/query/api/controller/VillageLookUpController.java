package com.smu.village.village.query.api.controller;

import com.smu.village.core.query.GetByCodeQuery;
import com.smu.village.village.query.api.dto.AllLookupVillageResponse;
import com.smu.village.village.query.api.dto.LookupVillageResponse;
import com.smu.village.village.query.api.handler.VillageQueryHandler;
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
@RequestMapping(path = "api/v1/smu/village/lookup-village/")
@Tag(name="village")
@RequiredArgsConstructor
public class VillageLookUpController {
    private final VillageQueryHandler villageQueryHandler;

    @Operation(summary = "Get all villages")
    @GetMapping(path = "get-villages", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupVillageResponse> getVillages() {
        return villageQueryHandler.findVillages()
                .collectList()
                .map(list -> new AllLookupVillageResponse(true, list));
    }


    @Operation(summary = "Get village by code")
    @PutMapping(path = "get-village-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupVillageResponse> getByCode(@Valid @RequestBody GetByCodeQuery query) {
        return villageQueryHandler.findByVillageCode(query)
                .map(village -> new LookupVillageResponse(true, village))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Village not found")));
    }
}
