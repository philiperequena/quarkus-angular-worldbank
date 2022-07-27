package com.philiperequena.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.philiperequena.domain.dto.WorldBankCountry;
import com.philiperequena.domain.dto.WorldBankIndicator;
import com.philiperequena.service.WorldBankService;

@Path("/country")
public class WorldIndicatorController {

    @Inject
    WorldBankService worldBankService;

    @Operation(summary = "Get full list of contries name and code", description = "Get full list from worldbank.org of contries without paginator, return all data")
    @APIResponse(responseCode = "200", description = "Success", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = WorldBankCountry.class)) })
    @GET
    @Path("/code")
    @Produces(MediaType.APPLICATION_JSON)
    public WorldBankCountry getContryCode() {
        return worldBankService.getListCountryCode();
    }

    @Operation(summary = "Get full list of indicators data from one contry by code", description = "Get full list from worldbank.org of Poverty indicators by one contry, return all data")
    @APIResponse(responseCode = "200", description = "Success", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = WorldBankIndicator.class)) })
    @GET
    @Path("/indicator/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public WorldBankIndicator getContryIndicator(@PathParam("code") String code) {
        return worldBankService.getListIndicatorByCode(code);
    }
}
