package org.ostech.springimplemetation.service;

import org.ostech.springimplemetation.dto.WithdrawDTO;
import org.ostech.springimplemetation.dto.WithdrawDTOResponse;
import org.ostech.springimplemetation.entity.Account;
import org.ostech.springimplemetation.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public WithdrawDTOResponse withdraw(WithdrawDTO withdrawDTO) {
        try {
            Account account = accountRepository.findByAccountIdForUpdate(withdrawDTO.getAccountId());
            if (account.getBalance().compareTo(withdrawDTO.getAmount()) >= 0) {
                account.setBalance(account.getBalance().subtract(withdrawDTO.getAmount()));
                Account savedAccount = accountRepository.saveAndFlush(account);
                withdrawDTO.setAccountId(savedAccount.getId());
                withdrawDTO.setAmount(savedAccount.getBalance());
                return new WithdrawDTOResponse(withdrawDTO, HttpStatus.OK, "SUCCESS: Withdrawal succeed.");
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new WithdrawDTOResponse(new WithdrawDTO(), HttpStatus.INTERNAL_SERVER_ERROR, "Error: Withdrawal Failed.");
        }
        return new WithdrawDTOResponse(new WithdrawDTO(), HttpStatus.NOT_MODIFIED, "No Enough Money: Withdrawal Failed.");
    }
}
