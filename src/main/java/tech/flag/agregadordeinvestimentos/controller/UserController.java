package tech.flag.agregadordeinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.flag.agregadordeinvestimentos.dto.CreateUserDto;
import tech.flag.agregadordeinvestimentos.dto.UpdateUserDto;
import tech.flag.agregadordeinvestimentos.entity.User;
import tech.flag.agregadordeinvestimentos.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Long userId) {
        var user = userService.findUserById(userId);

        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return userService.findAllUsers().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(userService.findAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId,
                                           @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
      userService.deleteUser(userId);
      return ResponseEntity.noContent().build();
    }
}
