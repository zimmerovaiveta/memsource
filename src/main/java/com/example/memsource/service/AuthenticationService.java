package com.example.memsource.service;

import com.example.memsource.controller.NotFoundError;
import com.example.memsource.dto.UserCredentialsDto;
import com.example.memsource.dto.UserProfile;
import com.example.memsource.entity.UserCredentials;
import com.example.memsource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final String LOGIN_URL = "https://cloud.memsource.com/web/api2/v1/auth/login";
    private Map<Long, UserProfile> authenticatedUsers;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    public AuthenticationService() {
        authenticatedUsers = new HashMap<>();
    }

    public UserProfile getUserProfile(long userId) {

        UserProfile _profile = authenticatedUsers.get(userId);
        if(_profile != null && _profile.getExpires().isAfter(Instant.now()))  {
            return _profile;
        }

        UserProfile profile = authenticate(userId);
        authenticatedUsers.put(userId, profile);
        return profile;
    }

    private UserProfile authenticate(long userId) {
        UserCredentials credentials = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundError("user", userId));

        HttpEntity<UserCredentialsDto> request = new HttpEntity<>(new UserCredentialsDto(credentials));
        ResponseEntity<UserProfile> response = restTemplate
                .exchange(LOGIN_URL, HttpMethod.POST, request, UserProfile.class);

        return response.getBody();
    }
}
