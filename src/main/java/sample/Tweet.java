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

@Entity
@NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t JOIN t.user u ORDER BY t.timestamp DESC")
public class Tweet implements Serializable {

    @Id
    public UUID id;
    @Column(columnDefinition = "nvarchar(140)")
    public String text;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime timestamp;
    @ManyToOne
    public Account user;
}
