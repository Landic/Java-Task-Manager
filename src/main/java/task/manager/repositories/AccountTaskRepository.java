package task.manager.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import task.manager.models.AccountTask;
import java.util.List;

public interface AccountTaskRepository extends JpaRepository<AccountTask, Long> {
    List<AccountTask> findByAccountId(Long accountId);
    List<AccountTask> findByAccountIdAndIsMyDayTrue(Long accountId);
    List<AccountTask> findByAccountIdAndIsImportantTrue(Long accountId);
    List<AccountTask> findByAccountIdAndIsCompletedFalse(Long accountId);

    @Query("SELECT at FROM AccountTask at WHERE at.account.id = ?1 AND at.isCompleted = false ORDER BY at.task.dueDate ASC")
    List<AccountTask> findActiveTasksByAccountId(Long accountId);
}