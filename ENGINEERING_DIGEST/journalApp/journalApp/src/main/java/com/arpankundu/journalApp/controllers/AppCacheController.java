package com.arpankundu.journalApp.controllers;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.services.AppCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppCacheController {

    @Autowired
    AppCacheService appCacheService;

    @PostMapping("/reload-journal-entry")
    public ResponseEntity<?> reloadJournalEntries(){
        try{
            appCacheService.reloadData();
            return new ResponseEntity<>("Journal Entries are reloaded!!",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some internal problem is happening", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get-reloaded-data")
    public ResponseEntity<?> getReloadedData(){
        try{
            List<JournalEntry> allEntries=appCacheService.getReloadedData();
            if(allEntries.isEmpty())
                return new ResponseEntity<>("May be no entries are found or entries are not reloaded!!",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(allEntries,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal errors!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
