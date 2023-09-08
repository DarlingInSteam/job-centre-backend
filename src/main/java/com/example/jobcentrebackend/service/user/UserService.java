package com.example.jobcentrebackend.service.user;

import com.example.jobcentrebackend.dto.employer.EmployerDto;
import com.example.jobcentrebackend.dto.user.UserDto;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
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

    public EmployerDto getUserEmployer(@NotNull String phone) throws UserNotFoundException, EmployerNotFoundException {
        var user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return EmployerDto.toDto(employerRepository
                .findByUser(user)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"))
        );
    }
}
