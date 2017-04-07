package com.ryantablada.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ryantablada.entities.Post;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, String> {

}
