package com.gcnbl.dao;

import com.gcnbl.beans.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<BlogUser,Long> {

}
