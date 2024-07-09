package com.practice.spring.spring_mvc.controller;

import com.practice.spring.spring_mvc.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// This will make sure that the methods of this class will be called only when the journal endpoint is used
@RequestMapping("_journal")
public class AppController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    // it is not mandatory to use get or any other verb as it says like we can use get for adding a new entry, it is
    // just a standard that is followed, it might help when there are two methods that uses the same endpoint but does
    // different things like one adds and the other one just gets

    // this will be called when journal endpoint is hit like http://localhost:8080/journal
    // we can also use @GetMapping("all") and then this method will be called when http://localhost:8080/journal/all
    @GetMapping("all")
    public List<JournalEntry> getAllJournals() {
        return new ArrayList<>(journalEntries.values());
    }

    //@RequestBody is used to tell the method to convert the body that is sent in the request to the
    // JournalEntry Object.
    @PostMapping
    public Boolean createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntries.put(journalEntry.getId(), journalEntry);
        return true;
    }

    //{id} when the endpoint journal/id/2 is hit then 2 will be treated as id variable,
    // and it can be used inside method.
    //@PathVariable - to tell the method that this variable is a path variable
    @GetMapping("id/{id}")
    public JournalEntry getJournalByIdSentInPath(@PathVariable Long id) {
        return journalEntries.get(id);
    }

    //{id} when the endpoint journal/id/2 is hit then 2 will be treated as id variable,
    // and it can be used inside method.
    //@PathVariable - to tell the method that this variable is a path variable
    @GetMapping
    public JournalEntry getJournalByIdSentInParams(@RequestParam Long id) {
        return journalEntries.get(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
        journalEntries.put(id, journalEntry);
        return journalEntries.get(id);
    }

    @DeleteMapping("id/{id}")
    public Boolean deleteJournalById(@PathVariable Long id) {
        journalEntries.remove(id);
        return true;
    }

}
