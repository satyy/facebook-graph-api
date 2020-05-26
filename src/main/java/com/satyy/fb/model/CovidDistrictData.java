package com.satyy.fb.model;

import static com.satyy.fb.util.ConstantUtil.MODEL_FIELD_SEPARATOR;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidDistrictData {
    private String id;
    private String name;
    private Integer confirmed;
    private Integer recovered;
    private Integer deaths;
    private String zone;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Place: ").append(this.name).append(MODEL_FIELD_SEPARATOR);
        if (this.confirmed != null)
            builder.append("Confirmed: ").append(this.confirmed.toString()).append(MODEL_FIELD_SEPARATOR);
        if (this.recovered != null)
            builder.append("Recovered: ").append(this.recovered.toString()).append(MODEL_FIELD_SEPARATOR);
        if (this.deaths != null)
            builder.append("Deaths: ").append(this.deaths.toString()).append(MODEL_FIELD_SEPARATOR);
        if (this.zone != null)
            builder.append("Zone: ").append(this.zone);
        return builder.toString();
    }
}
