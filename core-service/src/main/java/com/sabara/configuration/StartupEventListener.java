package com.sabara.configuration;

import com.sabara.model.entity.Group;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.HeroResource;
import com.sabara.service.HeroService;
import com.sabara.service.IntegrationServiceClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StartupEventListener {

    private static final Function<String, Group> GROUP_MAPPER =
            group -> {
                Group groupToAdd = new Group();
                groupToAdd.setName(group);
                return groupToAdd;
            };

    @Value("${event.listener.enabled}")
    public boolean enabled;

    private final IntegrationServiceClient integrationServiceClient;
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @EventListener(condition = "@startupEventListener.enabled")
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        List<HeroResource> heroes = integrationServiceClient.getAllHeroes();
        heroes.stream().map(this::convertToEntity).forEach(heroService::addHero);
    }

    private Hero convertToEntity(HeroResource heroResource) {
        Hero hero = modelMapper.map(heroResource, Hero.class);
        hero.setName(new StringBuilder(hero.getName()).append(" ").append(heroResource.getFullname()).toString());
        hero.setGroups(heroResource.getGroups().stream().filter(StringUtils::isNotBlank).map(GROUP_MAPPER).collect(Collectors.toSet()));
        return hero;
    }
}
