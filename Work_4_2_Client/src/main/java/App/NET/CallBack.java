package App.NET;

@FunctionalInterface
public interface CallBack { //Интерфейс оброботки сообщений

    void onReceive(String message);
}
