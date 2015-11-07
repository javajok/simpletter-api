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
@Entity
@NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t JOIN t.user u ORDER BY t.timestamp DESC")
public class Tweet implements Serializable {

    /**
     * ツイートID
     */
    @Id
    public UUID id;

    /**
     * ツイートの内容
     */
    @Column(columnDefinition = "nvarchar(140)")
    public String text;

    /**
     * ツイートをした日時
     */
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime timestamp;

    /**
     * ツイートをした人
     */
    @ManyToOne
    public Account user;
}
