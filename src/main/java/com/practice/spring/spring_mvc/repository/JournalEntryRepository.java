package com.practice.spring.spring_mvc.repository;

import com.practice.spring.spring_mvc.entity.PersistenceJournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<PersistenceJournalEntry, ObjectId> {

}
