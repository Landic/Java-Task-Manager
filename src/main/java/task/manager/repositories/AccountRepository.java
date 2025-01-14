package task.manager.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import task.manager.models.Account;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    List<Account> findByIsActiveTrue();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE Account a SET a.lastLogin = ?2 WHERE a.id = ?1")
    void updateLastLogin(Long id, LocalDateTime lastLogin);

    @Modifying
    @Query("UPDATE Account a SET a.isActive = ?2 WHERE a.id = ?1")
    void updateActiveStatus(Long id, boolean isActive);
}