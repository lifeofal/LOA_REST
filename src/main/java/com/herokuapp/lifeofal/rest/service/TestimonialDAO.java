package com.herokuapp.lifeofal.rest.service;

import com.herokuapp.lifeofal.rest.model.Testimonial;
import com.herokuapp.lifeofal.rest.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestimonialDAO {
    @Autowired
    private TestimonialRepository testimonialRepository;
    public List<Testimonial> allTestimonials(){
        return testimonialRepository.findAll();
    }

    public Optional<Testimonial> findTestimonialByUid(Integer uid){
        return testimonialRepository.findMovieByUid(uid);
    }

    public Testimonial createOrUpdate(Testimonial testimonial){
        return testimonialRepository.save(testimonial);
    }
    public Testimonial create(Testimonial testimonial){
        return testimonialRepository.insert(testimonial);
    }
}
