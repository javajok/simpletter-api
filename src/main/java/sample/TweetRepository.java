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

        // データベースに対してSELECT文を発行します。
        //
        // createNamedQueryメソッドの第1引数に"Tweet.findAll"を渡していますが、
        // これは"Tweet.findAll"と名付けられたクエリを使用するよう指定しています。
        // 第2引数にはTweet.classを渡していますが、これはSELECTした結果を
        // Tweetクラスのインスタンスに変換して受け取るよう指定しています。
        //
        // 最後にgetResultListメソッドを呼んでいます。
        // これはSELECTして得られた複数行のレコードをTweetのリストとして受け取る
        // 事ができます。
        return em.createNamedQuery("Tweet.findAll", Tweet.class)
                .getResultList();
    }

    /**
     * ツイートを保存します。
     * 
     * @param tweet 保存されるツイート
     */
    public void create(Tweet tweet) {

        // データベースに対してINSERT文を発行します。
        // persistメソッドは渡されたインスタンスのクラス定義に書かれた
        // アノテーションなどからテーブルに関する情報を集めて自動的に
        // INSERT文を構築します。
        em.persist(tweet);
    }

    /**
     * 指定されたIDを持つツイートを検索して返します。
     * 
     * @param id
     * @return 抽出されたツイート。検索にヒットしなかった場合はnullが返る。
     */
    public Tweet find(UUID id) {

        // データベースに対して主キーを条件にしたSELECT文を発行します。
        // Tweetと対応するテーブルの主キーが何であるかはTweetクラスに
        // 書かれた@Idというアノテーションで設定しています。
        return em.find(Tweet.class, id);
    }

    /**
     * 指定されたツイートを削除します。
     * 
     * @param tweet 削除対象のツイート
     */
    public void remove(Tweet tweet) {

        // データベースに対してDELETE文を発行します。
        // INSERT文と同じくDELETE文も自動的に構築されます。
        // ※INSERT文に関する説明はこのクラスのcreateメソッドを参照してください。
        em.remove(tweet);
    }
}
