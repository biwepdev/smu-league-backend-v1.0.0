package com.smu.committee.committee.cmd.api.controller;

import com.smu.committee.committee.cmd.api.command.CommitteeEnableCommand;
import com.smu.committee.committee.core.dto.MessageResponse;
import com.smu.committee.committee.core.utils.MapUtils;
import com.smu.committee.committee.query.api.handler.CommitteeEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/software/committee/enable-committee")
@Tag(name = "committee",description = "Data REST API for committee resource")
public class CommitteeEnableController {
    private final CommitteeEventHandler committeeEventHandler;

    @Operation(summary = "Enable a committee by code")
    @PutMapping("/a-committee-by-code")
    public Mono<ResponseEntity<MessageResponse>> enable(@Valid @RequestBody CommitteeEnableCommand command) {
        try {
            return committeeEventHandler.enable(command)
                    .flatMap (committee -> {
                        if ( committee != null ) {
                            return Mono.just ( ResponseEntity.ok ( )
                                    .body ( new MessageResponse(true , MapUtils.ENABLE)));
                        } else {
                            return Mono.just(ResponseEntity.ok()
                                    .body ( new MessageResponse (false , MapUtils.OPERATION_FAILED)));
                        }
                    } );
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
