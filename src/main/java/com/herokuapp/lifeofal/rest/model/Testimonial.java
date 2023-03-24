package com.herokuapp.lifeofal.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "testimonials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Testimonial extends AbstractObject {

//    @Id
//    private ObjectId id;
//    private Integer testId;
    private String user;
    private String text;
    private List<String> links;
}
