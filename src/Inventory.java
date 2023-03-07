import java.util.ArrayList;

// import java.util.List;

public class Inventory {

  private ArrayList<Item> items;

  public Inventory() {
    items = new ArrayList<>();
  }

  protected void addItem(Item item) {
    items.add(item);
  }

  protected void removeItem(Item item) {
    items.remove(item);
  }

  protected Item findItem(String sku) {
    for (Item item : items) {
      if (item.getSku().equals(sku)) {
        return item;
      }
    }
    return null;
  }

  protected void display() {
    System.out.println("Inventory:");
    for (Item item : items) {
      item.display();
      System.out.println();
    }
  }
}
