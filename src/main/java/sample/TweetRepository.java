package sample;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TweetRepository {

    @Autowired
    EntityManager em;

    public List<Tweet> findAll() {
        return em.createNamedQuery("Tweet.findAll", Tweet.class)
                .getResultList();
    }

    public void create(Tweet tweet) {
        em.persist(tweet);
    }

    public Tweet find(UUID id) {
        return em.find(Tweet.class, id);
    }

    public void remove(Tweet tweet) {
        em.remove(tweet);
    }
}
