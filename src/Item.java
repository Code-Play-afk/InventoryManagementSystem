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

  protected String getSku() {
    return sku;
  }

  protected String getName() {
    return name;
  }

  protected void setName(String name) {
    this.name = name;
  }

  protected String getCategory() {
    return category;
  }

  protected void setCategory(String category) {
    this.category = category;
  }

  protected int getQuantity() {
    return quantity;
  }

  protected void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  protected double getPrice() {
    return price;
  }

  protected void setPrice(double price) {
    this.price = price;
  }

  protected void display() {
    System.out.println("SKU: " + sku);
    System.out.println("Name: " + name);
    System.out.println("Category: " + category);
    System.out.println("Quantity: " + quantity);
    System.out.println("Price: $" + price);
  }
}
