package com.tss.service;

import com.tss.dao.AccountDAO;
import com.tss.model.Account;
import java.util.List;

public class AccountService {
    private final AccountDAO dao = new AccountDAO();

    public void createAccount(Account acc) {
        dao.insertAccount(acc);
    }

    public List<Account> getAllAccounts() {
        return dao.getAllAccounts();
    }
    
    public boolean transferFunds(int senderId, int receiverId, double amount) {
        return dao.transferAmount(senderId, receiverId, amount);
    }

    public void closeConnection() {
        dao.close();
    }
}
