package com.theironyard.repositories;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johnjastrow on 4/10/17.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
