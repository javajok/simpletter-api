package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    EntityManager em;

    public void create(Account account) {
        em.persist(account);
    }

    public Account find(String userId) {
        return em.find(Account.class, userId);
    }

    public List<Account> findAll() {
        return em.createNamedQuery("Account.findAll", Account.class).getResultList();
    }
}
