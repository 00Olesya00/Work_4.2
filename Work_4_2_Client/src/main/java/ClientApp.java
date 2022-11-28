import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("chat.fxml"));
        Group root = new Group(parent);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }
//        public static void main(String[] args) {
//            launch();
//        }

}
