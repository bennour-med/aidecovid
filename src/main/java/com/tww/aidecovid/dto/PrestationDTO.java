package com.tww.aidecovid.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PrestationDTO {
    private Long id;
    private Long serviceId;
    private Long discussionId;
    private Long requesterId;
    private Long providerId;
    private String date;
    private String time;
    private String cps;
    //private List<CodePostal> cps;
    public Date getDateTime() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(date + " " + time);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
