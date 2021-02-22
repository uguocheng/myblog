package com.gcnbl.beans;

import com.gcnbl.annotation.DataProxy;
import com.gcnbl.service.BlogUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CreateDataProxy implements DataProxy<CreateBean> {

    @Resource
    private BlogUserService userService;

    @Override
    public void beforeAdd(CreateBean createBean) {
        createBean.setCreateTime(new Date());
        createBean.setCreateUser(new BlogUser(userService.getCurrentUid()));
    }

    @Override
    public void beforeUpdate(CreateBean createBean) {
        createBean.setUpdateTime(new Date());
        createBean.setUpdateUser(new BlogUser(userService.getCurrentUid()));
    }
}