package mk.ukim.finki.wp.eshop.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.eshop.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = null;

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));
    }
}
