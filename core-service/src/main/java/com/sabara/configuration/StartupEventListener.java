package com.sabara.configuration;

import com.sabara.model.entity.Hero;
import com.sabara.service.HeroService;
import com.sabara.service.IntegrationServiceClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "event.listener.enabled", havingValue = "true")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StartupEventListener {

    private final IntegrationServiceClient integrationServiceClient;
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStart() {
        integrationServiceClient
            .getAllHeroes()
            .stream()
            .map(heroResource -> modelMapper.map(heroResource, Hero.class))
            .forEach(hero -> {
                hero.setName(hero.getName() + " " + hero.getFullname());
                heroService.addHero(hero);
            });
    }

}
