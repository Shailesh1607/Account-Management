package org.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    public User() {}

    public User(String username, String email, List<Account> accounts) {
        this.username = username;
        this.email = email;
        this.accounts = accounts;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public List<Account> getAccounts() { return accounts; }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
