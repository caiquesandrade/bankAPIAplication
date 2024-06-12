package org.aplicacaobancariaapi.course.resources;

import org.aplicacaobancariaapi.course.entities.Account;
import org.aplicacaobancariaapi.course.entities.Statement;
import org.aplicacaobancariaapi.course.servicies.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementResource {

    @Autowired
    private StatementService statementService;

    @GetMapping
    public ResponseEntity<List<Statement>> findAll() {
        List<Statement> list = statementService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Statement> findById(@PathVariable Long id) {
        Statement obj = statementService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
