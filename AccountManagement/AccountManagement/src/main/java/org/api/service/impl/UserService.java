package org.api.service.impl;



import lombok.extern.slf4j.Slf4j;
import org.api.dto.AccountDTO;
import org.api.dto.UserDTO;
import org.api.model.Account;
import org.api.repository.UserRepository;
import org.api.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> getUserByUserId(Long userId) {
        log.info("Getting user details...");
        return userRepository.findById(userId)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getAccounts().stream()
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

