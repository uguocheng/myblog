package com.gcnbl.beans;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public class BaseBean implements Serializable {
    private static final long serialVersionUID = 2049856502279610379L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "generator")
    //@GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    private Long id;
}
