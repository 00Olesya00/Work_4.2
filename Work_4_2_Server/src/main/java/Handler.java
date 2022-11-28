import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Handler implements Runnable{
    private boolean running;
    private InputStream is;
    private OutputStream os;
    private byte[] buf;
    private Socket socket;

    public Handler(Socket socket) throws IOException {
        running = true;
        buf = new byte[10000];
        this.socket = socket;
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }
    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        try { // т.к, нет вариантов прокинуть на верх try -catch, обязательно вне цикла
            while (running) {
                //обрабатываем сообщение, приняли/отправили
                int read = is.read(buf);
                String messege = new String(buf,0,read)
                        .trim();
                if (messege.equals("выйти")) {
                        os.write("Соединение с пользователем прервано...\n".getBytes(StandardCharsets.UTF_8));
                    System.out.println("Соединение с пользователем прервано...\n".getBytes(StandardCharsets.UTF_8));
                        close();
                        stop();
                        break;
                    }
                System.out.println("Получено: " + messege );
               os.write((messege+"\n").getBytes(StandardCharsets.UTF_8));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void close() throws IOException {// Закрытие соединения/разрыв связи
        os.close();
        is.close();
        socket.close();
    }
}