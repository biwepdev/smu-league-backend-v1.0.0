package com.smu.committee.committee.core.payload.impl;

import com.smu.committee.committee.core.common.CommitteeCode;
import com.smu.committee.committee.core.payload.CommitteePayload;
import com.smu.committee.committee.core.exception.response.CommitteeIdExceptionRegexResponse;
import com.smu.committee.committee.query.api.repository.CommitteeRepositories;
import com.smu.committee.committee.query.api.repository.CommitteeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CommitteePayloadImpl implements CommitteePayload {
    private final CommitteeRepository committeeRepository;
    private final CommitteeRepositories committeeRepositories;

    @Override
    public Mono<String> getCode() {
        return committeeRepository.count()
                .flatMap(aLong -> {
                    if (aLong == 0) {
                        return Mono.just("COM000001");
                    }
                    else {
                        Mono<String> code = committeeRepositories.getLastElement()
                                .flatMap(committee -> Mono.just(committee.getCommitteeCode()));
                        return CommitteeCode.generate(code);
                    }
                });
    }

    @Override
    public void regexCommitteeId(String committeeId) throws CommitteeIdExceptionRegexResponse {
        String regexCommitteeId = "^[a-z0-9-]{36}$";
        Pattern patternCommitteeId = Pattern.compile( regexCommitteeId);
        Matcher matcherCommitteeId = patternCommitteeId.matcher(committeeId);
        if (! matcherCommitteeId.matches())
            throw new CommitteeIdExceptionRegexResponse("Must be thirty-six characters");
    }

}
