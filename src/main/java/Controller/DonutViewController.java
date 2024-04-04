package Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Model.*;
import Model.Order;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DonutViewController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button addToOrder;

    @FXML
    private Button removeButton;

    @FXML
    private TextField subtotal;


    @FXML
    private ImageView donutImage = new ImageView();


    @FXML
    private ComboBox<String> donutTypes =  new ComboBox<>();

    @FXML
    private ComboBox<String> setQuantity =  new ComboBox<>();


    @FXML
    private ListView<String> donutFlavors = new ListView<>();


    @FXML
    private ListView<String> selectedFlavors = new ListView<>();

    private Donut donut = new Donut();

    private double totalPrice = 0;

    private List<Donut> tempSelectedDonuts = new ArrayList<>();

    private Order currentOrder;

    private void updatePrice() {
        double tempTotalPrice = 0.0;
        for (Donut donut : tempSelectedDonuts) {
            tempTotalPrice += donut.price();
        }
        subtotal.setText(String.format("%.2f", tempTotalPrice));
    }


    private void showAlertOrderAdded() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Updated");
        alert.setHeaderText(null);
        alert.setContentText("The selected items have been added to your order.");

        alert.showAndWait();
    }

    public void setDonutImage(String flavor){
        String imageName;
        if(flavor.equalsIgnoreCase("Yeast Donuts")){
            imageName = "yeast.png";
        }else if (flavor.equalsIgnoreCase("Cake Donuts")){
            imageName = "cakedonut.png";
        }else if (flavor.equalsIgnoreCase("Donut Holes")){
            imageName = "donutholes.png";
        }else{
            imageName = "donut.png";
        }
        Image donut = new Image("file:src/images/"+imageName);
        donutImage.setImage(donut);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentOrder = MainViewController.getCurrentOrder();
        donutTypes.setItems(FXCollections.observableArrayList("Yeast Donuts","Cake Donuts","Donut Holes"));
        setDonutImage("");
        setQuantity.setItems(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9"));
        addToOrder.setDisable(true);
    }

    @FXML
    public void donutTypePicked(ActionEvent actionEvent){
        String typePicked = donutTypes.getValue();
        donutFlavors.getItems().clear();
        setDonutImage(typePicked);
        if(typePicked.equalsIgnoreCase("Yeast Donuts")){
            donutFlavors.getItems().addAll("jelly", "glazed", "chocolate frosted", "strawberry frosted", "sugar", "lemon filled");
        }else if(typePicked.equalsIgnoreCase("Cake Donuts")){
            donutFlavors.getItems().addAll("cinnamon sugar", "old fashioned", "blueberry");
        }else if(typePicked.equalsIgnoreCase("Donut Holes")){
            donutFlavors.getItems().addAll("jelly holes", "glazed holes", "cinnamon holes");
        }else {
            donutFlavors.getItems().addAll("");
        }
        addToOrder.setDisable(false);
    }

    @FXML
    void addFlavoredDonut(ActionEvent event) {
        String selectedFlavor = donutFlavors.getSelectionModel().getSelectedItem();
        String typePicked = donutTypes.getValue();
        if (selectedFlavor == null || typePicked == null) {
            return;
        }
        String quantityValue = setQuantity.getValue();
        if (quantityValue == null) {
            return;
        }
        int quantity = Integer.parseInt(quantityValue);

        Donut newDonut = new Donut(typePicked, selectedFlavor, quantity);

        tempSelectedDonuts.add(newDonut);

        String listViewEntry = String.format("%s - %s (%d)", typePicked, selectedFlavor, quantity);
        selectedFlavors.getItems().add(listViewEntry);

        updatePrice();
    }



    @FXML
    public void removeFlavors(ActionEvent actionEvent) {
        String selectedFlavorString = selectedFlavors.getSelectionModel().getSelectedItem();
        if(selectedFlavorString == null) { return; }

        String[] parts = selectedFlavorString.split(" - ");
        String type = parts[0];
        String rest = parts[1];
        String flavor = rest.substring(0, rest.lastIndexOf(" ("));
        String quantityString = rest.substring(rest.lastIndexOf("(") + 1, rest.length() - 1);
        int quantity;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            return;
        }

        tempSelectedDonuts.removeIf(donut ->
                donut.getType().equals(type) &&
                        donut.getFlavor().equals(flavor) &&
                        donut.getQuantity() == quantity);

        selectedFlavors.getItems().remove(selectedFlavorString);

        updatePrice();
    }

    @FXML
    private void AddToOrder(ActionEvent event) {
        for (Donut donut : tempSelectedDonuts) {
            currentOrder.addItem(donut);
        }

        tempSelectedDonuts.clear();
        showAlertOrderAdded();
    }



}
