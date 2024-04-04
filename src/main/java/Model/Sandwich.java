package Model;

import java.util.List;

public class Sandwich extends MenuItem {

    private String bread;
    private String protein;
    private List<String> addOns;
    private int quantity;

    private double BEEF_PRICE = 10.99;

    private double CHICKEN_PRICE = 8.99;

    private double FISH_PRICE = 9.99;

    private double PRICE = 0.00;

    private double ADDIn_PRICE = 0.30;

    private double CHEESE_PRICE = 1;


    public Sandwich(String bread, String protein, List<String> addOns, int quantity) {
        this.bread = bread;
        this.protein = protein;
        this.addOns = addOns;
        this.quantity = quantity;
    }

    @Override
    public double price() {
        double basePrice = switch (protein) {
            case "beef" -> BEEF_PRICE;
            case "chicken" -> CHICKEN_PRICE;
            case "fish" -> FISH_PRICE;
            default -> PRICE;
        };
        double addOnsPrice = 0;
        for (String addOn : addOns) {
            addOnsPrice += "cheese".equals(addOn) ? CHEESE_PRICE : ADDIn_PRICE;
        }
        return (basePrice + addOnsPrice) * quantity;
    }
    @Override
    public String toString() {
        String addOnsString = addOns.isEmpty() ? "No add-ons" : String.join(", ", addOns);
        return String.format("Sandwich [Protein: %s] Add-Ons: %s, Quantity: %d", protein.substring(0, 1).toUpperCase() + protein.substring(1), addOnsString, quantity);
    }

}

