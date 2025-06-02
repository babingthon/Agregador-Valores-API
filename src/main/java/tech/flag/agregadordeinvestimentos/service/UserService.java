package tech.flag.agregadordeinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.flag.agregadordeinvestimentos.dto.AccountResponseDto;
import tech.flag.agregadordeinvestimentos.dto.CreateAccountDto;
import tech.flag.agregadordeinvestimentos.dto.CreateUserDto;
import tech.flag.agregadordeinvestimentos.dto.UpdateUserDto;
import tech.flag.agregadordeinvestimentos.entity.Account;
import tech.flag.agregadordeinvestimentos.entity.BillingAddress;
import tech.flag.agregadordeinvestimentos.entity.User;
import tech.flag.agregadordeinvestimentos.repository.AccountRepository;
import tech.flag.agregadordeinvestimentos.repository.BillingAddressRepository;
import tech.flag.agregadordeinvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    public void createAccount(Long userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var account = new Account(
            new ArrayList<>(),
            createAccountDto.description(),
           null,
            user
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

       var accounts = user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId(), ac.getDescription()))
                .toList();

        return accounts;
    }
}
