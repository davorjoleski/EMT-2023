package mk.ukim.finki.wp.eshop.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.eshop.model.Category;
import mk.ukim.finki.wp.eshop.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = null;
    public static List<User> users = null;

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        users = new ArrayList<>();

        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));

        users.add(new User("kostadin.mishev","km","Kostadin","Mishev"));
        users.add(new User("ana.todorovska", "at", "Ana","Todorovska"));
        users.add(new User("milena.trajanovska", "mt", "Milena","Trajanovska"));
        users.add(new User("aleksandar.petrushevski", "ap", "Aleksandar","Petrushevski"));
    }
}
