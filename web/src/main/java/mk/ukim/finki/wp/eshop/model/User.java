package mk.ukim.finki.wp.eshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.wp.eshop.convertors.UserFullnameConverter;

import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
public class User {

    @Id
    private String username;

    private String password;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname fullname;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User() {

    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.fullname = new UserFullname(name, surname);
    }
}
