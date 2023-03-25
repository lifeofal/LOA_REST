package com.herokuapp.lifeofal.rest.repository;

import com.herokuapp.lifeofal.rest.model.Testimonial;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestimonialRepository extends MongoRepository<Testimonial, ObjectId> {
    Optional<Testimonial> findTestimonialByUid(Integer uid);
    Optional<Testimonial> findThreeRandomTestimonialByApproved(Boolean uid);

}
