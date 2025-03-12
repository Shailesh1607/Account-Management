package org.api.service;

import org.api.dto.UserDTO;

import java.util.Optional;

public interface IUserService {
    public Optional<UserDTO> getUserbyUserId(Long userId);
}

