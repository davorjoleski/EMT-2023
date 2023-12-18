package mk.ukim.finki.wp.eshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.eshop.convertors.UserFullNameConverter;
import mk.ukim.finki.wp.eshop.model.embeddables.UserAddress;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shop_users")
public class User {

    @Id
    private String username;

    private String password;

    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullname;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride( name = "country", column = @Column(name = "user_country")),
        @AttributeOverride( name = "city", column = @Column(name = "user_city")),
        @AttributeOverride( name = "address1", column = @Column(name = "user_address1")),
        @AttributeOverride( name = "address2", column = @Column(name = "user_address2"))
    })
    private UserAddress userAddress;

    public User(String username, String password, String name, String surname, UserAddress userAddress) {
        this.username = username;
        this.password = password;
        this.fullname = new UserFullName(name, surname);
        this.userAddress = userAddress;
    }
}
