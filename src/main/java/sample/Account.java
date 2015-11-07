package sample;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * ユーザーアカウントを表すエンティティです。
 * 
 * @author backpaper0
 *
 */
@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {

    /**
     * ユーザーID
     */
    @Id
    public String userId;

    /**
     * アイコンのデータ
     */
    @Column(length = Integer.MAX_VALUE)
    public byte[] icon;
}
