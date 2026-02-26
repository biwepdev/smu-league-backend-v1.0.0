package com.smu.committee.committee.query.api.dto;

import com.smu.committee.committee.query.api.response.CommitteeResponse;

import java.io.Serializable;
import java.util.List;

public record AllLookupCommitteeResponse(boolean success, List<CommitteeResponse> committee) implements Serializable {
}
