package com.smu.committee.committee.core.common;

public enum State {
    ACTIVE("01", "Active"),
    INACTIVE("00", "Inactive");

    private final String code;
    private final String label;

    State(String code, String label){
        this.code = code;
        this.label = label;
    }

    public String getCode(){return code;}
    public String getLabel(){return label;}
}
