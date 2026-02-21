package com.yassine.bookshop.repositories;

import com.yassine.bookshop.entities.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(@Email String email);
}
