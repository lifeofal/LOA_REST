package com.herokuapp.lifeofal.rest.controller;

import com.herokuapp.lifeofal.rest.model.AbstractObject;
import com.herokuapp.lifeofal.rest.model.Testimonial;
import com.herokuapp.lifeofal.rest.service.TestimonialDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/testimonial")
public class TestimonialController {

    @Autowired
    private TestimonialDAO testimonialDAO;
    @Autowired
    private MongoOperations mongoOperations;

    @GetMapping("testimonial")
    public ResponseEntity<List<Testimonial>> getAllTestimonials() {
        return new ResponseEntity<List<Testimonial>>(testimonialDAO.allTestimonials(), HttpStatus.OK);
    }

    @GetMapping("testimonial/{uid}")
    public ResponseEntity<Optional<Testimonial>> getTestimonialById(@PathVariable Integer uid) {
        return new ResponseEntity<Optional<Testimonial>>(testimonialDAO.findTestimonialByUid(uid), HttpStatus.OK);
    }

    //put should create but the json body shouldnt include a UID
    //We only want to create
    @PutMapping("/create")
    public ResponseEntity<Object> createTestimonial(@RequestBody Testimonial objBody) {
        //still need to fix query issues anyway
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "uid")).limit(1);
        Testimonial lastObj = mongoOperations.findOne(query, Testimonial.class);
        int nextUid = (lastObj == null) ? 1 : lastObj.getUid() + 1;
        objBody.setUid(nextUid);
        return createOrUpdateTestimonial(objBody, objBody.getUid());
    }

    @PostMapping("/update/{uid}")
    public ResponseEntity<Object> createOrUpdateTestimonial(@RequestBody Testimonial objBody, @PathVariable Integer uid) {
        Object savedEntity = null;
        Boolean foundFlag = false;

        Optional<Testimonial> tempTestimonial = testimonialDAO.findTestimonialByUid(uid);
        if (tempTestimonial.isPresent()) {
            Testimonial existingCustomer = tempTestimonial.get();
            existingCustomer.setId(objBody.getId());
            existingCustomer.setUser(objBody.getUser());
            existingCustomer.setText(objBody.getText());
            if (objBody.getLinks() != null) {
                existingCustomer.setLinks(objBody.getLinks());
            }
            savedEntity = testimonialDAO.createOrUpdate(existingCustomer);
            foundFlag = true;
        }else {
            savedEntity = testimonialDAO.create(objBody);
        }

        if (foundFlag) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Testimonial has been updated");
            response.put("entity", savedEntity);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Testimonial has been created");
            response.put("entity", savedEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }


}
