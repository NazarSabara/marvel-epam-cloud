package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class IntegrationServiceFallback implements IntegrationServiceClient {

    private final Throwable cause;

    @Override
    public Optional<HeroResource> getHeroById(Long id) {
        log.warn(cause.getMessage(), cause);

        return Optional.empty();
    }

    @Override
    public List<HeroResource> getAllHeroes() {
        log.warn(cause.getMessage(), cause);

        return new ArrayList<>();
    }
}
