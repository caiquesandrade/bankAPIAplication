package org.aplicacaobancariaapi.course.repositories;

import org.aplicacaobancariaapi.course.entities.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
}
