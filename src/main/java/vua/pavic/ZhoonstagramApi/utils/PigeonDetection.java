package vua.pavic.ZhoonstagramApi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PigeonDetection extends ObjectDetection {

    @Override
    public void setupImagePath(File file) throws IOException {
        URL url = new URL("http://localhost:5000/detect?file_name="+ URLEncoder.encode(file.getAbsolutePath(),java.nio.charset.StandardCharsets.UTF_8.toString()));
        this.con = (HttpURLConnection) url.openConnection();
        this.con.setRequestMethod("GET");
    }

    @Override
    public double detect() throws IOException {
        int status = this.con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(this.con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        this.con.disconnect();

        String certainty = content.toString();
        double decimalCertainty = Double.parseDouble(certainty);
        return decimalCertainty;
    }
}
