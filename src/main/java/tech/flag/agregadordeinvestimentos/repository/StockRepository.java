package tech.flag.agregadordeinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.flag.agregadordeinvestimentos.entity.Account;
import tech.flag.agregadordeinvestimentos.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
