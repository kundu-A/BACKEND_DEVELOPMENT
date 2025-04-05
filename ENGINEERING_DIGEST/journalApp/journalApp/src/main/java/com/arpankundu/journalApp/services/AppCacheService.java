package com.arpankundu.journalApp.services;

import com.arpankundu.journalApp.models.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppCacheService {

    @Autowired
    JournalServices journalServices;

    private List<JournalEntry> getData;

    public void reloadData(){
        getData=new ArrayList<>();
        getData=journalServices.getAllData();
    }
    public List<JournalEntry> getReloadedData(){
        return getData;
    }
}
