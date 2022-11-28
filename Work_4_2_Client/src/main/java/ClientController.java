import App.NET.IONet;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class ClientController {
    public static class clientController implements Initializable {
        private static final int SOCKET_PORT = 8188;// порт
        public ListView<String> listView;
        public final static String FILE_TO_SEND = "c:/temp/source.pdf"; //Расположение файла
        public TextField input;
        private IONet net;


        public void sendMsg(ActionEvent actionEvent) throws IOException {
            net.senMsg(input.getText());
            input.clear();
        }
        private void addMessage(String msg) {

            Platform.runLater(() -> listView.getItems().add(msg));// обрабатываем сообщение, добовляем в ListView

        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) { //поднимим сеть
            try {

                Socket socket = new Socket("localhost",SOCKET_PORT); //поднимаем сокет

                net = new IONet(this::addMessage, socket); // получение сообщения (call back)

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
