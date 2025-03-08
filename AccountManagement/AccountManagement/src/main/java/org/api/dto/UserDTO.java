package org.api.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<AccountDTO> accounts;

    public UserDTO(Long id, String username, String email, List<AccountDTO> accounts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.accounts = accounts;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public List<AccountDTO> getAccounts() { return accounts; }
}

