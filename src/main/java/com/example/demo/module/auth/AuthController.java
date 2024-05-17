package com.example.demo.module.auth;

import com.example.demo.module.common.JwtService;
import com.example.demo.module.user.User;
import com.example.demo.util.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseTemplate<?> responseTemplate;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterInputDto registerUserDto) {
        User registeredUser = authService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest loginUserDto) {
        try {
            User authenticatedUser = authService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            AuthResponse loginResponse = new AuthResponse(jwtToken, jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(responseTemplate.build(401, "Incorrect User or Password. Please try again."), HttpStatus.UNAUTHORIZED);
        }
    }
}
