package app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    @Transactional
    public void signUp(String userId) {
        Account account = new Account();
        account.userId = userId;
        try {
            account.icon = Files.readAllBytes(
                    Paths.get(getClass().getResource("/default.png").toURI()));
        } catch (IOException e) {
        } catch (URISyntaxException e) {
        }
        repository.create(account);
    }

    public Account find(String userId) {
        return repository.find(userId);
    }

    @Transactional
    public void update(String userId, byte[] icon) {
        Account account = repository.find(userId);
        account.icon = icon;
    }
}
