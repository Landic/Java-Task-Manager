package task.manager.services;

import task.manager.models.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import task.manager.repositories.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Регистрация с учётом email
    public void register(String username, String password, String email) {
        // Проверка на уникальность username
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

        // Проверка на уникальность email
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already taken");
        }

        // Создание нового аккаунта
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setEmail(email); // Устанавливаем email
        accountRepository.save(account); // Сохраняем в базу данных
    }

    // Вход в систему
    public void login(String username, String password) {
        Optional<Account> accountOpt = accountRepository.findByUsername(username);

        // Проверка наличия аккаунта и соответствия пароля
        if (accountOpt.isEmpty() || !passwordEncoder.matches(password, accountOpt.get().getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Получаем аккаунт (если необходимо, можно вернуть его)
        accountOpt.get();
    }
}