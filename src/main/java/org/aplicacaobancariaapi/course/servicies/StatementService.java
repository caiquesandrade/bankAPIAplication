package org.aplicacaobancariaapi.course.servicies;

import org.aplicacaobancariaapi.course.entities.Account;
import org.aplicacaobancariaapi.course.entities.constants.CoreConstants;
import org.aplicacaobancariaapi.course.entities.Statement;
import org.aplicacaobancariaapi.course.repositories.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    private StatementRepository repository;

    @Autowired
    private AccountService accountService;

    public List<Statement> findAll() {
        return repository.findAll();
    }

    public Statement findById(Long id) {
        Optional<Statement> obj = repository.findById(id);
        return obj.get();
    }

    public Account transferMoney(Map<String, String> obj) {
        Long originAccountNumber = Long.valueOf(obj.get("originAccount"));
        Long destinationAccountNumber = Long.valueOf(obj.get("destinationAccount"));

        Double valueToTransfer = Double.valueOf(obj.get("totalValue"));
        Account originAccount = accountService.findById(originAccountNumber);

        if (checkTransferencePeriodAllowed()) {
            if (valueToTransfer > originAccount.getAccountTransferLimit()) {
                System.out.printf("You value to transfer %.2f is greater than your account limit to transfer %s ", valueToTransfer, originAccount.getAccountTransferLimit());
                return null;
            } else {
                doTransferMoney(originAccountNumber, destinationAccountNumber, valueToTransfer);
            }
        } else {
            System.out.print("Ops, this time period not allowed transference, try later.");
            return null;
        }
        return originAccount;
    }

    private void doTransferMoney(Long originAccountNumber, Long destinationAccountNumber, Double valueToTransfer) {
        try {
            accountService.makeWithdraw(valueToTransfer, originAccountNumber);
            accountService.makeDeposit(valueToTransfer, destinationAccountNumber);
        } catch (Exception e) {
//            "Sorry, you can't transfer the money! Try again."
        }

    }

    private static boolean checkTransferencePeriodAllowed() {
        LocalTime maxLimitReferenceTime = LocalTime.of(CoreConstants.MAX_LIMIT_HOUR, CoreConstants.MAX_LIMIT_MINUTE);
        LocalTime minLimitReferenceTime = LocalTime.of(CoreConstants.MIN_LIMIT_HOUR, CoreConstants.MIN_LIMIT_MINUTE);
        LocalTime nowTime = LocalTime.now();
        return nowTime.isAfter(minLimitReferenceTime) && nowTime.isBefore(maxLimitReferenceTime);
    }
}
