import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private Stage stage;
    private Boolean faceDetected = false;
    private Boolean secondaryText = false;
    private Boolean helpButton = false;
    private Text userDisplayTextMain;
    private Text userDisplayTextSub;
    private Text userDisplayTextHelp;
    private HBox buttonHBox;
    private String profileString = "There is no current customer \n This will update when a face is detected.";
    private String profileName = "There is no current customer";

    private static Items item;

    public final Timer clockTimer = new Timer();

    public static void main(String[] args) {
        item = new Items("Macbook","laptops");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        userDisplayTextMain = new Text("Step up to begin your tailored experience.");
        userDisplayTextMain.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        userDisplayTextMain.setFill(Color.WHITE);
        userDisplayTextMain.setStyle("-fx-text-fill: white;");
        userDisplayTextMain.setX(300);
        userDisplayTextMain.setY(200);
        userDisplayTextMain.setTextAlignment(TextAlignment.CENTER);

        userDisplayTextSub = new Text(item.getName().substring(0,15) + "     Iphone 11.      Airpod Pros.");
        userDisplayTextSub.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        userDisplayTextSub.setFill(Color.WHITE);
        userDisplayTextSub.setTextAlignment(TextAlignment.CENTER);
        userDisplayTextSub.setVisible(false);

        userDisplayTextHelp = new Text("Would you like employee help?");
        userDisplayTextHelp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        userDisplayTextHelp.setFill(Color.WHITE);
        userDisplayTextHelp.setTextAlignment(TextAlignment.CENTER);
        userDisplayTextHelp.setVisible(false);

        Button yesButton = new Button("Yes");
        yesButton.setAlignment(Pos.CENTER_LEFT);

        Button noButton = new Button("No");
        yesButton.setAlignment(Pos.CENTER_RIGHT);

        buttonHBox = new HBox(70);
        buttonHBox.getChildren().add(yesButton);
        buttonHBox.getChildren().add(noButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setVisible(false);

        VBox textBox = new VBox();
        textBox.getChildren().add(userDisplayTextMain);
        textBox.getChildren().add(userDisplayTextSub);
        textBox.getChildren().add(userDisplayTextHelp);
        textBox.getChildren().add(buttonHBox);
        textBox.setSpacing(75);
        textBox.setAlignment(Pos.CENTER);

        StackPane userPane = new StackPane(textBox);
        userPane.setPrefSize(2560,1600);
        userPane.setAlignment(Pos.CENTER);
        userPane.setStyle("-fx-background-image: url('UserDisplayBackground.png');" + "-fx-background-size: stretch;" + "-fx-background-size: no-repeat;" + "-fx-background-size: center;");

        ObservableList<String> names = FXCollections.observableArrayList(
                "MacBook Pro", "Iphone 11", "Airpod Pros", "Canon EOS R", "USB Adapter", "Iphone Cover", "Portable Charger");
        ListView<String> analyticBox = new ListView<>(names);
        analyticBox.setMaxSize(250, 350);

        Label profileBox = new Label(profileString);
        profileBox.setFont(new Font(25));
        profileBox.setTextFill(Color.WHITE);
        profileBox.setAlignment(Pos.CENTER_LEFT);

        ImageView profileImage = new ImageView();
        profileImage.setFitHeight(250);
        profileImage.setFitWidth(350);

        Label nameLabel = new Label(profileName);
        nameLabel.setFont(new Font(25));
        nameLabel.setTextFill(Color.WHITE);

        VBox customerBox = new VBox(30);
        customerBox.getChildren().add(profileImage);
        customerBox.getChildren().add(nameLabel);
        customerBox.setAlignment(Pos.CENTER);

        customerBox.setAlignment(Pos.CENTER_LEFT);

        HBox dataBox = new HBox();
        dataBox.getChildren().add(customerBox);
        dataBox.getChildren().add(profileBox);
        dataBox.getChildren().add(analyticBox);
        dataBox.setSpacing(225);
        dataBox.setAlignment(Pos.CENTER);

        StackPane employeePane = new StackPane(dataBox);
        userPane.setPrefSize(2560,1600);
        userPane.setAlignment(Pos.CENTER);
        employeePane.setStyle("-fx-background-image: url('EmployeeDisplayBackground.png');" + "-fx-background-size: stretch;" + "-fx-background-size: no-repeat;" + "-fx-background-size: center;");

        Tab userTab = new Tab("User Interface");
        userTab.setClosable(false);
        userTab.setContent(userPane);
        userTab.setStyle("-fx-text-base-color: white;"+" -fx-background-color: grey;");

        Tab employeeTab = new Tab("Employee Interface");
        employeeTab.setClosable(false);
        employeeTab.setContent(employeePane);
        employeeTab.setStyle("-fx-text-base-color: white;"+" -fx-background-color: grey;");

        TabPane tabScreen = new TabPane(userTab, employeeTab);
        tabScreen.setOnKeyPressed(e -> buttonClick());

        Scene scene = new Scene(tabScreen, 1600, 2560);

        primaryStage.setTitle("BestAI V0.1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // This is where the program periodically refreshes the GUI and checks global variables.
        // You can set text fields and also their visibility here, depending on events.

        clockTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (faceDetected) {
                        userDisplayTextMain.setText("Hi Jill. Here are some recommendations.");
                    }
                    if (secondaryText) {
                        userDisplayTextSub.setVisible(true);
                    }
                    if (helpButton) {
                        userDisplayTextHelp.setVisible(true);
                        buttonHBox.setVisible(true);
                    }
                });

            }
        }, 0,500);

    }

    public void buttonClick() {
        faceDetected = true;
        secondaryText = true;
        helpButton = true;
    }
}
