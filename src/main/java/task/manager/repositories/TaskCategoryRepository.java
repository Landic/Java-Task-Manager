package task.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.models.TaskCategory;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    TaskCategory findByName(String name);
}