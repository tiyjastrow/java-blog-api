package com.ryantablada.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ryantablada.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, String> {

}
