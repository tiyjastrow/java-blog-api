package com.theironyard.controllers;

import com.theironyard.entities.User;
import com.theironyard.serializers.RootSerializer;
import com.theironyard.parsers.RootParser;
import com.theironyard.repositories.UserRepository;
import com.theironyard.serializers.UserSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by johnjastrow on 4/10/17.
 */
@RestController
public class UserController {
    @Autowired
    UserRepository users;

    RootSerializer rootSerializer = new RootSerializer();
    UserSerializer userSerializer = new UserSerializer();

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody RootParser<User> parser, HttpServletResponse response) {
        User user = parser.getData().getEntity();

        try {
            users.save(user);
        } catch ( DataIntegrityViolationException e) {
            System.out.println("Error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }

}
