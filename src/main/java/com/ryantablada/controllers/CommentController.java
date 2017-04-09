package com.ryantablada.controllers;

import java.util.HashMap;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ryantablada.entities.Comment;
import com.ryantablada.parsers.RootParser;
import com.ryantablada.repositories.CommentRepository;
import com.ryantablada.serializers.CommentSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
public class CommentController {
  
  @Autowired
  CommentRepository comments;

  RootSerializer rootSerializer;
  CommentSerializer commentSerializer;

  public CommentController() {
    this.rootSerializer = new RootSerializer();
    this.commentSerializer = new CommentSerializer();
  }


  @RequestMapping(path  = "/comments", method = RequestMethod.POST)
  public HashMap<String, Object> storeComment(@RequestBody RootParser<Comment> parser) {
    Comment comment = parser.getData().getEntity();
    comment.setPost(parser.getData().getRelationshipId("post"));

    System.out.println(comment.getPost().getId());

    return this.rootSerializer.serializeOne("/comments" + comment.getId(), comment, this.commentSerializer);
  }
}
