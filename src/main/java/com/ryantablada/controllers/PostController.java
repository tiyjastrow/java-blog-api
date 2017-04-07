package com.ryantablada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ryantablada.entities.Post;
import com.ryantablada.parsers.RootParser;
import com.ryantablada.repositories.PostRepository;
import com.ryantablada.serializers.PostSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
@CrossOrigin(origins = "*")
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

  @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
  public Map<String, Object> findOnePost(@PathVariable("id") String id) {
    Post post = posts.findOne(id);

    return rootSerializer.serializeOne("/posts/" + post.getId(), post, postSerializer);
  }

  @RequestMapping(path = "/posts/{id}", method = RequestMethod.PATCH)
  public Map<String, Object> updatePost(@PathVariable("id") String id, @RequestBody RootParser<Post> parser) {
    Post existingPost = posts.findOne(id);
    Post input = parser.getData().getEntity();

    existingPost.setContent(input.getContent());
    existingPost.setTitle(input.getTitle());
    
    posts.save(existingPost);

    return rootSerializer.serializeOne("/posts/" + existingPost.getId(), existingPost, postSerializer);
  }

  @RequestMapping(path = "/posts/{id}", method = RequestMethod.DELETE)
  public void deletePost(@PathVariable("id") String id, HttpServletResponse response) {
    posts.delete(id);

    response.setStatus(204);
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
