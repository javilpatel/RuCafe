package Controller;

import Model.MenuItem;
import Model.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllOrderViewController implements Initializable {

    @FXML
    private Button cancelOrder;

    @FXML
    private Button exportOrder;

    @FXML
    private ComboBox<String> orderNumber =  new ComboBox<>();

    @FXML
    private ListView<String> orderListView = new ListView<>();

    @FXML
    private TextField totalAmount;

    private double TAX_PRICE = 0.06625;

    private List<Order> orders = OrderViewController.getAllOrders();

    private void populateOrderNumbers() {
        orderNumber.getItems().clear();
        for (Order order : orders) {
            orderNumber.getItems().add(String.valueOf(order.getOrderNumber()));
        }
    }
    private void viewOrderDetails(String orderNumString) {
        int orderNum = Integer.parseInt(orderNumString);
        Order selectedOrder = orders.stream()
                .filter(order -> order.getOrderNumber() == orderNum)
                .findFirst()
                .orElse(null);

        if (selectedOrder != null) {
            orderListView.getItems().clear();
            for (MenuItem item : selectedOrder.getItems()) {
                orderListView.getItems().add(item.toString());
            }


            double subtotal = selectedOrder.calculateTotal();
            double tax = subtotal * 0.06625;
            totalAmount.setText(String.format("%.2f", subtotal + tax));
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateOrderNumbers();
        orderNumber.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                viewOrderDetails(newVal);
            }
        });

    }
    @FXML
    private void cancelSelectedOrder() {
        String selectedOrderNum = orderNumber.getValue();
        if (selectedOrderNum != null && !selectedOrderNum.isEmpty()) {
            orders.removeIf(order -> String.valueOf(order.getOrderNumber()).equals(selectedOrderNum));
            populateOrderNumbers();
            orderListView.getItems().clear();
            totalAmount.clear();
        }
    }

    @FXML
    private void exportOrdersToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders");
        fileChooser.setInitialFileName("orders.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                for (Order order : orders) {
                    writer.write(String.format("Order #%d\n", order.getOrderNumber()));
                    for (MenuItem item : order.getItems()) {
                        writer.write(item.toString() + "\n");
                    }
                    double subtotal = order.calculateTotal();
                    double tax = subtotal * TAX_PRICE;
                    double total = subtotal + tax;
                    writer.write(String.format("Subtotal: $%.2f, Tax: $%.2f, Total: $%.2f\n\n", subtotal, tax, total));
                }
            } catch (IOException e) {
                System.err.println("Error saving orders to file: " + e.getMessage());
            }
        }
    }

}
