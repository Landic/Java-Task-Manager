package task.manager.controllers;

import org.springframework.web.bind.annotation.*;
import task.manager.services.AccountService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Альтернативный способ настройки CORS
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        String email = payload.get("email"); // Получаем email из запроса

        // Проверка на null для email, можно также добавить валидацию
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Вызываем сервис для регистрации с email
        accountService.register(username, password, email);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password are required");
        }

        accountService.login(username, password);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        return response;
    }
}