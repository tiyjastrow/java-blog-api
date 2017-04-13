package com.theironyard.repositories;

import org.springframework.data.repository.CrudRepository;
import com.theironyard.entities.Post;

public interface PostRepository extends CrudRepository<Post, String> {

}
