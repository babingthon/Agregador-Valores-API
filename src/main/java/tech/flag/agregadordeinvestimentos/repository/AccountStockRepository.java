package tech.flag.agregadordeinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.flag.agregadordeinvestimentos.entity.Account;
import tech.flag.agregadordeinvestimentos.entity.AccountStock;
import tech.flag.agregadordeinvestimentos.entity.AccountStockId;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
