package task.manager.controllers;
import org.springframework.web.bind.annotation.*;
import task.manager.models.Account;
import task.manager.services.AccountService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Account account) {
        accountService.register(account.getUsername(), account.getPassword(), account.getEmail());
        return "Registration successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody Account account) {
        accountService.login(account.getUsername(), account.getPassword());
        return "Login successful";
    }
}