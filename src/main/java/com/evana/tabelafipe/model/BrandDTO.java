package com.evana.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrandDTO(
        @JsonAlias("code") Integer code,
        @JsonAlias("name") String name
) {
}
