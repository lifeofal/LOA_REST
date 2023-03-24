package com.herokuapp.lifeofal.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractObject {

    @Id
    private ObjectId id;
    private Integer uid;
}
