package ru.vk.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vk.internship.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
