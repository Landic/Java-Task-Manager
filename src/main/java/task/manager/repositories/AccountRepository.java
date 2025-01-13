package task.manager.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.models.Account;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}