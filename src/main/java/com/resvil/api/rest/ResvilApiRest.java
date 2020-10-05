package com.resvil.api.rest;
import com.resvil.api.classes.User;
import com.resvil.api.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResvilApiRest
{

    //@Autowired
    //private UserDao userDao;

    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public ResponseEntity<User> greet()
    {
        User u = new User(1, "Steve", "Donoghue", "asd@gmail.com", "123", "Admin");
        return ResponseEntity.ok(u);

    }

/*    @RequestMapping(value = "addUser", method =  RequestMethod.POST)
    public ResponseEntity<User> addUse(@RequestBody User user)
    {
        userDao.save(user);
        return ResponseEntity.ok(user);
    }*/
}
