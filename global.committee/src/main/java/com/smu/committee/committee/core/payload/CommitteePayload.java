package com.smu.committee.committee.core.payload;

import com.smu.committee.committee.core.exception.response.CommitteeIdExceptionRegexResponse;
import reactor.core.publisher.Mono;

public interface CommitteePayload {
    Mono<String> getCode();
    void regexCommitteeId(String committeeId)
            throws CommitteeIdExceptionRegexResponse;
}
