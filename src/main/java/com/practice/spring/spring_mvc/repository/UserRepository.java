package com.practice.spring.spring_mvc.repository;

import com.practice.spring.spring_mvc.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    // as we have made username field indexed therefore we can declare this function to get the users by username
    User findByUserName(String username);

    void deleteByUserName(String username);
}
