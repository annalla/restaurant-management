package models;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
    protected String name;
    protected String description;
    protected String image;
    protected double price;
    protected MenuType menuType;

    public MenuItem(String name, String description, String image, double price) {
        this.name = name == null ? "" : name;
        this.description = description;
        this.image = image;
        this.price = price < 0 ? 0 : price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public MenuItem(MenuItem menu) {
        this.name = menu.name == null ? "" : menu.name;
        this.description = menu.description;
        this.image = menu.description;
        this.price = menu.price < 0 ? 0 : menu.price;
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
        if (name != null && name.length() != 0) {
            this.name = name;
        }
    }

    public void setDescription(String description) {
        if (description != null && description.length() != 0) {
            this.description = description;
        }
    }

    public void setImage(String image) {
        if (image != null && image.length() != 0) {
            this.image = image;
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void update(String name, String description, String image, double price) {
        if (name != null && name.length() != 0) {
            this.name = name;
        }
        if (description != null && description.length() != 0) {
            this.description = description;
        }
        if (image != null && image.length() != 0) {
            this.image = image;
        }
        if (price >= 0) {
            this.price = price;
        }
    }

    public void updateMenu(MenuItem menu) {
        if (menu.name != null && menu.name.length() != 0) {
            this.name = menu.name;
        }
        if (menu.description != null && menu.description.length() != 0) {
            this.description = menu.description;
        }
        if (menu.image != null && menu.image.length() != 0) {
            this.image = menu.image;
        }
        if (menu.price >= 0) {
            this.price = menu.price;
        }
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

    public String getBasicInfor() {
        return name + "\t\t\t" + getMenuType() + "\t\t\t" + price;
    }

    public boolean update(String properties) {
        String[] items = properties.split("-");
        double price;
        if (items.length != 4) {
            return false;
        }
        for (int i = 0; i < items.length; i++) {
            items[i] = items[i].trim();
        }
        try {
            price = Double.parseDouble(items[items.length - 1]);
            if (price < 0) {
                return false;
            }
        } catch (RuntimeException e) {
            return false;
        }
        update(items[0], items[1], items[2], price);
        return true;
    }
}
