package org.api.service.impl;



import lombok.extern.slf4j.Slf4j;
import org.api.dto.AccountDTO;
import org.api.dto.UserDTO;
import org.api.exception.NoDataFoundException;
import org.api.model.Account;
import org.api.model.User;
import org.api.repository.UserRepository;
import org.api.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> getUserbyUserId(Long userId) {
        log.info("Getting user details...");
        return userRepository.findById(userId)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        Optional.ofNullable(user.getAccounts())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList())
                ));
    }

    private AccountDTO convertToDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountName(),
                account.getAccountType(),
                account.getBalanceDate(),
                account.getCurrency(),
                account.getOpeningAvailableBalance()
        );
    }
}

