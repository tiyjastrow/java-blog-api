package com.ryantablada.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ryantablada.entities.Post;

public interface PostRepository extends CrudRepository<Post, String> {

}
