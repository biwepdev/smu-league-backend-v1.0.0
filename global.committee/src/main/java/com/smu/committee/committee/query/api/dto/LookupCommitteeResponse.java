package com.smu.committee.committee.query.api.dto;

import com.smu.committee.committee.query.api.response.CommitteeResponse;

import java.io.Serializable;

public record LookupCommitteeResponse(boolean success, CommitteeResponse committee) implements Serializable {
}
