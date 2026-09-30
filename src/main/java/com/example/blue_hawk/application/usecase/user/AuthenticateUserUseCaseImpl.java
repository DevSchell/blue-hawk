package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.AuthenticateUserCommand;
import com.example.blue_hawk.application.dto.user.AuthenticateUserOutput;
import com.example.blue_hawk.domain.entity.User;
import com.example.blue_hawk.domain.repository.IUserRepository;
import com.example.blue_hawk.infrastructure.security.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public AuthenticateUserUseCaseImpl(IUserRepository userRepository,
                                       PasswordEncoder passwordEncoder,
                                       JwtTokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticateUserOutput handle(AuthenticateUserCommand command) {
        // Mesmo erro para e-mail inexistente e senha errada, para não revelar quais e-mails existem.
        User user = userRepository.findByEmail(User.normalizeEmail(command.email()))
                .filter(found -> passwordEncoder.matches(command.password(), found.getPasswordHash()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        return new AuthenticateUserOutput(tokenService.generateToken(user), "Bearer", tokenService.getExpirationSeconds());
    }
}
