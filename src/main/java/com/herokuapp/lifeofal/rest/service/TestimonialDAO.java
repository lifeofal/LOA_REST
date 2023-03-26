package com.herokuapp.lifeofal.rest.service;

import com.herokuapp.lifeofal.rest.model.Testimonial;
import com.herokuapp.lifeofal.rest.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
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
        Query query = new Query();
        query.addCriteria(Criteria.where("approved").is(true));

        long count = mongoTemplate.count(query, Testimonial.class);
        List<Testimonial> randomTestimonials;

        try{
        if (count < 3) {
            throw new Exception("There are less then 3 approved testimonials");
        }

                randomTestimonials = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("approved").is(true)),
                        Aggregation.sample(3)),
                "testimonials", Testimonial.class).getMappedResults();

    } catch (Exception e) {
            randomTestimonials = mongoTemplate.find(query, Testimonial.class);
    }

        return randomTestimonials;
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
