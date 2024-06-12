package org.aplicacaobancariaapi.course.repositories;

import org.aplicacaobancariaapi.course.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
