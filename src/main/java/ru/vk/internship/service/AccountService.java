package ru.vk.internship.service;

import ru.vk.internship.model.Account;

import java.util.ArrayList;

public interface AccountService {
    Account getAccountById(Long id);
    ArrayList<Account> getAllAccounts();
    Account createAccount(Account account);
    Account registerAccount(String username, String password);
    Account promoteAccount(Long id, String roleName);
    Account demoteAccount(Long id, String roleName);
    void deleteAccount(Long id);
}
