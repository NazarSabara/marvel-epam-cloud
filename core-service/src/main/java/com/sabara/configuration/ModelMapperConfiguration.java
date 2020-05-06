package com.sabara.configuration;

import com.sabara.model.entity.Group;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.HeroResource;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(heroPropertyMap);
        modelMapper.addMappings(heroResourcePropertyMap);

        return modelMapper;
    }

    private PropertyMap<HeroResource, Hero> heroPropertyMap = new PropertyMap<>() {
        protected void configure() {
            using(groupToEntityConverter).map(source.getGroups()).setGroups(null);
        }
    };

    private PropertyMap<Hero, HeroResource> heroResourcePropertyMap = new PropertyMap<>() {
        protected void configure() {
            using(groupToResourceConverter).map(source.getGroups()).setGroups(null);
        }
    };

    private Converter<Set<String>, Set<Group>> groupToEntityConverter = context ->
        context.getSource().stream()
            .filter(StringUtils::isNotBlank)
            .map(Group::new)
            .collect(toSet());

    private Converter<Collection<Group>, Set<String>> groupToResourceConverter = context ->
        context.getSource().stream()
            .map(Group::getName)
            .collect(toSet());
}
