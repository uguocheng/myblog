package com.gcnbl.service.serviceImpl;


import com.gcnbl.beans.BlogUser;
import com.gcnbl.dao.UserDao;
import com.gcnbl.service.BlogUserService;
import com.gcnbl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addBlogUser(String username, String password, String password2, int telphone) {
        if (!password.equals(password2)){
            return 0;
        }
        BlogUser blogUser = new BlogUser();
        blogUser.setName(username);
        blogUser.setPassword(password);
        blogUser.setPhone(telphone);

        BlogUser blogUser1 = userDao.save(blogUser);
        if (blogUser1 != null) {
            return 1;
        }
        return 0;
    }
}
