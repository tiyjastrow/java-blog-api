package com.ryantablada.controllers.comment;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ryantablada.entities.Comment;
import com.ryantablada.repositories.CommentRepository;
import com.ryantablada.serializers.*;

@RestController
@CrossOrigin(origins = "*")
public class CommentPostController {
  @Autowired
  CommentRepository comments;

  RootSerializer rootSerializer;
  PostSerializer postSerializer;

  public CommentPostController() {
    rootSerializer = new RootSerializer();
    postSerializer = new PostSerializer();
  }

  @RequestMapping(path = "/comments/{id}/post", method = RequestMethod.GET)
  public Map<String, Object> getPostComment(@PathVariable("id") String id) {
    Comment comment = comments.findOne(id);

    return rootSerializer.serializeOne("/comments/" + comment.getId() + "/post", comment.getPost(), postSerializer);
  }
}
