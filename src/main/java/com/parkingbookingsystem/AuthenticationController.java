package com.parkingbookingsystem;

import com.parkingbookingsystem.configuration.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest loginUser) throws AuthenticationException {
        JwtResponse response = new JwtResponse();
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin")
    public String admin(){
        return "HELLO Admin......";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("user")
    public String user(){
        return "HELLO User......";
    }

    @RequestMapping("/resource")
    public  ResponseEntity<?> home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap map = new HashMap<>();
        map.put("Hello You are looged in as ",auth.getPrincipal());
        map.put("UUID OF USER IS ", applicationUserRepository.setUserIdToToken(auth.getName()));
        return ResponseEntity.ok(map);
    }
}
