package com.tww.aidecovid.statics;

import java.util.ArrayList;
import java.util.List;

public enum Status {
    NEW("New"), WAITING("Waiting"), APPROVED("Accepted"), DECLINED("Declined"), DONE("Done");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
