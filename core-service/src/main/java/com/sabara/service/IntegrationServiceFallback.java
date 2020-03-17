package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class IntegrationServiceFallback implements IntegrationServiceClient {

    private final Throwable cause;

    public IntegrationServiceFallback(Throwable cause){
        this.cause = cause;
    }

    @Override
    public Optional<HeroResource> getHeroById(Long id) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404){
            log.error("404 Resource not found.");
        } else {
            log.error("Something went wrong.");
        }

        return Optional.empty();
    }

    @Override
    public List<HeroResource> getAllHeroes() {
        log.error("Something went wrong.");

        return new ArrayList<>();
    }
}
