package com.example.jobcentrebackend.service.user;

import com.example.jobcentrebackend.dto.user.UserDto;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.user.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UnemployedRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UnemployedRepository unemployedRepository;

    public UserDto getUserId(@NotNull long id) throws UserNotFoundException {
        return UserDto.toDto(userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }

    public UserDto getUserPhone(@NotNull String phone) throws UserNotFoundException {
        return UserDto.toDto(userRepository
                .findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
    }
}
