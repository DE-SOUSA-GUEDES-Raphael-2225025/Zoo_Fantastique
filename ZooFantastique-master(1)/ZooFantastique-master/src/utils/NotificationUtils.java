package utils;

import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class NotificationUtils {

    public static void showNotification(String title, String text, Pos pos){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().title(title).text(text).position(pos).showInformation();
            }
        });
    }

    public static void showNotification(String title, String text){
        showNotification(title, text, Pos.BOTTOM_RIGHT);
    }

    public static void showErrorNotification(String title, String text){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().title(title).text(text).position(Pos.BOTTOM_RIGHT).showError();
            }
        });
    }
}
