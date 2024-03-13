package ru.vk.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.internship.audit.RecordAudit;
import ru.vk.internship.model.Account;
import ru.vk.internship.service.AccountService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController extends BaseController {

    private final AccountService accountService;

    @RecordAudit
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @RecordAudit
    @GetMapping
    public ResponseEntity<ArrayList<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @RecordAudit
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @RecordAudit
    @RequestMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestParam(name = "username", required = true) String username, @RequestParam(name = "password", required = true) String password) {
        return ResponseEntity.ok(accountService.registerAccount(username, password));
    }

    @RecordAudit
    @PatchMapping("/{id}/promote")
    public ResponseEntity<Account> promoteAccount(@PathVariable Long id, @RequestParam(name = "role", required = true) String roleName) {
        return ResponseEntity.ok(accountService.promoteAccount(id, roleName));
    }

    @RecordAudit
    @PatchMapping("/{id}/demote")
    public ResponseEntity<Account> demoteAccount(@PathVariable Long id, @RequestParam(name = "role", required = true) String roleName) {
        return ResponseEntity.ok(accountService.demoteAccount(id, roleName));
    }

    @RecordAudit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

}
