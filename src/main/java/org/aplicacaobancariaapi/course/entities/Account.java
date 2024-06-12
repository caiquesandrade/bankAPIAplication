package org.aplicacaobancariaapi.course.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;
    private String accountName;
    private Integer iban;
    private Double amount;
    private Integer bankCode;
    private Integer checkDigits;
    private Integer accountTransferLimit;

    @OneToMany(mappedBy = "originAccount", cascade = CascadeType.ALL)
    private final Set<Statement> statements = new HashSet<>();

    public Account(Long accountID, String accountName, Integer iban, Double amount, Integer bankCode, Integer checkDigits, Integer accountTransferLimit) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.iban = iban;
        this.amount = amount;
        this.bankCode = bankCode;
        this.checkDigits = checkDigits;
        this.accountTransferLimit = accountTransferLimit;
    }

    public Account() {

    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getIban() {
        return iban;
    }

    public void setIban(Integer iban) {
        this.iban = iban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getBankCode() {
        return bankCode;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getCheckDigits() {
        return checkDigits;
    }

    public void setCheckDigits(Integer checkDigits) {
        this.checkDigits = checkDigits;
    }

    public Integer getAccountTransferLimit() {
        return accountTransferLimit;
    }

    public Set<Statement> getStatements() {
        return statements;
    }

    public void setAccountTransferLimit(Integer accountTransferLimit) {
        this.accountTransferLimit = accountTransferLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountID, account.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }
}
