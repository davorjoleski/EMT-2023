package mk.ukim.finki.wp.eshop.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.eshop.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users = null;
    public static List<Category> categories = null;
    public static List<Manufacturer> manufacturers = null;
    public static List<Product> products = null;
    public static List<ShoppingCart> shoppingCarts = null;


    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();
        products = new ArrayList<>();
        shoppingCarts = new ArrayList<>();

        users.add(new User("kostadin.mishev", "km", "Kostadin", "Mishev"));
        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska"));
        users.add(new User("milena.trajanovska", "mt", "Milena", "Trajanovska"));
        users.add(new User("aleksandar.petrushevski", "ap", "Aleksandar", "Petrushevski"));

        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Sports", "Sports category"));
        categories.add(new Category("Food", "Food category"));

        manufacturers.add(new Manufacturer("Nike", "USA"));
        manufacturers.add(new Manufacturer("Coca Cola", "USA"));
        manufacturers.add(new Manufacturer("Literatura", "MK"));
    }
}
