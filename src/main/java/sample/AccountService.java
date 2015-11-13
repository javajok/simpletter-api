package sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザーアカウントに関するロジックをまとめたクラスです。
 * 
 * @author backpaper0
 *
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    /**
     * 指定されたユーザーIDでユーザーアカウントを構築して保存します。
     * 
     * @param userId ユーザーID
     */
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

    /**
     * 指定されたユーザーIDを持つユーザーアカウントを返します。
     * 
     * @param userId ユーザーID
     * @return ユーザーアカウント
     */
    public Account find(String userId) {
        Account found = repository.find(userId);
        if (found == null) {
            throw new AccountNotFoundException();
        }
        return found;
    }

    /**
     * 指定されたユーザーIDを持つユーザーアカウントのアイコンを更新します。
     * 
     * @param userId ユーザーID
     * @param icon アイコンのデータ
     */
    @Transactional
    public void update(String userId, byte[] icon) {
        Account account = repository.find(userId);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        account.icon = icon;
    }

    /**
     * 登録されているすべてのユーザーアカウントを返します。
     * 
     * @return ユーザーアカウントのリスト
     */
    public List<Account> findAll() {
        return repository.findAll();
    }
}
