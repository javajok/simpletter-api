package app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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
            URL icon = getClass().getResource("/default.png");
            URLConnection con = icon.openConnection();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (InputStream in = con.getInputStream()) {
                byte[] b = new byte[Math.max(con.getContentLength(), 8192)];
                int i;
                while (-1 != (i = in.read(b))) {
                    out.write(b, 0, i);
                }
            }
            account.icon = out.toByteArray();
        } catch (IOException e) {
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
