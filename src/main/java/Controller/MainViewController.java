package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private ImageView imageDonut = new ImageView();

    @FXML
    private ImageView imageCoffee = new ImageView();

    @FXML
    private ImageView imageSandwich = new ImageView();

    @FXML
    private ImageView imageCurrentOrder = new ImageView();

    @FXML
    private ImageView imageAllOrder = new ImageView();

    private static Order currentOrder = new Order();

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void resetOrder() {
        currentOrder = new Order(); // Call this method when you want to start a new order
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Image donut= new Image("file:src/images/dount1.png");
        imageDonut.setImage(donut);


        Image coffee = new Image("file:src/images/coffee.png");
        imageCoffee.setImage(coffee);


        Image sandwich = new Image("file:src/images/sandwich.png");
        imageSandwich.setImage(sandwich);

        Image currentOrder = new Image("file:src/images/order.png");
        imageCurrentOrder.setImage(currentOrder);

        Image allOrder = new Image("file:src/images/allorders.png");
        imageAllOrder.setImage(allOrder);


    }

    @FXML
    void openDonutWindow(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Donut-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Donuts");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openCoffeeWindow(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Coffee-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Coffee");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void openSandwichWindow(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sandwich-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sandwich");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void openCurrentOrderWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Current Order");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openAllOrderWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("All-Order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("All Orders");
        stage.setScene(scene);
        stage.show();
    }

}