package task.manager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_task")
public class AccountTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column
    private boolean isMyDay;

    @Column
    private boolean isImportant;

    @Column
    private boolean isCompleted;

    @Column
    private LocalDateTime assignedDate;

    @Column
    private LocalDateTime completedDate;

    public AccountTask() {
        this.assignedDate = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isMyDay() {
        return isMyDay;
    }

    public void setMyDay(boolean myDay) {
        isMyDay = myDay;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        if (completed) {
            this.completedDate = LocalDateTime.now();
        } else {
            this.completedDate = null;
        }
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }
}