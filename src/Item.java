public class Item {

  private String sku;
  private String name;
  private String category;
  private int quantity;
  private double price;

  public Item(
    String sku,
    String name,
    String category,
    int quantity,
    double price
  ) {
    this.sku = sku;
    this.name = name;
    this.category = category;
    this.quantity = quantity;
    this.price = price;
  }

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void display() {
    System.out.println("SKU: " + sku);
    System.out.println("Name: " + name);
    System.out.println("Category: " + category);
    System.out.println("Quantity: " + quantity);
    System.out.println("Price: $" + price);
  }
}
