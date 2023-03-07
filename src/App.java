import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

  // ! ||--------------------------------------------------------------------------------||
  // ! ||                                      M                                         ||
  // ! ||--------------------------------------------------------------------------------||

  private static Scanner scanner = new Scanner(System.in);
  private static String username = "user";
  private static String password = "pass";
  private static ArrayList<Item> inventory = new ArrayList<Item>();
  private static String csvFilePath = "src\\stock.csv";

  private static boolean validateLogin(
    String inputUsername,
    String inputPassword
  ) {
    if (username.equals(inputUsername) && password.equals(inputPassword)) {
      System.out.println("Login Successful!!!");
      return true;
    } else {
      System.out.println("Username or Password does not match!!!\n");
      return false;
    }
  }

  private static void addItem() throws IOException {
    System.out.println("\nAdd new item");
    System.out.println("------------");
    System.out.print("Enter SKU: ");
    String sku = scanner.nextLine();
    System.out.print("Enter item name: ");
    String name = scanner.nextLine();
    System.out.print("Enter item category: ");
    String category = scanner.nextLine();
    System.out.print("Enter item price: ");
    double price = scanner.nextDouble();
    scanner.nextLine();
    System.out.print("Enter item quantity: ");
    int quantity = scanner.nextInt();

    Item item = new Item(sku, name, category, quantity, price);
    inventory.add(item);
    System.out.println("Item added to inventory.");
    writeStockToCSV(inventory, csvFilePath);
    printInventory();
  }

  private static void removeItem() throws IOException {
    printInventory();
    System.out.print("Enter SKU of item to remove: ");
    String sku = scanner.nextLine();

    boolean itemRemoved = false;
    for (Item item : inventory) {
      if (item.getSku().equals(sku)) {
        inventory.remove(item);
        itemRemoved = true;
        writeStockToCSV(inventory, csvFilePath);
        break;
      }
    }

    if (itemRemoved) {
      System.out.println("\nItem removed from inventory.");
      printInventory();
    } else {
      System.out.println("\nItem not found in inventory.");
    }
  }

  private static void updateItem() throws IOException {
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
        System.out.print("Enter new item quantity(leave blank if no change): ");
        String quantityString = scanner.nextLine();
        if (!quantityString.isEmpty()) {
          int quantity = Integer.parseInt(quantityString);
          item.setQuantity(quantity);
        }
        itemUpdated = true;
        break;
      }
    }

    if (itemUpdated) {
      writeStockToCSV(inventory, csvFilePath);
      System.out.println("\nItem updated.");
      printInventory();
    } else {
      System.out.println("\nItem not found in inventory.");
    }
  }

  private static void writeStockToCSV(
    ArrayList<Item> inventory,
    String filename
  ) throws IOException {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      // String[] header = { "SKU", "Name", "Category", "Price", "Quantity" };
      // writer.writeNext(header);
      for (Item item : inventory) {
        writer.write(
          item.getSku() +
          "," +
          item.getName() +
          "," +
          item.getCategory() +
          "," +
          Double.toString(item.getPrice()) +
          "," +
          Integer.toString(item.getQuantity()) +
          "\n"
        );
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("The file could not be found.write\n");
      e.printStackTrace();
    }
  }

  private static ArrayList<Item> readStockFromCSV(String filename) {
    ArrayList<Item> inventory = new ArrayList<Item>();
    String line = "";
    String csvSplitter = ",";
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(csvSplitter);
        String sku = data[0];
        String name = data[1];
        String category = data[2];
        double price = Double.parseDouble(data[3]);
        int quantity = Integer.parseInt(data[4]);
        Item item = new Item(sku, name, category, quantity, price);
        inventory.add(item);
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("The file could not be found.read\n");
      e.printStackTrace();
    }
    return inventory;
  }

  // ! ||--------------------------------------------------------------------------------||
  // ! ||                                      V                                         ||
  // ! ||--------------------------------------------------------------------------------||

  private static void printMenu() {
    System.out.println("\nInventory Management System");
    System.out.println("----------------------------");
    System.out.println("1. Add item");
    System.out.println("2. Remove item");
    System.out.println("3. Update item");
    System.out.println("4. Print inventory");
    System.out.println("5. Quit");
    System.out.print("Enter choice: ");
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
      "----------------------------------------------------------------"
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

  // ! ||--------------------------------------------------------------------------------||
  // ! ||                                      C                                         ||
  // ! ||--------------------------------------------------------------------------------||
  public static void main(String[] args) throws IOException {
    System.out.println("");
    controlProgram();
    scanner.close();
    System.out.println("");
  }

  static void controlProgram() throws IOException {
    boolean passedUserValidation = false;
    System.out.println("User Login");
    System.out.println("----------");
    do {
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();
      passedUserValidation = validateLogin(username, password);
      if (passedUserValidation) {
        inventory = readStockFromCSV(csvFilePath);
        break;
      }
    } while (!passedUserValidation);
    boolean quit = false;
    while (!quit) {
      printMenu();
      int choice = scanner.nextInt();
      scanner.nextLine();
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
}
