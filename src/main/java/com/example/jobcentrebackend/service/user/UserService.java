package com.example.jobcentrebackend.service.user;

import com.example.jobcentrebackend.dto.employer.EmployerDto;
import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.dto.user.UserDto;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.unemployed.UnemployedNotFoundException;
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

    public String updateUsername(@NotNull String username) throws UserNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(username);

        userRepository.save(user);

        return "Username updated";
    }

    public UserDto getUserId(@NotNull long id) throws UserNotFoundException {
        return UserDto.toDto(userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }

    public UserDto getUserUsername(@NotNull String username) throws UserNotFoundException {
        return UserDto.toDto(userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }

    public UserDto getUserPhone(@NotNull String phone) throws UserNotFoundException {
        return UserDto.toDto(userRepository
                .findByPhone(phone)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }

    public EmployerDto getUserEmployer(@NotNull String username) throws UserNotFoundException, EmployerNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return EmployerDto.toDto(employerRepository
                .findByUser(user)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"))
        );
    }

    public UnemployedDto getUserUnemployed(@NotNull String username) throws UserNotFoundException, UnemployedNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UnemployedDto.toDto(unemployedRepository
                .findByUser(user)
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed not found exception"))
        );
    }
}
