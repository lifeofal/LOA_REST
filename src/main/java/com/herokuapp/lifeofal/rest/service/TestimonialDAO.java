package com.herokuapp.lifeofal.rest.service;

import com.herokuapp.lifeofal.rest.model.Testimonial;
import com.herokuapp.lifeofal.rest.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TestimonialDAO {
    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    MongoTemplate mongoTemplate;
    public List<Testimonial> allTestimonials(){
        return testimonialRepository.findAll();
    }

    public List<Testimonial> findThreeRandomApproved(Boolean approved) throws Exception {
//        return testimonialRepository.findThreeRandomTestimonialByApproved(approved);
        // Create a query to find all approved testimonials
        Query query = new Query();
        query.addCriteria(Criteria.where("approved").is(approved));

        // Get the total number of approved testimonials
        long count = mongoTemplate.count(query, Testimonial.class);
        if (count < 1) {
            throw new Exception();
        }

        // Generate three random numbers to select three testimonials
        if (count > 3) {
            List<Integer> randomNumbers = new ArrayList<>();
            Random rand = new Random();
            while (randomNumbers.size() < 3) {
                int randomNum = rand.nextInt((int) count);
                if (!randomNumbers.contains(randomNum)) {
                    randomNumbers.add(randomNum);
                }
            }



        // Create a query to find the three random approved testimonials
        Query queryRandom = new Query();
        queryRandom.addCriteria(Criteria.where("approved").is(approved));
        queryRandom.with(Sort.by(Sort.Direction.ASC, "_id"));
        queryRandom.skip(randomNumbers.get(0));
        queryRandom.limit(3);


        // Return the three random approved testimonials
        return mongoTemplate.find(queryRandom, Testimonial.class);
    }
     return mongoTemplate.find(query, Testimonial.class);
    }


    public Optional<Testimonial> findTestimonialByUid(Integer uid){
        return testimonialRepository.findTestimonialByUid(uid);
    }

    public Testimonial createOrUpdate(Testimonial testimonial){
        return testimonialRepository.save(testimonial);
    }
    public Testimonial create(Testimonial testimonial){
        return testimonialRepository.insert(testimonial);
    }
}
