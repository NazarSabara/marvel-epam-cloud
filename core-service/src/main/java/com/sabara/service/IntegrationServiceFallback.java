package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntegrationServiceFallback implements IntegrationServiceClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Throwable cause;

    public IntegrationServiceFallback(Throwable cause){
        this.cause = cause;
    }

    @Override
    public Optional<HeroResource> getHeroById(Long id) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404){
            logger.error("404 Resource not found.");
        } else {
            logger.error("Something went wrong.");
        }

        return Optional.empty();
    }

    @Override
    public List<HeroResource> getAllHeroes() {
        logger.error("Something went wrong.");

        return new ArrayList<>();
    }
}
