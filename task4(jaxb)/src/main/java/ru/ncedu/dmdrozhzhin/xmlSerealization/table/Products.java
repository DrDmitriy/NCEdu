package ru.ncedu.dmdrozhzhin.xmlSerealization.table;

public class Products {

    private int productId;
    Manufactures manufacture;
    private String name;
    private double price;
    private String category;
    private boolean isAvailable;
    private String date;
    private String size;

    public Manufactures getManufactures() {
        return manufacture;
    }

    public void setManufactures(Manufactures manufactures) {
        this.manufacture = manufactures;
    }

    public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setisAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Products(int productId, Manufactures manufacture, String name, double price, String category, boolean isAvailable, String date, String size) {
        this.productId = productId;
        this.manufacture = manufacture;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
        this.date = date;
        this.size = size;
    }

    public Products() {
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", manufacture_id=" + manufacture +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                ", date='" + date + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
