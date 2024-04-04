package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import Model.*;

import java.util.ArrayList;
import java.util.List;


public class OrderViewController {

    @FXML
    private Button removeItem;

    @FXML
    private Button placeOrder;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField totalAmount;

    @FXML
    private ListView<MenuItem> orderListView = new ListView<>();

    private Order currentOrder = MainViewController.getCurrentOrder();

    private static List<Order> allOrders = new ArrayList<>();


    private final double SALES_TAX = 0.06625;

    private void showAlertOrderAdded() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("The order has been placed.");

        alert.showAndWait();
    }

    private void updateOrderView() {
        orderListView.getItems().clear();
        double subTotalValue = 0;

        for (MenuItem item : currentOrder.getItems()) {
            orderListView.getItems().add(item);
            subTotalValue += item.price();
        }

        double tax = subTotalValue * SALES_TAX;
        double total = subTotalValue + tax;

        subtotal.setText(String.format("%.2f", subTotalValue));
        salesTax.setText(String.format("%.2f", tax));
        totalAmount.setText(String.format("%.2f", total));
    }

    public void initialize() {
        updateOrderView();
    }



    @FXML
    private void removeSelectedItem() {
        MenuItem selectedItem = orderListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            currentOrder.removeItem(selectedItem);
            updateOrderView();
        }
    }

    @FXML
    private void placeOrderAction() {

        allOrders.add(currentOrder);
        MainViewController.resetOrder();
        updateOrderView();
        showAlertOrderAdded();
    }

    public static List<Order> getAllOrders() {
        return allOrders;
    }

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
        updateOrderView();
    }

}
