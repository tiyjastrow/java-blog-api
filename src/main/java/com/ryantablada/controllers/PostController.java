package com.ryantablada.controllers;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.Post;
import com.ryantablada.parsers.RootParser;
import com.ryantablada.repositories.PostRepository;
import com.ryantablada.serializers.PostSerializer;
import com.ryantablada.serializers.RootSerializer;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PostController {

  RootSerializer rootSerializer = new RootSerializer();
  PostSerializer postSerializer = new PostSerializer();

  @Autowired
  PostRepository posts;

  @RequestMapping(path = "/posts", method = RequestMethod.GET)
  public Map<String, Object> findAllPosts() {
    Iterable<Post> results = posts.findAll();

    return rootSerializer.serializeMany("/posts", results, postSerializer);
  }

  @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
  public Map<String, Object> findOnePost(@PathVariable String id) {
//    Post post = new Post();
//    post.setId("2");
//    post.setTitle("This is my first Post");
//    post.setContent("Lorem lorem lorem");
    Post post = posts.findOne(id);

    return rootSerializer.serializeOne("/posts/" + id, post, postSerializer);
  }
  @RequestMapping(path = "/posts", method = RequestMethod.POST)
  public Map<String, Object> createPost(@RequestBody RootParser<Post> parser, HttpServletResponse response) {
    Post post = parser.getData().getEntity();

   post.setUser("abc123;aksdhv;oauhdv;ah001");    //post.setUser(parser.getData().getId());

    try {
      posts.save(post);
    } catch ( DataIntegrityViolationException e) {
      System.out.println("Save error: " + e.getMessage());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    } catch (Exception e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    return rootSerializer.serializeOne(
      "/posts/" + post.getId(),
      post,
      postSerializer);
  }
}
