import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private Stage stage;
    private Boolean faceDetected = false;
    private Boolean secondaryText = false;
    private Boolean helpButton = false;
    private Boolean captured = false;
    private Text userDisplayTextMain;
    private Text userDisplayTextSub;
    private Text userDisplayTextHelp;
    private HBox buttonHBox;
    private String profileName = "There is no current customer";
    private static FaceID faceID = new FaceID();
    private WebcamIO webcamIO = new WebcamIO();
    private static Items item;
    private Image profileImage = new Image("0.png");
    private ImageView profileImageView;

    public final Timer clockTimer = new Timer();

    public static void main(String[] args) {

        String faceId1 = faceID.FaceRecognize();
        faceID.FaceCompare(faceId1, "filler");


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
        yesButton.setStyle("-fx-background-color: \n" +
                        "        #090a0c,\n" +
                        "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                        "        linear-gradient(#20262b, #191d22),\n" +
                        "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                        "    -fx-background-radius: 5,4,3,5;\n" +
                        "    -fx-background-insets: 0,1,2,0;\n" +
                        "    -fx-text-fill: white;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                        "    -fx-font-family: \"Arial\";\n" +
                        "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                        "    -fx-font-size: 12px;\n" +
                        "    -fx-padding: 10 20 10 20;");

        Button noButton = new Button("No");
        noButton.setAlignment(Pos.CENTER_RIGHT);
        noButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        buttonHBox = new HBox(70);
        buttonHBox.getChildren().add(yesButton);
        buttonHBox.getChildren().add(noButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setVisible(false);

        Button captureButton = new Button("Capture Face");
        captureButton.setAlignment(Pos.BOTTOM_CENTER);
        captureButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");
        captureButton.setOnAction(e -> captureButtonClick());


        VBox textBox = new VBox();
        textBox.getChildren().add(userDisplayTextMain);
        textBox.getChildren().add(userDisplayTextSub);
        textBox.getChildren().add(userDisplayTextHelp);
        textBox.getChildren().add(buttonHBox);
        textBox.getChildren().add(captureButton);
        textBox.setSpacing(70);
        textBox.setAlignment(Pos.CENTER);

        StackPane userPane = new StackPane(textBox);
        userPane.setPrefSize(2560,1600);
        userPane.setAlignment(Pos.CENTER);
        userPane.setStyle("-fx-background-image: url('UserDisplayBackground.png');" + "-fx-background-size: stretch;" + "-fx-background-size: no-repeat;" + "-fx-background-size: center;");

        ObservableList<String> names = FXCollections.observableArrayList(
                "MacBook Pro", "Iphone 11", "Airpod Pros", "Canon EOS R", "USB Adapter", "Iphone Cover", "Portable Charger");
        ListView<String> analyticBox = new ListView<>(names);
        analyticBox.setMaxSize(300, 370);


        ObservableList<String> names1 = FXCollections.observableArrayList(
                "Name:", "Email:", "Past Purchases:", "Cart:");
        ListView<String> profileBox = new ListView<>(names1);
        profileBox.setMaxSize(300, 370);


        profileImageView = new ImageView();
        profileImageView.setImage(profileImage);
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitHeight(250);
        profileImageView.setFitWidth(300);

        Label nameLabel = new Label(profileName);
        nameLabel.setFont(new Font(20));
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setPrefSize(300, 100);
        nameLabel.setMaxSize(300, 100);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        VBox customerBox = new VBox(10);
        customerBox.getChildren().add(profileImageView);
        customerBox.getChildren().add(nameLabel);
        customerBox.setAlignment(Pos.CENTER);

        customerBox.setAlignment(Pos.CENTER_LEFT);

        HBox dataBox = new HBox();
        dataBox.getChildren().add(customerBox);
        dataBox.getChildren().add(profileBox);
        dataBox.getChildren().add(analyticBox);
        dataBox.setSpacing(140);
        dataBox.setPadding(new Insets(270, 150, 100, 150));
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
        }, 0,1000);

    }

    public void buttonClick() {
        faceDetected = true;
        secondaryText = true;
        helpButton = true;
    }

    public void captureButtonClick(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                webcamIO.getImage();
            }
        };
        Thread test = new Thread(r);


        profileImage = new Image(webcamIO.getCount() + ".png");
        profileImageView.setImage(profileImage);
        stage.show();
    }
}
