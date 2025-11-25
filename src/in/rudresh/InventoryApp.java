package in.rudresh;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Item> inventory = new HashMap<>();

		while (true) {
			System.out.println("\n=== Inventory Management System ===");
			System.out.println("1. Add Item");
			System.out.println("2. View All Items");
			System.out.println("3. Update Quantity");
			System.out.println("4. Remove Item");
			System.out.println("5. Search Item");
			System.out.println("6. Low-Stock Alerts");
			System.out.println("7. Exit");

			System.out.print("Enter choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> addItem(inventory, sc);
			case 2 -> viewItems(inventory);
			case 3 -> updateQuantity(inventory, sc);
			case 4 -> removeItem(inventory, sc);
			case 5 -> searchItem(inventory, sc);
			case 6 -> lowStockAlert(inventory);
			case 7 -> {
				System.out.println("Goodbye!");
				System.exit(0);
			}
			default -> System.out.println("Invalid choice! Try again.");
			}
		}
	}

	public static void addItem(HashMap<Integer, Item> inventory, Scanner sc) {
		System.out.print("Enter Item ID: ");
		int id = sc.nextInt();
		sc.nextLine();

		if (inventory.containsKey(id)) {
			System.out.println("Item ID already exists!");
			return;
		}

		System.out.print("Enter Item Name: ");
		String name = sc.nextLine();

		System.out.print("Enter Quantity: ");
		int qty = sc.nextInt();

		inventory.put(id, new Item(id, name, qty));
		System.out.println("Item added successfully!");
	}

	public static void viewItems(HashMap<Integer, Item> inventory) {
		System.out.println("\n--- Inventory List ---");

		if (inventory.isEmpty()) {
			System.out.println("No items found!");
			return;
		}

		inventory.values().forEach(System.out::println);
	}

	public static void updateQuantity(HashMap<Integer, Item> inventory, Scanner sc) {
		System.out.print("Enter Item ID to update: ");
		int id = sc.nextInt();

		if (!inventory.containsKey(id)) {
			System.out.println("Item not found!");
			return;
		}

		System.out.print("Enter new quantity: ");
		int newQty = sc.nextInt();

		inventory.get(id).setQuantity(newQty);
		System.out.println("Quantity updated!");
	}

	public static void removeItem(HashMap<Integer, Item> inventory, Scanner sc) {
		System.out.print("Enter Item ID to delete: ");
		int id = sc.nextInt();

		if (inventory.remove(id) != null) {
			System.out.println("Item removed!");
		} else {
			System.out.println("Item not found!");
		}
	}

	public static void searchItem(HashMap<Integer, Item> inventory, Scanner sc) {
		System.out.print("Enter Item ID to search: ");
		int id = sc.nextInt();

		Item item = inventory.get(id);

		if (item != null) {
			System.out.println("Item Found: " + item);
		} else {
			System.out.println("Item not found!");
		}
	}

	public static void lowStockAlert(HashMap<Integer, Item> inventory) {
		System.out.println("\n--- Low Stock Items (Qty < 5) ---");

		List<Item> lowStockItems = inventory.values().stream().filter(item -> item.getQuantity() < 5)
				.collect(Collectors.toList());

		if (lowStockItems.isEmpty()) {
			System.out.println("No low-stock items!");
		} else {
			lowStockItems.forEach(System.out::println);
		}
	}
}