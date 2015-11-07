package sample;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ユーザーアカウントをデータベースに保存したり読み込むためのクラスです。
 * 
 * @author backpaper0
 *
 */
@Repository
public class AccountRepository {

    @Autowired
    EntityManager em;

    /**
     * ユーザーアカウントを保存します。
     * 
     * @param account 保存されるユーザーアカウント
     */
    public void create(Account account) {
        em.persist(account);
    }

    /**
     * 指定されたユーザーIDを持つユーザーアカウントを検索して返します。
     * 
     * @param userId ユーザーID
     * @return 抽出されたユーザーアカウント。検索にヒットしなかった場合はnullが返る。
     */
    public Account find(String userId) {
        return em.find(Account.class, userId);
    }

    /**
     * 登録されているすべてのユーザーアカウントを検索して返します。
     * 
     * @return 抽出されたユーザーアカウントのリスト
     */
    public List<Account> findAll() {
        return em.createNamedQuery("Account.findAll", Account.class)
                .getResultList();
    }
}
