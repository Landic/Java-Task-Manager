import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        accountService.register(username, password);
        return "Registration successful";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        accountService.login(username, password);
        return "Login successful";
    }
}
