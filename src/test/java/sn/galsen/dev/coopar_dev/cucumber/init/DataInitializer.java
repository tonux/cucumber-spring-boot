package sn.galsen.dev.coopar_dev.cucumber.init;

import org.springframework.stereotype.Component;
import sn.galsen.dev.coopar_dev.domain.Account;
import sn.galsen.dev.coopar_dev.repos.AccountRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {
    private final AccountRepository accountRepository;

    public DataInitializer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void initializeData() {
        accountRepository.save(new Account("779999999",500.0));
    }

    public void initializeDataList() {
        // Create a list of Account objects
        List<Account> accounts = Arrays.asList(
                new Account("770000000",500.0),
                new Account("771111111",600.0),
                new Account("772222222",700.0)
        );
        // Save the list of Account objects
        List<Account> savedAccounts = accountRepository.saveAll(accounts);
    }

    public void cleanData() {
        accountRepository.deleteAll();
    }
}
