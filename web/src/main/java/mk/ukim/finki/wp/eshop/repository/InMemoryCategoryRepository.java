package mk.ukim.finki.wp.eshop.repository;

import mk.ukim.finki.wp.eshop.bootstrap.DataHolder;
import mk.ukim.finki.wp.eshop.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {
    public List<Category> findAll() {
        return DataHolder.categories;
    }

    public Category save(Category category) {
        if (category == null || category.name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        DataHolder.categories.removeIf(c-> c.getName().equals(category.getName()));
        DataHolder.categories.add(category);
        return category;
    }

    public Optional<Category> findByName(String name) {

//        for (Category c: DataHolder.categories) {
//            if (c.getName().equals(name)) {
//                return c;
//            }
//        }
//        return null;

        return DataHolder.categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public void delete(String name) {
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }

    public List<Category> search(String text) {

        return DataHolder.categories
                .stream()
                .filter(c -> c.getName().contains(text) ||
                        c.getDescription().contains(text))
                .collect(Collectors.toList());
    }

}
