package com.evana.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CarByYearDTO(
        @JsonAlias("code") String code,
        @JsonAlias("name") String name
) {
}
