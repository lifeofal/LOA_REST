package com.herokuapp.lifeofal.rest.controller;

import com.herokuapp.lifeofal.rest.model.AbstractObject;
import com.herokuapp.lifeofal.rest.model.Testimonial;
import com.herokuapp.lifeofal.rest.service.TestimonialDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/")
public class TestimonialController {

    @Autowired
    private TestimonialDAO testimonialDAO;

    @GetMapping("testimonial")
    public ResponseEntity<List<Testimonial>> getAllTestimonials() {
        return new ResponseEntity<List<Testimonial>>(testimonialDAO.allTestimonials(), HttpStatus.OK);
    }

    @GetMapping("testimonial/{id}")
    public ResponseEntity<Optional<Testimonial>> getTestimonialById(@PathVariable Integer uid) {
        return new ResponseEntity<Optional<Testimonial>>(testimonialDAO.findMovieByUid(uid), HttpStatus.OK);
    }

    @PutMapping("{entity}/create")
    public void createEntity(@RequestBody AbstractObject objBody, @PathVariable String entity) {
        createOrUpdateEntity(objBody, entity, objBody.getUid());
    }

    @PostMapping("{entity}/update/{id}")
    public ResponseEntity<Object> createOrUpdateEntity(@RequestBody AbstractObject objBody, @PathVariable String entity, @PathVariable Integer id) {
        Object savedEntity = null;
        Boolean foundFlag = false;

        switch (entity) {
            case "testimonial":
                Optional<Testimonial> tempTestimonial = testimonialDAO.findMovieByUid(id);
                if (tempTestimonial.isPresent()) {
                    Testimonial existingCustomer = tempTestimonial.get();
                    Testimonial testimonialBody = (Testimonial) objBody;
                    existingCustomer.setId(testimonialBody.getId());
                    existingCustomer.setUser(testimonialBody.getUser());
                    existingCustomer.setText(testimonialBody.getText());
                    existingCustomer.setLinks(testimonialBody.getLinks());
                    savedEntity = testimonialDAO.createOrUpdate(existingCustomer);
                    foundFlag = true;
                }
                break;
        }
        if (foundFlag) {
            return ResponseEntity.ok(savedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
