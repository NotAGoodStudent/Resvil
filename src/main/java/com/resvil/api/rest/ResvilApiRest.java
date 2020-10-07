package com.resvil.api.rest;
import com.resvil.api.classes.Product;
import com.resvil.api.classes.User;
import com.resvil.api.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResvilApiRest
{

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao prodDao;

    @RequestMapping(value = "getUser/{mail}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("mail") String mail)
    {

        Optional<User> foundUser = userDao.findById(mail);
        if(foundUser.isPresent())
        {
            User u = foundUser.get();
            return ResponseEntity.ok(u);
        }

        else
            {
                return ResponseEntity.noContent().build();
            }


    }
 /*   @RequestMapping(value = "getProd", method = RequestMethod.GET)
    public ResponseEntity<Product> getProd()
    {

    }*/



     @RequestMapping(value = "addUser", method =  RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        List<User> users = userDao.findAll();
        for(User u : users)
        {
            if(u.getEmail().equals(user.getEmail()))
            {
                return ResponseEntity.noContent().build();

            }
        }

        userDao.save(user);
        return ResponseEntity.ok(user);


    }

    
}
