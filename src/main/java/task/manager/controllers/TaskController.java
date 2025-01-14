package task.manager.controllers;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.manager.models.AccountTask;
import task.manager.services.TaskService;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<AccountTask> createTask(@RequestBody AccountTask accountTask) {
        return ResponseEntity.ok(taskService.createTask(accountTask));
    }

    @PutMapping("/{taskId}/title")
    public ResponseEntity<AccountTask> updateTaskTitle(
            @PathVariable Long taskId,
            @RequestBody String newTitle) {
        return ResponseEntity.ok(taskService.updateTaskTitle(taskId, newTitle));
    }

    @PutMapping("/{taskId}/description")
    public ResponseEntity<AccountTask> updateTaskDescription(
            @PathVariable Long taskId,
            @RequestBody String newDescription) {
        return ResponseEntity.ok(taskService.updateTaskDescription(taskId, newDescription));
    }

    @PutMapping("/{taskId}/due-date")
    public ResponseEntity<AccountTask> updateDueDate(
            @PathVariable Long taskId,
            @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        return ResponseEntity.ok(taskService.updateDueDate(taskId, dueDate));
    }

    @PutMapping("/{taskId}/toggle-important")
    public ResponseEntity<AccountTask> toggleImportant(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.toggleImportant(taskId));
    }

    @PutMapping("/{taskId}/toggle-myday")
    public ResponseEntity<AccountTask> toggleMyDay(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.toggleMyDay(taskId));
    }

    @PutMapping("/{taskId}/toggle-complete")
    public ResponseEntity<AccountTask> toggleComplete(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.toggleComplete(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<AccountTask>> getAllTasks(@PathVariable Long accountId) {
        return ResponseEntity.ok(taskService.getAllTasks(accountId));
    }

    @GetMapping("/account/{accountId}/myday")
    public ResponseEntity<List<AccountTask>> getMyDayTasks(@PathVariable Long accountId) {
        return ResponseEntity.ok(taskService.getMyDayTasks(accountId));
    }

    @GetMapping("/account/{accountId}/important")
    public ResponseEntity<List<AccountTask>> getImportantTasks(@PathVariable Long accountId) {
        return ResponseEntity.ok(taskService.getImportantTasks(accountId));
    }

    @GetMapping("/account/{accountId}/active")
    public ResponseEntity<List<AccountTask>> getActiveTasks(@PathVariable Long accountId) {
        return ResponseEntity.ok(taskService.getActiveTasks(accountId));
    }
}