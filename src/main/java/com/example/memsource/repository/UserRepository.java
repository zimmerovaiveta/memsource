package com.example.memsource.repository;

import com.example.memsource.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByUserName(String userName);

}
