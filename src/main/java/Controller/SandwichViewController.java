package Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Model.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SandwichViewController implements Initializable {

    @FXML
    private Button addToOrder;

    @FXML
    private TextField subtotal;

    @FXML
    private ImageView sandwichImage = new ImageView();

    @FXML
    private ToggleGroup proteinGroup, breadGroup;

    @FXML
    private RadioButton bagel, wheatToast, sourDough, beef, chicken, fish;

    @FXML
    private CheckBox lettuce, tomato, cheese, onion;

    private Order currentOrder;

    private void showAlertOrderAdded() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Updated");
        alert.setHeaderText(null);
        alert.setContentText("The selected items have been added to your order.");

        alert.showAndWait();
    }

    private void updateSubtotal() {
        String bread = ((RadioButton)breadGroup.getSelectedToggle()) != null ? ((RadioButton)breadGroup.getSelectedToggle()).getText().toLowerCase() : "";
        String protein = ((RadioButton)proteinGroup.getSelectedToggle()) != null ? ((RadioButton)proteinGroup.getSelectedToggle()).getText().toLowerCase() : "";
        List<String> addOns = new ArrayList<>();
        if (lettuce.isSelected()) addOns.add("lettuce");
        if (tomato.isSelected()) addOns.add("tomato");
        if (cheese.isSelected()) addOns.add("cheese");
        if (onion.isSelected()) addOns.add("onion");
        int quantity = 1;


        Sandwich sandwich = new Sandwich(bread, protein, addOns, quantity);
        double price = sandwich.price();
        subtotal.setText(String.format("%.2f", price));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentOrder = MainViewController.getCurrentOrder();
        Image sandwich1 = new Image("file:src/images/sandwich.png");
        sandwichImage.setImage(sandwich1);

        proteinGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> updateSubtotal());
        lettuce.setOnAction(event -> updateSubtotal());
        tomato.setOnAction(event -> updateSubtotal());
        cheese.setOnAction(event -> updateSubtotal());
        onion.setOnAction(event -> updateSubtotal());

    }

    @FXML
    private void addToOrder() {

        String bread = ((RadioButton)breadGroup.getSelectedToggle()) != null ? ((RadioButton)breadGroup.getSelectedToggle()).getText().toLowerCase() : "";
        String protein = ((RadioButton)proteinGroup.getSelectedToggle()) != null ? ((RadioButton)proteinGroup.getSelectedToggle()).getText().toLowerCase() : "";
        List<String> addOns = new ArrayList<>();
        if (lettuce.isSelected()) addOns.add("lettuce");
        if (tomato.isSelected()) addOns.add("tomato");
        if (cheese.isSelected()) addOns.add("cheese");
        if (onion.isSelected()) addOns.add("onion");

        Sandwich sandwich = new Sandwich(bread, protein, addOns, 1);
        currentOrder.addItem(sandwich);
        updateTotalSubtotal();
        showAlertOrderAdded();
    }

    private void updateTotalSubtotal() {
        double total = currentOrder.calculateTotal();
    }
}





