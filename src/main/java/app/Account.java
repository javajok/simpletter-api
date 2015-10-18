package app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account implements Serializable {

    @Id
    public String userId;
    @Column(length = Integer.MAX_VALUE)
    public byte[] icon;
}
