import java.util.ArrayList;

// import java.util.List;

public class Inventory {

  private ArrayList<Item> items;

  public Inventory() {
    items = new ArrayList<>();
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  public Item findItem(String sku) {
    for (Item item : items) {
      if (item.getSku().equals(sku)) {
        return item;
      }
    }
    return null;
  }

  public void display() {
    System.out.println("Inventory:");
    for (Item item : items) {
      item.display();
      System.out.println();
    }
  }
}
