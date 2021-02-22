package com.gcnbl.beans;

import com.gcnbl.annotation.PreDataProxy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@PreDataProxy(CreateDataProxy.class)
public class CreateBean extends BaseBean {
    private static final long serialVersionUID = 4728595796964761962L;

    private BlogUser createUser;

    private BlogUser updateUser;

    //@SkipSerialize
    private Date createTime;

    //@SkipSerialize
    private Date updateTime;
}
