package com.example.jobcentrebackend.dto;

import java.util.Date;

public class EmploymentHistoryDto {
    private long id;
    private Date employmentDate;
    private Date terminationDate;
    private String terminationReason;
    private boolean archived;
}
