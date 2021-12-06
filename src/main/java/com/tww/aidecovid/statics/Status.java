package com.tww.aidecovid.statics;

import java.util.ArrayList;
import java.util.List;

public enum Status {
    NEW("Nouveau"), WAITING("En attente"), APPROVED("Accepté"), DECLINED("Refusé"), DONE("Fait");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
