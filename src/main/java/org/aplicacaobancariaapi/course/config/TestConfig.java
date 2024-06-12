package org.aplicacaobancariaapi.course.config;

import org.aplicacaobancariaapi.course.entities.Account;
import org.aplicacaobancariaapi.course.entities.Statement;
import org.aplicacaobancariaapi.course.entities.enums.TransactionType;
import org.aplicacaobancariaapi.course.repositories.AccountRepository;
import org.aplicacaobancariaapi.course.repositories.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Override
    public void run(String... args) throws Exception {
        Account a1 = new Account(null , "Caique", 1234, 230.00, 321, 2222, 1000);
        Account a2 = new Account(null , "Caio", 1212, 5000.00, 333, 1111, 1000);

        accountRepository.saveAll(Arrays.asList(a1, a2));

        Statement s1 = new Statement(null, 200.40, a1, a2, TransactionType.TRANSFERENCE, Instant.now());
        Statement s2 = new Statement(null, 3839.42, a2, null, TransactionType.DEPOSIT, Instant.now());
        Statement s3 = new Statement(null, 200.00, a2, null, TransactionType.WITHDRAW, Instant.now());

        a1.getStatements().add(s1);
        a2.getStatements().add(s2);
        a2.getStatements().add(s3);

        accountRepository.saveAll(Arrays.asList(a1,a2));

    }
}
