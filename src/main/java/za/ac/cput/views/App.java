package za.ac.cput.views;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.entity.Guest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    private static OkHttpClient client = new OkHttpClient();

    private static String run(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public static void getAll() {
        final String URL = "http://localhost:8080/guests/all";

        try {
            String responseBody = run(URL);
            JSONArray jsonArray = new JSONArray(responseBody);

            // Custom Gson configuration
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                            LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .create();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Guest guest = gson.fromJson(jsonObject.toString(), Guest.class);
                System.out.println(guest);
            }
        } catch (IOException e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing response: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getAll();
    }
}
