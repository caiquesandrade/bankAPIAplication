package org.aplicacaobancariaapi.course.resources;

import org.aplicacaobancariaapi.course.entities.Account;
import org.aplicacaobancariaapi.course.repositories.AccountRepository;
import org.aplicacaobancariaapi.course.servicies.AccountService;
import org.aplicacaobancariaapi.course.servicies.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/accounts")
public class AccountResource {

    @Autowired
    private AccountService service;

    @Autowired
    private StatementService statementService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Account obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Account> insert(@RequestBody Account obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getAccountID()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/deposit/{id}")
    public ResponseEntity<Account> makeDeposit(@RequestBody Account obj, @PathVariable Long id) {
        obj = service.makeDeposit(obj.getAmount(), id);
        if (Objects.isNull(obj)) {
            return ResponseEntity.badRequest().body(obj);
        }
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/withdraw/{id}")
    public ResponseEntity<Account> withdraw(@RequestBody Account obj, @PathVariable Long id) {
        obj = service.makeWithdraw(obj.getAmount(), id);
        if (Objects.isNull(obj)) {
            return ResponseEntity.badRequest().body(obj);
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/transfer-money")
    public ResponseEntity<Account> transferMoney(@RequestBody Map<String, String> obj) {
        Account result = statementService.transferMoney(obj);
        if (Objects.isNull(result)) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
