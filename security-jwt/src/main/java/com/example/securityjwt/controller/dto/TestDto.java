package com.example.securityjwt.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TestDto {

    @NotNull
    private String title;

    @Max(value = 999)
    @NotNull
    private int max;

    @Min(value = 10)
    @NotNull
    private int min;
}
