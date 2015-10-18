package app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t JOIN t.user u")
public class Tweet implements Serializable {

    @Id
    public UUID id;
    public String text;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime timestamp;
    @ManyToOne
    public Account user;
}
