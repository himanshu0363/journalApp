package com.practice.spring.spring_mvc.controller;

import com.practice.spring.spring_mvc.entity.PersistenceJournalEntry;
import com.practice.spring.spring_mvc.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("journal")
public class PersistenceAppController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAllJournalsByUsername() {
        try {
            List<PersistenceJournalEntry> list = journalEntryService.getAll();
            if (!list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
            }

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody PersistenceJournalEntry persistenceJournalEntry) {
        try {
            journalEntryService.saveEntry(persistenceJournalEntry);
            return new ResponseEntity<>(persistenceJournalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getJournalByIdSentInPath(@PathVariable ObjectId id) {
        PersistenceJournalEntry byId = journalEntryService.findById(id);
        if (byId != null) {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping
//    public JournalEntry getJournalByIdSentInParams(@RequestParam Long id) {
//        return null;
//    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable ObjectId id,
            @RequestBody PersistenceJournalEntry persistenceJournalEntry) {
        PersistenceJournalEntry byId = journalEntryService.findById(id);
        if (byId != null) {
            byId.setContent(byId.getContent().equals(persistenceJournalEntry.getContent()) ? byId.getContent() : persistenceJournalEntry.getContent());
            byId.setTitle(byId.getTitle().equals(persistenceJournalEntry.getTitle()) ? byId.getTitle() : persistenceJournalEntry.getTitle());
            PersistenceJournalEntry entry = journalEntryService.updateById(byId);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id) {
        try {
            journalEntryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
