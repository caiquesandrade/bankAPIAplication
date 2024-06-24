package org.aplicacaobancariaapi.course.servicies;

import jakarta.persistence.EntityNotFoundException;
import org.aplicacaobancariaapi.course.entities.Account;
import org.aplicacaobancariaapi.course.entities.Statement;
import org.aplicacaobancariaapi.course.entities.enums.TransactionType;
import org.aplicacaobancariaapi.course.repositories.AccountRepository;
import org.aplicacaobancariaapi.course.repositories.StatementRepository;
import org.aplicacaobancariaapi.course.servicies.exceptions.DatabaseException;
import org.aplicacaobancariaapi.course.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private StatementRepository statementRepository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findById(Long id) {
        Optional<Account> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Account insert(Account obj) {
        return repository.save(obj);
    }

    public Account makeDeposit(Double amountToDeposit, Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            Double amountTotal = account.get().getAmount() + amountToDeposit;
            account.get().setAmount(amountTotal);

            Statement statement = new Statement(null, amountToDeposit, account.get(), null, TransactionType.DEPOSIT, Instant.now());
            statementRepository.save(statement);
            account.get().getStatements().add(statement);
            return repository.save(account.get());
        }
        return null;
    }

    public Account makeWithdraw(Double amountToWithdraw, Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            Double amountTotal = account.get().getAmount() - amountToWithdraw;
            account.get().setAmount(amountTotal);

            Statement statement = new Statement(null, amountToWithdraw, account.get(), null, TransactionType.WITHDRAW, Instant.now());
            statementRepository.save(statement);
            account.get().getStatements().add(statement);
            return repository.save(account.get());
        }
        return null;
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Account update (Long id, Account obj) {
        try {
            Account entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Account entity, Account obj) {
        entity.setAccountName(obj.getAccountName());
        entity.setAccountTransferLimit(obj.getAccountTransferLimit());
    }
}
