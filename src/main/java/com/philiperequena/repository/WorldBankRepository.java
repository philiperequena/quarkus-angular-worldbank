package com.philiperequena.repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "worldbank-api")
public interface WorldBankRepository {

    @GET
    @Path("/country")
    public String getCountry(@QueryParam("page") Integer page, @QueryParam("format") String format);

    @GET
    @Path("/country/{code}/indicator/SI.POV.DDAY")
    public String getIndicatorByContry(@PathParam("code") String code, @QueryParam("page") Integer page,
            @QueryParam("format") String format);
}