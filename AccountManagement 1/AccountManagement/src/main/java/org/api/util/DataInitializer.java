package org.api.util;

import org.api.model.Account;
import org.api.model.Transaction;
import org.api.model.User;
import org.api.repository.AccountRepository;
import org.api.repository.AccountTransactionRepository;
import org.api.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountTransactionRepository transactionRepository;
    private final Random random = new Random();

    public DataInitializer(UserRepository userRepository, AccountRepository accountRepository, AccountTransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void initData() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
        System.out.println("Initializing real-world sample data...");

        List<User> users = new ArrayList<>();

        String[] names = {"John Doe", "Emma Smith", "Michael Johnson", "Sophia Brown", "Daniel Lee",
                "Olivia Davis", "James Wilson", "Ava Martinez", "Robert Anderson", "Emily Taylor"};

        String[] emails = {"johndoe@gmail.com", "emma.smith@yahoo.com", "michael.j@gmail.com",
                "sophia.brown@outlook.com", "daniel.lee@company.com", "olivia.davis@bank.com",
                "james.wilson@finance.com", "ava.martinez@work.com", "robert.anderson@web.com",
                "emily.taylor@domain.com"};

        for (int i = 0; i < names.length; i++) {
            User user = new User(
                    null,
                    names[i],
                    emails[i],
                    new ArrayList<>()
            );
            users.add(user);
        }
        userRepository.saveAll(users);

        List<Account> accounts = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        String[] accountTypes = {"SAVINGS", "CURRENT", "BUSINESS"};
        String[] currencies = {"USD", "EUR", "GBP", "INR", "JPY"};
        String[] transactionDescriptions = {"Online Purchase", "ATM Withdrawal", "Bank Transfer", "Bill Payment", "Salary Deposit"};

        for (User user : users) {
            List<Account> userAccounts = new ArrayList<>();

            for (int j = 1; j <= 2; j++) { // Each user gets 2 accounts
                String accountNumber = "ACC" + (100000 + random.nextInt(900000));
                String accountName = (j == 1) ? "Primary Account" : "Secondary Account";
                String accountType = accountTypes[random.nextInt(accountTypes.length)];
                String currency = currencies[random.nextInt(currencies.length)];
                double balance = 5000 + (random.nextDouble() * 20000);

                Account account = new Account(
                        null,
                        accountNumber,
                        accountName,
                        accountType,
                        LocalDate.now().minusYears(random.nextInt(10)), // Account opened up to 10 years ago
                        currency,
                        balance,
                        user,
                        new ArrayList<>()
                );
                userAccounts.add(account);
                accounts.add(account);
                List<Transaction> accountTransactions = new ArrayList<>();

                for (int k = 1; k <= 5; k++) { // Each account gets 5 transactions
                    boolean isCredit = k % 2 == 0;
                    double amount = 100 + (random.nextDouble() * 1000);
                    String description = transactionDescriptions[random.nextInt(transactionDescriptions.length)];

                    Transaction transaction = new Transaction(
                            null,
                            account.getAccountNumber(),
                            account.getAccountName(),
                            LocalDate.now().minusDays(random.nextInt(60)), // Transactions in the last 60 days
                            account.getCurrency(),
                            isCredit ? 0.0 : amount, // Debit amount
                            isCredit ? amount : 0.0, // Credit amount
                            isCredit ? "CREDIT" : "DEBIT",
                            description,
                            account
                    );
                    accountTransactions.add(transaction);
                    transactions.add(transaction);
                }

                account.setTransactions(accountTransactions);
            }
            user.setAccounts(userAccounts);
        }

        accountRepository.saveAll(accounts);
        transactionRepository.saveAll(transactions);

        System.out.println("Real-world sample data initialized successfully!");
    }
}
