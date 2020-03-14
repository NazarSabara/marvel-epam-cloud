package com.sabara.service;

import com.sabara.dto.HeroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/heroes")
@FeignClient(name = "core-service", url = "core-service:8080")
public interface CoreServiceClient {

    @GetMapping("/{id}")
    Optional<HeroDTO> getHeroById(@PathVariable String id);
}
