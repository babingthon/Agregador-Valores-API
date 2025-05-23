package tech.flag.agregadordeinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.flag.agregadordeinvestimentos.dto.CreateUserDto;
import tech.flag.agregadordeinvestimentos.dto.UpdateUserDto;
import tech.flag.agregadordeinvestimentos.entity.User;
import tech.flag.agregadordeinvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(CreateUserDto createUserDto) {

        var entity = new User(
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Long userId, UpdateUserDto updateUserDto) {
        var userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteUser(Long userId) {
        var userExists = userRepository.findById(userId);
        if (userExists.isPresent()) {
            userRepository.deleteById(userId);
        }
    }
}
