package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.LoginRequest;
import com.enigmacamp.tokonyadia.dto.request.RegisterRequest;
import com.enigmacamp.tokonyadia.dto.response.LoginResponse;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.entity.RefreshToken;
import com.enigmacamp.tokonyadia.entity.Role;
import com.enigmacamp.tokonyadia.security.jwt.JwtUtil;
import com.enigmacamp.tokonyadia.service.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final MemberService memberService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;
    private final RefreshTokenService refreshTokenService;

    public AuthServiceImpl(MemberService memberService, CustomerService customerService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, RoleService roleService, RefreshTokenService refreshTokenService) {
        this.memberService = memberService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if(memberService.existsByUsername(registerRequest.getUsername())){
            throw new IllegalStateException("Username is already in use");
        }

        Role userRole = roleService.findRoleByName("USER")
                .orElseThrow(()-> new RuntimeException("Role not found"));

        Member member = new Member();
        member.setUsername(registerRequest.getUsername());
        member.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        member.setRoles(Set.of(userRole));
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

//        Akse token jwt
        String accessToken = jwtUtil.generateToken(authentication);

        Optional<Member> member = memberService.findByUsername(loginRequest.username());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(member.get().getId());

//
//        String token = jwtUtil.generateToken(authentication);
        return new LoginResponse(
                accessToken,
                refreshToken.getToken()
        );
    }
}
