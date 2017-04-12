package com.ryantablada.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ryantablada.entities.User;

public interface UserRepository extends CrudRepository<User, String> {
  User findByUsername(String username);
}
