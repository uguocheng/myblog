package com.gcnbl.beans;

import com.gcnbl.annotation.PreDataProxy;
import com.gcnbl.annotation.SkipSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@PreDataProxy(CreateDataProxy.class)
public class CreateBean extends BaseBean {
    private static final long serialVersionUID = 4728595796964761962L;

    @SkipSerialize
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private BlogUser createUser;

    @SkipSerialize
    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    private BlogUser updateUser;

    @SkipSerialize
    @CreatedDate
    private Date createTime;

    @SkipSerialize
    @LastModifiedDate
    private Date updateTime;
}
