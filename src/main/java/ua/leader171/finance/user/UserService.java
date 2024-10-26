package ua.leader171.finance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public AppUser createUser(AppUser appUser) {
        if (getUserByUsername(appUser.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Користувач із таким ім'ям уже існує");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("USER");
        return userRepository.save(appUser);
    }

    public Optional<AppUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
