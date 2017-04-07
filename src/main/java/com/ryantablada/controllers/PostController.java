package com.ryantablada.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.Post;
import com.ryantablada.serializers.PostSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
public class PostController {
  @RequestMapping(path = "/posts", method = RequestMethod.GET)
  public Map<String, Object> findAllPost() {
    RootSerializer rootSerializer = new RootSerializer();
    PostSerializer postSerializer = new PostSerializer();

    Post post = new Post();
    post.setId(2);
    post.setTitle("This is my first Post");
    post.setContent("Lorem lorem lorem");

    Post post2 = new Post();
    post2.setId(15);
    post2.setTitle("This is my second Post");
    post2.setContent("More text goes here");

    List<HasId> posts = new ArrayList<>();
    posts.add(post);
    posts.add(post2);

    return rootSerializer.serializeMany("/posts", posts, postSerializer);
  }

  @RequestMapping(path = "/posts/1", method = RequestMethod.GET)
  public Map<String, Object> findOnePost() {
    RootSerializer rootSerializer = new RootSerializer();
    PostSerializer postSerializer = new PostSerializer();

    Post post = new Post();
    post.setId(2);
    post.setTitle("This is my first Post");
    post.setContent("Lorem lorem lorem");

    return rootSerializer.serializeOne("/posts/1", post, postSerializer);
  }
}
