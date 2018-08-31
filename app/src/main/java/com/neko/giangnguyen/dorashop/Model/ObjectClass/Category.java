package com.neko.giangnguyen.dorashop.Model.ObjectClass;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private String slug;
    private String description;
    private Boolean isHadChild;
    private List<Category> subCategories;

    public Category(int id, String name, String slug, String description, Boolean isHadChild) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.isHadChild = isHadChild;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHadChild() {
        return isHadChild;
    }

    public void setHadChild(Boolean hadChild) {
        isHadChild = hadChild;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", isHadChild=" + isHadChild +
                '}';
    }
}
