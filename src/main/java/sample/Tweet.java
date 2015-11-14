package sample;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * ひとつのツイートを表すエンティティです。
 * 
 * @author backpaper0
 *
 */
// テーブルと対応する事を表すアノテーションです。
// このクラスはTweetテーブルに対応します。
@Entity
// クエリに名前を付けて定義しています。
// queryにはSQLに よ く 似 た JPQLというJPA専用のクエリを書きます。
// JPQLはフレームワーク(JPA)がSQLに変換してデータベースに発行されます。
//
// ここではTweetテーブルと関連するAccountテーブルを検索しています。
// 検索条件は無く、並び順はタイムスタンプの降順(つまり新しく投稿されたものが最初に来る)です。
//
// このJPQLで書かれたクエリは概ね次のようなSQLと同じものです。
//
//     SELECT t.*, u.*
//       FROM Tweet t
// INNER JOIN Account u
//         ON t.userId = u.userId
//   ORDER BY t.timestamp DESC
@NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t JOIN t.user u ORDER BY t.timestamp DESC")
public class Tweet implements Serializable {

    /**
     * ツイートID
     */
    // @Idは主キーである事を表すアノテーションです。
    @Id
    public UUID id;

    /**
     * ツイートの内容
     */
    // textのデータ型を明示するため@Columnを使用しています。
    @Column(columnDefinition = "nvarchar(140)")
    public String text;

    /**
     * ツイートをした日時
     */
    // 現在のJPAではまだJava 8で導入されたLocalDateTimeに対応していません。
    // ですのでLocalDateTimeとTimestampを相互に変換するための補助クラスを
    // 用意して、その補助クラスを使う事を@Converterで明示しています。
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime timestamp;

    /**
     * ツイートをした人
     */
    // @ManyToOneはテーブル間の関連を表すアノテーションです。
    // TweetとAccountは n対1 の関係ですので@ManyToOneを書いています。
    //
    // 他に 1対nを表す@OneToMany、1対1 を表す@OneToOne、n対nを表す@ManyToMany
    // といったアノテーションもあります。
    @ManyToOne
    public Account user;
}
