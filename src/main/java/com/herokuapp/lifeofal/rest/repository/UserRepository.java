package com.herokuapp.lifeofal.rest.repository;

import com.herokuapp.lifeofal.rest.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends MongoRepository<User, ObjectId> {
}
