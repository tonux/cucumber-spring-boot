package sn.galsen.dev.coopar_dev.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.galsen.dev.coopar_dev.domain.Account;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByPhone(String phone);
}
