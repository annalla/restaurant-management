package models;

import java.util.Objects;

public abstract class MenuItem {
    protected String name;
    protected String description;
    protected String image;
    protected double price;

    public MenuItem(String name, String description, String image, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public MenuItem(MenuItem menu) {
        this.name = menu.name;
        this.description = menu.description;
        this.image = menu.description;
        this.price = menu.price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = (name == null || name.length() == 0) ? this.name : name;
    }

    public void setDescription(String description) {
        this.description = (description == null || name.length() == 0) ? this.description : description;
    }

    public void setImage(String image) {
        this.image = (image == null || image.length() == 0) ? this.image : image;
    }

    public void setPrice(double price) {
        this.price = price < 0 ? this.price : price;
    }

    public void update(String name, String description, String image, double price) {
        this.name = (name == null || name.length() == 0) ? this.name : name;
        this.description = (description == null || description.length() == 0) ? this.description : description;
        this.image = (image == null || image.length() == 0) ? this.image : image;
        this.price = price < 0 ? this.price : price;
    }
    public void updateMenu(MenuItem menu){
        this.name = (menu.name == null || menu.name.length() == 0) ? this.name : menu.name;
        this.description = (menu.description == null || menu.name.length() == 0) ? this.description : menu.description;
        this.image = (menu.image == null || menu.image.length() == 0) ? this.image : menu.image;
        this.price = menu.price < 0 ? this.price : menu.price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menu = (MenuItem) o;
        return Double.compare(menu.price, price) == 0 && Objects.equals(name, menu.name) && Objects.equals(description, menu.description) && Objects.equals(image, menu.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, image, price);
    }
}
