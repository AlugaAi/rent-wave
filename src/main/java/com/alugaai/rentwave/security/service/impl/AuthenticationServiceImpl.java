package com.alugaai.rentwave.security.service.impl;

import com.alugaai.rentwave.lib.annotations.LogExecution;
import com.alugaai.rentwave.lib.error.exception.ApplicationException;
import com.alugaai.rentwave.lib.error.exception.RepositoryException;
import com.alugaai.rentwave.security.dto.AuthenticateInputDto;
import com.alugaai.rentwave.security.dto.AuthenticateOutputDto;
import com.alugaai.rentwave.security.dto.RegisterInputDto;
import com.alugaai.rentwave.security.dto.RegisterOutputDto;
import com.alugaai.rentwave.security.entity.Account;
import com.alugaai.rentwave.security.enums.RoleEnum;
import com.alugaai.rentwave.security.jwt.JwtService;
import com.alugaai.rentwave.security.model.AccountModel;
import com.alugaai.rentwave.security.repository.AccountRepository;
import com.alugaai.rentwave.security.service.AuthenticationService;
import com.alugaai.rentwave.shared.annotations.TrackError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final AccountRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    @TrackError
    @LogExecution
    public RegisterOutputDto register(RegisterInputDto input) {
        log.info("Registering user: {}.", input.username());

        var account = Account.builder()
                .username(input.username())
                .password(passwordEncoder.encode(input.password()))
                .role(RoleEnum.USER)
                .build();

        var accountModel = AccountModel.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roleId(RoleEnum.USER.getId())
                .build();

        log.info("Persisting user into database.");
        userRepository.save(accountModel);

        log.info("Generating user jwt token.");
        var token = jwtService.generateToken(account);

        log.info("User successfully registered.");
        return new RegisterOutputDto(token);
    }

    @Override
    public AuthenticateOutputDto authenticate(AuthenticateInputDto input) {
        log.info("Authenticating user: {}.", input.username());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    input.username(),
                    input.password()
            )
        );

        log.info("Retrieving user from database.");
        var userModel = userRepository.findByUsername(input.username())
                .orElseThrow();

        var user = Account.builder()
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .role(RoleEnum.parse(userModel.getRoleId()))
                .build();

        log.info("Generating user jwt token.");
        var token = jwtService.generateToken(user);

        log.info("User successfully authenticated.");
        return new AuthenticateOutputDto(token);
    }

}
