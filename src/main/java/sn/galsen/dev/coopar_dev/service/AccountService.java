package sn.galsen.dev.coopar_dev.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sn.galsen.dev.coopar_dev.domain.Account;
import sn.galsen.dev.coopar_dev.model.AccountDTO;
import sn.galsen.dev.coopar_dev.repos.AccountRepository;
import sn.galsen.dev.coopar_dev.util.NotFoundException;


@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> findAll() {
        final List<Account> accounts = accountRepository.findAll(Sort.by("id"));
        return accounts.stream()
                .map(account -> mapToDTO(account, new AccountDTO()))
                .toList();
    }

    public AccountDTO get(final Long id) {
        return accountRepository.findById(id)
                .map(account -> mapToDTO(account, new AccountDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AccountDTO accountDTO) {
        final Account account = new Account();
        mapToEntity(accountDTO, account);
        return accountRepository.save(account).getId();
    }

    public void update(final Long id, final AccountDTO accountDTO) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(accountDTO, account);
        accountRepository.save(account);
    }

    public void delete(final Long id) {
        accountRepository.deleteById(id);
    }

    private AccountDTO mapToDTO(final Account account, final AccountDTO accountDTO) {
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setPhone(account.getPhone());
        return accountDTO;
    }

    private Account mapToEntity(final AccountDTO accountDTO, final Account account) {
        account.setBalance(accountDTO.getBalance());
        account.setPhone(accountDTO.getPhone());
        return account;
    }

    public AccountDTO getByPhone(String phone) {
        return accountRepository.findByPhone(phone)
                .map(account -> mapToDTO(account, new AccountDTO()))
                .orElseThrow(NotFoundException::new);
    }
}
