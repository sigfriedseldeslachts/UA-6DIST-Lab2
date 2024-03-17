package be.uantwerpen.lab2.controllers;

import be.uantwerpen.lab2.models.User;
import be.uantwerpen.lab2.models.UserBody;
import be.uantwerpen.lab2.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BankService service;

    @PostMapping
    public User create(@RequestBody UserBody body)
    {
        User user = new User(service.getNextUserId(), body.name);

        service.addUser(user);

        return user;
    }

    @GetMapping("/{id}")
    public User show(@PathVariable long id)
    {
        return service.getUser(id);
    }

}