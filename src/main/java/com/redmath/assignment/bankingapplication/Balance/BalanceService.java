package com.redmath.assignment.bankingapplication.Balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private  final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Constructor
    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public List<Balance> findAllBalances() {
        logger.debug("Fetching all balances");
        return balanceRepository.findAll();
    }

    // Find by account email
    public List<Balance> findAllByAccountEmail(String search) {
        logger.debug("Searching balances with account email containing: {}", search.replaceAll("[\r\n]", ""));

        return jdbcTemplate.query(
                "SELECT * FROM balance INNER JOIN account ON balance.account_id = account.id WHERE account.email LIKE '%' || ? || '%'",
                new Object[]{search},
                (rs, rowNum) -> {
                    Balance balance = new Balance();
                    balance.setAccount_id(rs.getLong("account_id"));
                    balance.setDate(rs.getString("date"));
                    balance.setAmount(rs.getDouble("amount"));
                    balance.setAccountType(rs.getString("accountType"));

                    return balance;
                });
    }

//    public Optional<Balance> findByAccountId(long accountId) {
//        logger.info("Balance details with account ID {} are: ", accountId);
//        return balanceRepository.findById()  //.findById(accountId);
//    }

    // Post Mapping
    public Balance create(Balance balance) {
        logger.info("Balance with account ID {} is added. ", balance.getBalance_id());
        return balanceRepository.save(balance);
    }

//    // Put Mapping
//    public Balance update(Balance newBalanceData) {
//        logger.info("Balance with account ID {} is updated. ", newBalanceData.getAccount_id());
//
//        Optional<Balance> existingBalance = balanceRepository.findById(newBalanceData.getAccount_id());
//
//        if (existingBalance.isPresent()) {
//            Balance balanceToUpdate = existingBalance.get();
//            balanceToUpdate.setDate(newBalanceData.getDate());
//            balanceToUpdate.setAmount(newBalanceData.getAmount());
//            balanceToUpdate.setAccountType(newBalanceData.getAccountType());
//
//            return balanceRepository.save(balanceToUpdate);
//        }
//
//        return null;
//    }
//
//    // Delete Mapping
//    public boolean delete(long accountId) {
//        Optional<Balance> balanceToDelete = balanceRepository.findById(accountId);
//
//        if (balanceToDelete.isPresent()) {
//            balanceRepository.delete(balanceToDelete.get());
//            return true;
//        }
//
//        return false; // Balance with given account ID not found
//    }
}
