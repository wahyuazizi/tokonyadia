package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByName(String name);
}
