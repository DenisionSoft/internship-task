package ru.vk.internship.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vk.internship.model.Account;
import ru.vk.internship.model.Role;
import ru.vk.internship.model.exception.AccountManagementException;
import ru.vk.internship.model.exception.RoleManagementException;
import ru.vk.internship.repository.AccountRepository;
import ru.vk.internship.repository.RoleRepository;
import ru.vk.internship.service.AccountService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Account getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null)
            throw new AccountManagementException("Account not found");
        return account;
    }

    @Override
    public ArrayList<Account> getAllAccounts() {
        return new ArrayList<>(accountRepository.findAll());
    }

    @Override
    public Account createAccount(Account account) {
        if (accountRepository.findByUsername(account.getUsername()).isPresent())
            throw new AccountManagementException("Account already exists");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account registerAccount(String username, String password) {
        if (accountRepository.findByUsername(username).isPresent())
            throw new AccountManagementException("Account already exists");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    @Override
    public Account promoteAccount(Long id, String roleName) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null)
            throw new AccountManagementException("Account not found");
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        account.getRoles().add(role);
        return accountRepository.save(account);
    }

    @Override
    public Account demoteAccount(Long id, String roleName) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null)
            throw new AccountManagementException("Account not found");
        Role role = roleRepository.findByName(roleName);
        if (role == null)
            role = account.getRoles().stream().filter(r -> r.getName().equals(roleName)).findFirst().orElse(null);
        if (role == null)
            throw new RoleManagementException("Role not found");
        account.getRoles().remove(role);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
