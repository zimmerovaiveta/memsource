package com.example.memsource.service;

import com.example.memsource.dto.UserCredentialsDto;
import com.example.memsource.entity.UserCredentials;
import com.example.memsource.repository.UserRepository;
import com.example.memsource.controller.NotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserCredentialsDto getUserById (long userId) {
        UserCredentials userCredentials = repository.findById(userId)
                .orElseThrow(() -> new NotFoundError("User", userId));
        return new UserCredentialsDto(userCredentials);
    }

    public UserCredentialsDto getUserByUserName (String userName) {
        UserCredentials userCredentials = repository.findByUserName(userName)
                .orElseThrow(() -> new NotFoundError("User", userName));
        return new UserCredentialsDto(userCredentials);
    }

    public UserCredentialsDto saveUser(UserCredentialsDto userCredentialsDto) {
        UserCredentials userCredentials = repository.save(new UserCredentials(userCredentialsDto));
        return new UserCredentialsDto(userCredentials);
    }

    public List<UserCredentialsDto> getAllUsers() {
        return repository.findAll().stream().map(UserCredentialsDto::new).collect(Collectors.toList());
    }

    public UserCredentialsDto updateUser(UserCredentialsDto updatedUserCredentialsDto, long userId) {
        UserCredentials userCredentials = repository.findById(userId)
                .map(_userCredentials -> {
                    _userCredentials.setUserName(updatedUserCredentialsDto.getUserName());
                    _userCredentials.setPassword(updatedUserCredentialsDto.getPassword());
                    return repository.save(_userCredentials);
                })
                .orElseGet(() -> {
                    updatedUserCredentialsDto.setUserId(userId);
                    return repository.save(new UserCredentials(updatedUserCredentialsDto));
                });
        return new UserCredentialsDto(userCredentials);
    }

    public void deleteUser(long userId) {
        repository.deleteById(userId);
    }

}
