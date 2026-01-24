package org.ostech.springimplemetation.repository;

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.ostech.springimplemetation.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = "select a from Account a where a.id=:accountId")
    Account findByAccountIdForUpdate(Long accountId);
}
