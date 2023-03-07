// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

  private static Scanner scanner = new Scanner(System.in);
  private static String username = "user";
  private static String password = "pass";

  private static ArrayList<Item> inventory = new ArrayList<Item>();

  public static void main(String[] args) {
    System.out.println("");
    controlProgram();
    scanner.close();
    System.out.println("");
  }

  static void controlProgram() {
    boolean passedUserValidation = false;
    do {
      System.out.println("Enter username: ");
      String username = scanner.nextLine();
      System.out.println("Enter password: ");
      String password = scanner.nextLine();
      System.out.println("");
      passedUserValidation = validateLogin(username, password);
      if (passedUserValidation) {
        // scanner.close();
        break;
      }
    } while (!passedUserValidation);
    boolean quit = false;
    while (!quit) {
      printMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume the newline character
      switch (choice) {
        case 1:
          addItem();
          break;
        case 2:
          removeItem();
          break;
        case 3:
          updateItem();
          break;
        case 4:
          printInventory();
          break;
        case 5:
          quit = true;
          break;
        default:
          System.out.println("Invalid choice.");
          break;
      }
    }
  }

  private static boolean validateLogin(
    String inputUsername,
    String inputPassword
  ) {
    if (username.equals(inputUsername) && password.equals(inputPassword)) {
      System.out.println("Login Successful!!!\n");
      return true;
    } else {
      System.out.println("Username or Password does not match!!!\n");
      return false;
    }
  }

  private static void printMenu() {
    System.out.println("\nInventory Management System");
    System.out.println("---------------------------------------------------");
    System.out.println("1. Add item");
    System.out.println("2. Remove item");
    System.out.println("3. Update item");
    System.out.println("4. Print inventory");
    System.out.println("5. Quit");
    System.out.print("Enter choice: ");
  }

  private static void addItem() {
    System.out.print("Enter SKU: ");
    String sku = scanner.nextLine();
    System.out.print("Enter item name: ");
    String name = scanner.nextLine();
    System.out.print("Enter item category: ");
    String category = scanner.nextLine();
    System.out.print("Enter item quantity: ");
    int quantity = scanner.nextInt();
    System.out.print("Enter item price: ");
    double price = scanner.nextDouble();
    scanner.nextLine();

    Item item = new Item(sku, name, category, quantity, price);
    inventory.add(item);
    System.out.println("\nItem added to inventory.");
  }

  private static void removeItem() {
    printInventory();
    System.out.print("Enter SKU of item to remove: ");
    String sku = scanner.nextLine();

    boolean itemRemoved = false;
    for (Item item : inventory) {
      if (item.getSku().equals(sku)) {
        inventory.remove(item);
        itemRemoved = true;
        break;
      }
    }

    if (itemRemoved) {
      System.out.println("\nItem removed from inventory.");
    } else {
      System.out.println("\nItem not found in inventory.");
    }
  }

  private static void updateItem() {
    printInventory();
    System.out.print("Enter SKU of item to update: ");
    String sku = scanner.nextLine();

    boolean itemUpdated = false;
    for (Item item : inventory) {
      if (item.getSku().equals(sku)) {
        System.out.print("Enter new item name (leave blank if no change): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
          item.setName(name);
        }

        System.out.print(
          "Enter new item category (leave blank if no change): "
        );
        String category = scanner.nextLine();
        if (!category.isEmpty()) {
          item.setCategory(category);
        }

        System.out.print("Enter new item price (leave blank if no change): ");
        String priceString = scanner.nextLine();
        if (!priceString.isEmpty()) {
          double price = Double.parseDouble(priceString);
          item.setPrice(price);
        }

        itemUpdated = true;
        System.out.println("\nItem updated.");
        break;
      }
    }

    if (!itemUpdated) {
      System.out.println("\nItem not found in inventory.");
    }
  }

  private static void printInventory() {
    System.out.println("\nCurrent Inventory:");
    System.out.printf(
      "%-10s%-20s%-15s%-10s%-15s\n",
      "SKU",
      "Name",
      "Category",
      "Price",
      "Quantity"
    );
    System.out.println(
      "------------------------------------------------------------------"
    );
    for (Item item : inventory) {
      System.out.printf(
        "%-10s%-20s%-15s%-10.2f%-15d\n",
        item.getSku(),
        item.getName(),
        item.getCategory(),
        item.getPrice(),
        item.getQuantity()
      );
    }
  }
}
