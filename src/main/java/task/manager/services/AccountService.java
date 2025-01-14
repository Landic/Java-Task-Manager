package task.manager.services;
import task.manager.models.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import task.manager.repositories.AccountRepository;
import java.util.Optional;

@Service public class AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(String username, String password, String email) {
        if (accountRepository.findByUsername(username).isPresent())  throw new RuntimeException("Username is already taken");
        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
    }
    public void login(String username, String password) {
        Optional<Account> accountOpt = accountRepository.findByUsername(username);
        if (accountOpt.isEmpty() || !passwordEncoder.matches(password, accountOpt.get().getPassword()))
            throw new RuntimeException("Invalid username or password");
        accountOpt.get();
    }
}