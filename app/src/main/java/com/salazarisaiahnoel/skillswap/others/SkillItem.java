package com.salazarisaiahnoel.skillswap.others;

public class SkillItem {

    String title, level, description, instructor, category;
    int simpleImage, image, categoryImage;

    public SkillItem(String title, String level, String description, String instructor, String category, int simpleImage, int image, int categoryImage) {
        this.title = title;
        this.level = level;
        this.description = description;
        this.instructor = instructor;
        this.category = category;
        this.simpleImage = simpleImage;
        this.image = image;
        this.categoryImage = categoryImage;
    }

    public String getTitle() {
        return title;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getCategory() {
        return category;
    }

    public int getSimpleImage() {
        return simpleImage;
    }

    public int getImage() {
        return image;
    }

    public int getCategoryImage() {
        return categoryImage;
    }
}
