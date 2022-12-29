package com.xsg.health.dto;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HealthMetrics {
    private String weight;
    private String bmi;
    private String bodyFat;
}
