package com.demo.controller;

import com.demo.Authentication.ImaniaUserDetailsService;
import com.demo.Authentication.JwtRequestFilter;
import com.demo.Authentication.JwtUtil;
import com.demo.dto.security.AuthenticationRequest;
import com.demo.dto.security.AuthenticationResponse;
import com.demo.dto.security.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private ImaniaUserDetailsService userDetailsService;

    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new AuthenticationResponse(authenticationRequest.getUsername(), LoginStatus.INVALID_PASSWORD));
        }catch (AuthenticationException x){
            return ResponseEntity.ok(new AuthenticationResponse(authenticationRequest.getUsername(), LoginStatus.INVALID_USERNAME));

        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails);
        final long personId=jwtUtil.getUserId(authenticationRequest.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(token, authenticationRequest.getUsername(), LoginStatus.LOGIN_SUCCESS,personId));
    }

    private void authenticate(String username, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @PostMapping(value = "/log-out")
    public void logout(){
        jwtRequestFilter.LogOutToken();
    }

}
