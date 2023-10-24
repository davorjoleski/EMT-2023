package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.Category;
import mk.ukim.finki.wp.eshop.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);
        return this.categoryRepository.save(category);
    }

    @Override
    public void delete(String name) {
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
