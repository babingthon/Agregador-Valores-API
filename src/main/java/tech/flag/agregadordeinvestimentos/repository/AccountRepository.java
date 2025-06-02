package tech.flag.agregadordeinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.flag.agregadordeinvestimentos.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
