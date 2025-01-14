package task.manager.models;

import jakarta.persistence.*;

@Entity
@Table(name = "task_category")
public class TaskCategory {
    public static final String MY_DAY = "Мой день";
    public static final String ALL_TASKS = "Все задачи";
    public static final String IMPORTANT = "Важное";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}