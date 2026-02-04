package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.Role;
import com.enigmacamp.tokonyadia.repository.RoleRepository;
import com.enigmacamp.tokonyadia.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository  roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
