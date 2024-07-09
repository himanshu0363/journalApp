package com.practice.spring.spring_mvc.service;

import com.practice.spring.spring_mvc.entity.PersistenceJournalEntry;
import com.practice.spring.spring_mvc.entity.User;
import com.practice.spring.spring_mvc.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional // as there are two db operations in this function therefore we need to
    // make sure that both happens or none of them happens
    public void saveEntry(PersistenceJournalEntry persistenceJournalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        persistenceJournalEntry.setDate(LocalDateTime.now());
        PersistenceJournalEntry save = journalEntryRepository.save(persistenceJournalEntry);
        User user = userService.findByUsername(username);
        user.getJournalEntries().add(save);
        userService.saveUserJournalEntries(user);
    }

    public List<PersistenceJournalEntry> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User byUsername = userService.findByUsername(username);
        return byUsername.getJournalEntries();
    }

    public PersistenceJournalEntry findById(ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<PersistenceJournalEntry> list = user.getJournalEntries().stream().filter(entry -> entry.getId().equals(id)).collect(Collectors.toList());
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public void deleteById(ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User byUsername = userService.findByUsername(username);
        byUsername.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        userService.saveUserJournalEntries(byUsername);
        journalEntryRepository.deleteById(id);
    }

    public PersistenceJournalEntry updateById(PersistenceJournalEntry persistenceJournalEntry) {
        return journalEntryRepository.save(persistenceJournalEntry);
    }
}
