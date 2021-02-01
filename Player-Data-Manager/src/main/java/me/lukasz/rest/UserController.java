package me.lukasz.rest;

import me.lukasz.presistance.domain.User;
import me.lukasz.presistance.dto.UserDTO;
import me.lukasz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController
{

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/readall")
    public ResponseEntity<List<UserDTO>> readAll()
    {
        return ResponseEntity.ok(userService.readAll());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") int id, @RequestBody User user)
    {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable("id") int id)
    {
        return !userService.deleteUser(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
