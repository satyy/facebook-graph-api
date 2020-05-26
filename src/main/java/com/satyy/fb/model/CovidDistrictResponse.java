package com.satyy.fb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CovidDistrictResponse {
    private String name;
    private Integer confirmed;
    private Integer recovered;
    private Integer deaths;
    private String zone;
}
