package com.philiperequena.domain.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorldBankHeader {
    @JsonIgnore
    public Integer page;
    @JsonIgnore
    public Integer pages;
    @JsonIgnore
    public Integer perPage;
    @Schema(description="Total of data present on list.", example = "299")
    public Integer total;
}
