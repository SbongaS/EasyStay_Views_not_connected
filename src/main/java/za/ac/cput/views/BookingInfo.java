package za.ac.cput.views;

import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingInfo extends JFrame implements ActionListener {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
