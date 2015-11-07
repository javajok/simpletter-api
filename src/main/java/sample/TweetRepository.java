package sample;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ツイートをデータベースに保存したり読み込むためのクラスです。
 * 
 * @author backpaper0
 *
 */
@Repository
public class TweetRepository {

    @Autowired
    EntityManager em;

    /**
     * これまで投稿されたすべてのツイートを検索して返します。
     * 
     * @return 抽出されたツイートのリスト
     */
    public List<Tweet> findAll() {
        return em.createNamedQuery("Tweet.findAll", Tweet.class)
                .getResultList();
    }

    /**
     * ツイートを保存します。
     * 
     * @param tweet 保存されるツイート
     */
    public void create(Tweet tweet) {
        em.persist(tweet);
    }

    /**
     * 指定されたIDを持つツイートを検索して返します。
     * 
     * @param id
     * @return 抽出されたツイート。検索にヒットしなかった場合はnullが返る。
     */
    public Tweet find(UUID id) {
        return em.find(Tweet.class, id);
    }

    /**
     * 指定されたツイートを削除します。
     * 
     * @param tweet 削除対象のツイート
     */
    public void remove(Tweet tweet) {
        em.remove(tweet);
    }
}
