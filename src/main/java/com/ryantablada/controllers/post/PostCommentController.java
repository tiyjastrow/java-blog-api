package com.ryantablada.controllers.post;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ryantablada.entities.Post;
import com.ryantablada.repositories.PostRepository;
import com.ryantablada.serializers.CommentSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
public class PostCommentController {
  @Autowired
  PostRepository posts;

  RootSerializer rootSerializer;
  CommentSerializer commentSerializer;

  public PostCommentController() {
    rootSerializer = new RootSerializer();
    commentSerializer = new CommentSerializer();
  }

  @RequestMapping(path = "/posts/{id}/comments", method = RequestMethod.GET)
  public Map<String, Object> getCommentsPost(@PathVariable("id") String id) {
    Post post = posts.findOne(id);

    return rootSerializer.serializeMany("/posts/" + post.getId() + "/comments", post.getComments(), commentSerializer);
  }
}