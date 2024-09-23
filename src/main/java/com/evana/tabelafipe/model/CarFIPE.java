package com.evana.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CarFIPE(
        @JsonAlias("model") String modelName,
        @JsonAlias("modelYear") String modelYear,
        @JsonAlias("price") String price,
        @JsonAlias("codeFipe") String codeFipe,
        @JsonAlias("brand") String brand,
        @JsonAlias("referenceMonth") String referenceMonth
) {
}
