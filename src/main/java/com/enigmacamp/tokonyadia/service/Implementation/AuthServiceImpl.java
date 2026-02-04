package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.LoginRequest;
import com.enigmacamp.tokonyadia.dto.request.RegisterRequest;
import com.enigmacamp.tokonyadia.dto.response.LoginResponse;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.security.jwt.JwtUtil;
import com.enigmacamp.tokonyadia.service.AuthService;
import com.enigmacamp.tokonyadia.service.CustomerService;
import com.enigmacamp.tokonyadia.service.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final MemberService memberService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(MemberService memberService, CustomerService customerService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if(memberService.findByUsername(registerRequest.getUsername())){
            throw new IllegalStateException("Username is already in use");
        }

        Member member = new Member();
        member.setUsername(registerRequest.getUsername());
        member.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        memberService.saveMember(member);

        Customer customer = new Customer();
        customer.setFullname(registerRequest.getFullname());
        customer.setEmail(registerRequest.getEmail());
        customer.setAddress(registerRequest.getAddress());
        customer.setGender(registerRequest.getGender());
        customer.setMember(member);

        customerService.saveCustomer(customer);

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), loginRequest.password()
                )
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginResponse(token);
    }
}
