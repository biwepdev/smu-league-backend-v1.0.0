package com.smu.committee.committee.query.api.dto;

import com.smu.committee.committee.query.api.response.StateResponse;

import java.io.Serializable;
import java.util.List;

public record LookupStateList<S>(boolean success, List<StateResponse> state) implements Serializable {
}
