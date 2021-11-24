package com.tww.aidecovid.statics;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum CodePostal {
    BRUXELLES_VILLE(1000),
    SCHAERBEEK(1030),
    ETTERBEEK(1040);

    private final int value;

    CodePostal(int value) {
        this.value = value;
    }

    public String getDisplayName() {
        return this.name();
    }
    public int getValue() {
        return this.value;
    }
    public CodePostal getByValue(int value) {
        for (CodePostal cp : values()) {
            if (cp.value == value) return cp;
        }
        return null;
    }

    public String convertToString(List<CodePostal> cpList) {
        StringBuilder cps = new StringBuilder();
        if (!cpList.isEmpty()) {
            for (CodePostal cp : cpList) {
                cps.append(cp.value);
                cps.append(",");
            }
            cps.deleteCharAt(cps.length()-1);
        }
        return cps.toString();
    }

    public List<CodePostal> createFromString(String cps) {
        List<CodePostal> cpList = new ArrayList<>();
        if (cps != null && !cps.isEmpty()) {
            String[] list = cps.split(",");
            for (String cp : list) {
                cpList.add(getByValue(Integer.parseInt(cp)));
            }
        }
        return cpList;
    }
}
