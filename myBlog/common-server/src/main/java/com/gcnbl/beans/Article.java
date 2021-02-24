package com.gcnbl.beans;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "b_article")
public class Article extends CreateBean{
    private static final long serialVersionUID = -5952645553878508035L;

    private String content;
}
