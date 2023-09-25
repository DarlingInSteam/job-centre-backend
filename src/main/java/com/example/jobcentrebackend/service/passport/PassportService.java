package com.example.jobcentrebackend.service.passport;

import com.example.jobcentrebackend.dto.passport.CreatePassportRequest;
import com.example.jobcentrebackend.dto.passport.PassportDto;
import com.example.jobcentrebackend.entity.passport.PassportEntity;
import com.example.jobcentrebackend.exception.passport.PassportNotFoundException;
import com.example.jobcentrebackend.repository.passport.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PassportService {
    @Autowired
    private PassportRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PassportDto getPassportById(long id) throws PassportNotFoundException {
        PassportEntity passportEntity = repository
                .findById(id)
                    .orElseThrow(() -> new PassportNotFoundException("Passport not found"));

        return PassportDto.toDto(passportEntity);
    }

    public String updatePassport(CreatePassportRequest request, long id) throws PassportNotFoundException {
        PassportEntity passportEntity = repository
                .findById(id)
                    .orElseThrow(() -> new PassportNotFoundException("Passport not found"));

        passportEntity.setPassportNumber(passwordEncoder.encode(request.getPassportNumber()));
        passportEntity.setPassportIssuedBy(request.getPassportIssueBy());
        passportEntity.setPassportIssueDate(request.getPassportIssueDate());
        passportEntity.setAddress(request.getAddress());
        passportEntity.setPhoto(request.getPhoto());
        passportEntity.setDateOfBirth(request.getDateOfBirth());

        return "Passport was successfully update";
    }
}
