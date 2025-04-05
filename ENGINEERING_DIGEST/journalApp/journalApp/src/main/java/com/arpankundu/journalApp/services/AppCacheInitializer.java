package com.arpankundu.journalApp.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppCacheInitializer {

    @Autowired
    AppCacheService appCacheService;

    @PostConstruct
    public void init(){
        appCacheService.reloadData();
    }
}
