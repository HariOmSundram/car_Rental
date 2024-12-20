package com.knowit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowit.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    
    @Query(value="select * from users where email=?1 and password=?2",nativeQuery = true)
    public User getByEmailandPassword(String email,String password);

}
