package task.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.models.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByCreatedAtDesc();
}