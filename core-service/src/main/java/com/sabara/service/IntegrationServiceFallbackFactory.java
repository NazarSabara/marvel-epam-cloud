package com.sabara.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class IntegrationServiceFallbackFactory implements FallbackFactory<IntegrationServiceClient> {

    @Override
    public IntegrationServiceClient create(Throwable cause) {
        return new IntegrationServiceFallback(cause);
    }
}
