package tech.flag.agregadordeinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.flag.agregadordeinvestimentos.entity.Account;
import tech.flag.agregadordeinvestimentos.entity.BillingAddress;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
