package Game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " has been added to the inventory.");
    }

    public void removeItem(Item item) {
        if (items.remove(item)) {
            System.out.println(item.getName() + " has been removed from the inventory.");
        } else {
            System.out.println(item.getName() + " is not in the inventory.");
        }
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
    public boolean isEmpty() {
        return items.isEmpty();  // Uses List's isEmpty() method
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        System.out.println("Invalid item selection.");
        return null;
    }

    public int getItemCount() {
        return items.size();
    }
}
