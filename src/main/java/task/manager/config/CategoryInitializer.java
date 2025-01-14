package task.manager.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import task.manager.models.TaskCategory;
import task.manager.repositories.TaskCategoryRepository;
import java.util.Arrays;

@Component
public class CategoryInitializer implements CommandLineRunner {
    private final TaskCategoryRepository categoryRepository;

    public CategoryInitializer(TaskCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        // Проверяем, есть ли уже категории
        if (categoryRepository.count() == 0) {
            // Создаем базовые категории
            Arrays.asList(
                    createCategory("Мой день"),
                    createCategory("Все задачи"),
                    createCategory("Важное")
            ).forEach(categoryRepository::save);
        }
    }

    private TaskCategory createCategory(String name) {
        TaskCategory category = new TaskCategory();
        category.setName(name);
        return category;
    }
}