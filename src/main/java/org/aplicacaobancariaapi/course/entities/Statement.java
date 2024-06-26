package org.aplicacaobancariaapi.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.aplicacaobancariaapi.course.entities.enums.TransactionType;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Statement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statementId;
    private Double totalValue;
    private Integer transactionType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant instant;

    @JsonIgnore
    @ManyToOne
    private Account  originAccount;

    @JsonIgnore
    @ManyToOne
    private Account destinationAccount;
    public Statement() {

    }

    public Statement(Long id, Double totalValue, Account originAccount, Account destinationAccount, TransactionType transactionType, Instant instant) {
        this.statementId = id;
        this.totalValue = totalValue;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        setTransactionType(transactionType);
        this.instant = instant;
    }

    public Long getId() {
        return statementId;
    }

    public void setId(Long id) {
        this.statementId = id;
    }
    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public TransactionType getTransactionType() {
        return TransactionType.valueOf(transactionType);
    }

    public void setTransactionType(TransactionType transactionType) {
        if (transactionType != null){
            this.transactionType = transactionType.getCode();
        }
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return Objects.equals(statementId, statement.statementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statementId);
    }
}
