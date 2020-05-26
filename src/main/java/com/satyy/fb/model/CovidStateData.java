package com.satyy.fb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidStateData {
    private String id;
    private String state;
    private Integer active;
    private Integer confirmed;
    private Integer recovered;
    private Integer deaths;

    @JsonProperty("aChanges")
    private Integer activeChange;

    @JsonProperty("cChanges")
    private Integer confirmedChange;

    @JsonProperty("rChanges")
    private Integer recoveredChange;

    @JsonProperty("dChanges")
    private Integer deathChange;

    private List<CovidDistrictData> districtData;
}
