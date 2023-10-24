package mk.ukim.finki.wp.eshop.model;

import lombok.Data;

@Data
public class Category {
    public String name;
    public String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
