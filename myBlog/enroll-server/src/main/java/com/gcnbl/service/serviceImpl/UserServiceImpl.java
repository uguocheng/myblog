package com.gcnbl.service.serviceImpl;


import com.gcnbl.beans.BlogUser;
import com.gcnbl.dao.UserDao;
import com.gcnbl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void addBlogUser(String username, String password, int telphone) {

        BlogUser blogUser = new BlogUser();
        blogUser.setName(username);
        blogUser.setPassword(password);
        blogUser.setPhone(telphone);

        userDao.save(blogUser);
    }

    @Override
    public Long login(String username, String password) {

        BlogUser blogUser = userDao.findBlogUserByNameAndPassword(username, password);
        Long userId = blogUser.getId();

        return userId;
    }
}
