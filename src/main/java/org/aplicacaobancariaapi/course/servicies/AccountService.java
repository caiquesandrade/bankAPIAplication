package org.aplicacaobancariaapi.course.servicies;

import org.aplicacaobancariaapi.course.entities.Account;
import org.aplicacaobancariaapi.course.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findById(Long id) {
        Optional<Account> obj = repository.findById(id);
        return obj.get();
    }

    public Account insert(Account obj) {
        return repository.save(obj);
    }

    public Account makeDeposit(Double amountToDeposit, Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            Double amountTotal = account.get().getAmount() + amountToDeposit;
            account.get().setAmount(amountTotal);
            return repository.save(account.get());
        }
        return null;
    }

    public Account makeWithdraw(Double amountToWithdraw, Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            Double amountTotal = account.get().getAmount() - amountToWithdraw;
            account.get().setAmount(amountTotal);
            return repository.save(account.get());
        }
        return null;
    }
}
