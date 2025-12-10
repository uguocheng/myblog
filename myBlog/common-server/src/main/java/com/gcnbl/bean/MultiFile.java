package com.gcnbl.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Entity
@Accessors(chain = true)  //默认chain为false，如果为true，则setter方法返回的是 this ，而不是 void
@Table(name = "b_multi_file")
public class MultiFile extends CreateBean {
    private static final long serialVersionUID = -8798211998240859697L;

    @Column(name = "oldFileName")
    private String oldFileName;

    @Column(name = "newFileName")
    private String newFileName;

    @Column(name = "ext")
    private String ext;

    @Column(name = "path")
    private String path;

    @Column(name = "size")
    private String size;

    @Column(name = "type")
    private String type;

    @Column(name = "isImg")
    private String isImg;

    @Column(name = "downcounts")
    private Integer downcounts;

    @Column(name = "uploadTime")
    private Date uploadTime;

    @Column(name = "url")
    private String url;

    @Column(name = "userId")
    private Long userId; //用户外键
}
