package Model;

import java.util.List;

public class Coffee extends MenuItem {
    private String size;
    private List<String> addIns;
    private int quantity;

    private double SHORT_PRICE = 1.99;
    private double TALL_PRICE = 2.49;
    private double GRANDE_PRICE = 2.99;
    private double VENTI_PRICE = 3.49;
    private double PRICE = 0.00;

    private double ADDIn_PRICE = 0.30;


    public Coffee(String size, List<String> addIns, int quantity) {
        this.size = size;
        this.addIns = addIns;
        this.quantity = quantity;
    }

    @Override
    public double price() {
        double basePrice = switch (size) {
            case "Short" -> SHORT_PRICE;
            case "Tall" -> TALL_PRICE;
            case "Grande" -> GRANDE_PRICE;
            case "Venti" -> VENTI_PRICE;
            default -> PRICE;
        };
        double addInsPrice = ADDIn_PRICE * addIns.size();
        return (basePrice + addInsPrice) * quantity;
    }

    @Override
    public String toString() {
        String addInsString = addIns.isEmpty() ? "No add-ins" : String.join(", ", addIns);
        return String.format("Coffee [Size: %s, Add-Ins: %s, Quantity: %d]", size.toUpperCase(), addInsString, quantity);
    }


}

