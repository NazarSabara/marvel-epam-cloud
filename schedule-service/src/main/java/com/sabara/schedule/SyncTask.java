package com.sabara.schedule;

import com.sabara.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SyncTask {

    private final SyncService syncService;

    @Scheduled()
    public void syncHeroes(){
        syncService.sync();
    }
}
