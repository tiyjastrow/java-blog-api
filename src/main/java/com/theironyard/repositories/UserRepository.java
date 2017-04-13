package com.theironyard.repositories;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by johnjastrow on 4/10/17.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String name);
    List<User> findAll();
}
