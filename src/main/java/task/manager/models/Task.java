package task.manager.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<AccountTask> accountTasks = new HashSet<>();

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public Set<AccountTask> getAccountTasks() {
        return accountTasks;
    }

    public void setAccountTasks(Set<AccountTask> accountTasks) {
        this.accountTasks = accountTasks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}