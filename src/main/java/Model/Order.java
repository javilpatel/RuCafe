package Model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int lastOrderNumber = 0;
    private int orderNumber;
    private List<MenuItem> items;

    public Order() {
        this.orderNumber = ++lastOrderNumber;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public boolean removeItem(MenuItem item) {
        return items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.price();
        }
        return total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    // Getter for the items list
    public List<MenuItem> getItems() {
        return items;
    }
}

