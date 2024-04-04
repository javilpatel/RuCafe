package Controller;

import Model.Coffee;
import Model.Order;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CoffeeViewController implements Initializable {

    @FXML
    private Button addToOrder;

    @FXML
    private TextField subtotal;

    @FXML
    private ImageView coffeeImage = new ImageView();

    @FXML
    private ComboBox<String> coffeeSize =  new ComboBox<>();

    @FXML
    private ComboBox<String> setQuantity =  new ComboBox<>();

    @FXML
    private CheckBox sweetCream = new CheckBox();

    @FXML
    private CheckBox frenchVanilla = new CheckBox();

    @FXML
    private CheckBox irishCream = new CheckBox();

    @FXML
    private CheckBox mocha = new CheckBox();

    @FXML
    private CheckBox caramel = new CheckBox();

    private Order currentOrder;

    private void updateSubtotal() {
        String size = coffeeSize.getValue() != null ? coffeeSize.getValue() : "";
        int quantity = setQuantity.getValue() != null ? Integer.parseInt(setQuantity.getValue()) : 0;
        List<String> addIns = new ArrayList<>();
        if (sweetCream.isSelected()) addIns.add("Sweet Cream");
        if (frenchVanilla.isSelected()) addIns.add("French Vanilla");
        if (irishCream.isSelected()) addIns.add("Irish Cream");
        if (mocha.isSelected()) addIns.add("Mocha");
        if (caramel.isSelected()) addIns.add("Caramel");

        Coffee coffee = new Coffee(size, addIns, quantity);
        double price = coffee.price();

        subtotal.setText(String.format("%.2f", price));
    }

    private void showAlertOrderAdded() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Updated");
        alert.setHeaderText(null);
        alert.setContentText("The selected items have been added to your order.");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentOrder = MainViewController.getCurrentOrder();
        Image coffee1 = new Image("file:src/images/coffee.png");
        coffeeImage.setImage(coffee1);

        coffeeSize.setItems(FXCollections.observableArrayList("Short","Tall","Grande", "Venti"));
        setQuantity.setItems(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9"));

        coffeeSize.setOnAction(event -> updateSubtotal());
        setQuantity.setOnAction(event -> updateSubtotal());
        sweetCream.setOnAction(event -> updateSubtotal());
        frenchVanilla.setOnAction(event -> updateSubtotal());
        irishCream.setOnAction(event -> updateSubtotal());
        mocha.setOnAction(event -> updateSubtotal());
        caramel.setOnAction(event -> updateSubtotal());

        updateSubtotal();
    }

    @FXML
    public void sizePicked(ActionEvent actionEvent){
        addToOrder.setDisable(false);
    }

    @FXML
    private void addToOrder() {
        String size = coffeeSize.getValue();
        if(size == null){
            return;
        }
        String quantityValue = setQuantity.getValue();
        if(quantityValue==null){
            return;
        }
        int quantity = Integer.parseInt(quantityValue);
        List<String> addIns = new ArrayList<>();
        if (sweetCream.isSelected()) addIns.add("Sweet Cream");
        if (frenchVanilla.isSelected()) addIns.add("French Vanilla");
        if (irishCream.isSelected()) addIns.add("Irish Cream");
        if (mocha.isSelected()) addIns.add("Mocha");
        if (caramel.isSelected()) addIns.add("Caramel");

        Coffee coffee = new Coffee(size, addIns, quantity);
        currentOrder.addItem(coffee);

    }
}

