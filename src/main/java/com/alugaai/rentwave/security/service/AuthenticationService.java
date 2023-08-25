package com.alugaai.rentwave.security.service;

import com.alugaai.rentwave.security.dto.AuthenticateInputDto;
import com.alugaai.rentwave.security.dto.AuthenticateOutputDto;
import com.alugaai.rentwave.security.dto.RegisterInputDto;
import com.alugaai.rentwave.security.dto.RegisterOutputDto;

public interface AuthenticationService {

    RegisterOutputDto register(RegisterInputDto registerRequest);

    AuthenticateOutputDto authenticate(AuthenticateInputDto authenticationRequest);

}
