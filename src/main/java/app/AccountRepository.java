package app;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
