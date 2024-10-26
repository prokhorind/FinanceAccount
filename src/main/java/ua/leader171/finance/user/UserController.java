package ua.leader171.finance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser appUser) {
        AppUser newAppUser = userService.createUser(appUser);
        return ResponseEntity.ok(newAppUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUser(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
