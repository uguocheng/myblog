package com.gcnbl.dao;

import com.gcnbl.beans.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<BlogUser,Long> /*, JpaSpecificationExecutor<BlogUser>*/ {

    //@Query(value = "from BlogUser where name=?1 and password=?2")

    BlogUser findBlogUserByNameAndPassword(String name,String password);

    BlogUser findBlogUserByName(String name);
}
