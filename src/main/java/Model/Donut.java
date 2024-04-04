package Model;

public class Donut extends MenuItem {
    private String type;
    private String flavor;
    private int quantity;

    private double YEAST_PRICE = 1.79;
    private double CAKE_PRICE = 1.89;
    private double HOLES_PRICE = 0.39;

    private double PRICE = 0.00;


    public Donut(String type, String flavor, int quantity) {
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    public Donut() {
        this.type = "Yeast";
        this.flavor = "Jelly";
        this.quantity = 0;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double price() {
        double unitPrice = switch (type) {
            case "Yeast Donuts" -> YEAST_PRICE;
            case "Cake Donuts" -> CAKE_PRICE;
            case "Donut Holes" -> HOLES_PRICE;
            default -> PRICE;
        };
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("Donut [%s] Flavor: %s, Quantity: %d", type, flavor, quantity);
    }

}
