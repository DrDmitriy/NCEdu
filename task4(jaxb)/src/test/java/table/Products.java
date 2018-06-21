package table;

public class Products {

    private int product_id;
    Manufactures manufacture;
    private String name;
    private double price;
    private String category;
    private boolean is_available;
    private String date;
    private String size;

    public Manufactures getManufactures() {
        return manufacture;
    }

    public void setManufactures(Manufactures manufactures) {
        this.manufacture = manufactures;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
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

    public Products(int product_id, Manufactures manufacture, String name, double price, String category, boolean is_available, String date, String size) {
        this.product_id = product_id;
        this.manufacture = manufacture;
        this.name = name;
        this.price = price;
        this.category = category;
        this.is_available = is_available;
        this.date = date;
        this.size = size;
    }

    public Products() {
    }

    @Override
    public String toString() {
        return "Products{" +
                "product_id=" + product_id +
                ", manufacture_id=" + manufacture +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", is_available=" + is_available +
                ", date='" + date + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
