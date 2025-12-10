package com.gcnbl.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "b_user")
public class BlogUser extends CreateBean {

    private static final long serialVersionUID = 1048590861826298403L;

    public BlogUser() {
    }
    public BlogUser(Long id) {
        this.setId(id);
    }

    private String name;

    private String password;

    private Long phone;

    private String email;
}







