package com.test.credibanca.dto;

import com.test.credibanca.entity.User;
import lombok.Data;
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String lastName;


    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
    }

}