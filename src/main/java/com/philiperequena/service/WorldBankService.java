package com.philiperequena.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.philiperequena.domain.dto.WorldBankCountry;
import com.philiperequena.domain.dto.WorldBankIndicator;
import com.philiperequena.repository.WorldBankRepository;

@ApplicationScoped
public class WorldBankService {

    @Inject
    @RestClient
    WorldBankRepository worldBankRepository;

    public WorldBankCountry getListCountryCode() {
        WorldBankCountry wbc = new WorldBankCountry().fromJson(this.worldBankRepository.getCountry(1, "json"));
        while (wbc.header.page < wbc.header.pages) {
            wbc.fromJson(this.worldBankRepository.getCountry(wbc.header.page + 1, "json"));
        }
        return wbc;
    }

    public WorldBankIndicator getListIndicatorByCode(String code) {
        WorldBankIndicator wbi = new WorldBankIndicator()
                .fromJson(this.worldBankRepository.getIndicatorByContry(code, 1, "json"));
        while (wbi.header.page < wbi.header.pages) {
            wbi.fromJson(this.worldBankRepository.getIndicatorByContry(code, wbi.header.page + 1, "json"));
        }
        return wbi;
    }
}
