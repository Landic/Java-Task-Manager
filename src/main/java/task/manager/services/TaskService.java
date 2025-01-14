package task.manager.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.manager.models.*;
import task.manager.repositories.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final AccountTaskRepository accountTaskRepository;
    private final TaskCategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository,
                       AccountRepository accountRepository,
                       AccountTaskRepository accountTaskRepository,
                       TaskCategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.accountTaskRepository = accountTaskRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public AccountTask createTask(AccountTask accountTask) {
        Account account = accountRepository.findById(accountTask.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Аккаунт не найден"));

        TaskCategory category = null;
        if (accountTask.getTask().getCategory() != null) {
            category = categoryRepository.findById(accountTask.getTask().getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Категория не найдена"));
        }

        Task task = accountTask.getTask();
        task.setCategory(category);
        task = taskRepository.save(task);

        accountTask.setAccount(account);
        accountTask.setTask(task);
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public AccountTask updateTaskTitle(Long accountTaskId, String newTitle) {
        AccountTask accountTask = accountTaskRepository.findById(accountTaskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        accountTask.getTask().setTitle(newTitle);
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public AccountTask updateTaskDescription(Long accountTaskId, String newDescription) {
        AccountTask accountTask = accountTaskRepository.findById(accountTaskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        accountTask.getTask().setDescription(newDescription);
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public AccountTask updateDueDate(Long accountTaskId, LocalDateTime dueDate) {
        AccountTask accountTask = accountTaskRepository.findById(accountTaskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        accountTask.getTask().setDueDate(dueDate);
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public AccountTask toggleImportant(Long accountTaskId) {
        AccountTask accountTask = accountTaskRepository.findById(accountTaskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        accountTask.setImportant(!accountTask.isImportant());
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public AccountTask toggleMyDay(Long accountTaskId) {
        AccountTask accountTask = accountTaskRepository.findById(accountTaskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        accountTask.setMyDay(!accountTask.isMyDay());
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public AccountTask toggleComplete(Long accountTaskId) {
        AccountTask accountTask = accountTaskRepository.findById(accountTaskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        accountTask.setCompleted(!accountTask.isCompleted());
        return accountTaskRepository.save(accountTask);
    }

    @Transactional
    public void deleteTask(Long accountTaskId) {
        if (!accountTaskRepository.existsById(accountTaskId)) {
            throw new RuntimeException("Задача не найдена для удаления");
        }
        accountTaskRepository.deleteById(accountTaskId);
    }

    public List<AccountTask> getAllTasks(Long accountId) {
        return accountTaskRepository.findByAccountId(accountId);
    }

    public List<AccountTask> getMyDayTasks(Long accountId) {
        return accountTaskRepository.findByAccountIdAndIsMyDayTrue(accountId);
    }

    public List<AccountTask> getImportantTasks(Long accountId) {
        return accountTaskRepository.findByAccountIdAndIsImportantTrue(accountId);
    }

    public List<AccountTask> getActiveTasks(Long accountId) {
        return accountTaskRepository.findActiveTasksByAccountId(accountId);
    }
}