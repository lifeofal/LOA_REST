package com.herokuapp.lifeofal.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractObject{
//    private List<String> links;
    private String email;
//    private byte[] profilePicture;
    private String name;
    private String title;
    private String link;


}
