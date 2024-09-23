package com.evana.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FipeDetailsDTO(
        @JsonAlias("codeFipe") String codeFipe,
        @JsonAlias("model") String model,
        @JsonAlias("modelYear") String modelYear,
        @JsonAlias("price") String price,
        @JsonAlias("referenceMonth") String referenceMonth
)
{
}
