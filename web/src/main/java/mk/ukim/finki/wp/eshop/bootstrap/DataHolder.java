package mk.ukim.finki.wp.eshop.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.eshop.model.*;
import mk.ukim.finki.wp.eshop.repository.jpa.*;
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


    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    public DataHolder(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();
        products = new ArrayList<>();
        shoppingCarts = new ArrayList<>();


        if (userRepository.count() == 0) {
            users.add(new User("kostadin.mishev", "km", "Kostadin", "Mishev"));
            users.add(new User("ana.todorovska", "at", "Ana", "Todorovska"));
            users.add(new User("milena.trajanoska", "mt", "Milena", "Trajanoska"));
            users.add(new User("aleksandar.petrushevski", "ap", "Aleksandar", "Petrushevski"));
            userRepository.saveAll(users);
        }

        if (categoryRepository.count() == 0) {
            categories.add(new Category("Books", "Books category"));
            categories.add(new Category("Sports", "Sports category"));
            categories.add(new Category("Food", "Food category"));
            categoryRepository.saveAll(categories);
        }

        if (manufacturerRepository.count() == 0) {
            manufacturers.add(new Manufacturer("Nike", "USA"));
            manufacturers.add(new Manufacturer("Coca Cola", "USA"));
            manufacturers.add(new Manufacturer("Literatura", "MK"));
            manufacturerRepository.saveAll(manufacturers);
        }

    }
}
