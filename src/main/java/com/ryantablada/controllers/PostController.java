package com.ryantablada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.Post;
import com.ryantablada.parsers.RootParser;
import com.ryantablada.repositories.PostRepository;
import com.ryantablada.serializers.PostSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
public class PostController {

  RootSerializer rootSerializer;
  PostSerializer postSerializer;

  public PostController() {
    rootSerializer = new RootSerializer();
    postSerializer = new PostSerializer();
  }

  @Autowired
  PostRepository posts;

  @RequestMapping(path = "/posts", method = RequestMethod.GET)
  public Map<String, Object> findAllPost() {
    Iterable<Post> results = posts.findAll();

    return rootSerializer.serializeMany("/posts", results, postSerializer);
  }

  @RequestMapping(path = "/posts/1", method = RequestMethod.GET)
  public Map<String, Object> findOnePost() {
    Post post = new Post();
    post.setId("2");
    post.setTitle("This is my first Post");
    post.setContent("Lorem lorem lorem");

    return rootSerializer.serializeOne("/posts/1", post, postSerializer);
  }

  @RequestMapping(path = "/posts", method = RequestMethod.POST)
  public Map<String, Object> storePost(@RequestBody RootParser<Post> parser) {
    Post post = parser.getData().getEntity();

    posts.save(post);

    return rootSerializer.serializeOne(
      "/posts/" + post.getId(),
      post,
      postSerializer);
  }
}
