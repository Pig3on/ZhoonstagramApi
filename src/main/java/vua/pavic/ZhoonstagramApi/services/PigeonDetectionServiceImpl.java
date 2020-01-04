package vua.pavic.ZhoonstagramApi.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;

@Service
public class PigeonDetectionServiceImpl implements PigeonDetectionService {
    @Override
    public boolean isPigeon(File file) {
       try {
           URL url = new URL("http://localhost:5000/detect?file_name="+ URLEncoder.encode(file.getAbsolutePath(),java.nio.charset.StandardCharsets.UTF_8.toString()));
           HttpURLConnection con = (HttpURLConnection) url.openConnection();
           con.setRequestMethod("GET");

           int status = con.getResponseCode();

           BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
           String inputLine;
           StringBuilder content = new StringBuilder();
           while ((inputLine = in.readLine()) != null) {
               content.append(inputLine);
           }
           in.close();
           con.disconnect();

           String certainty = content.toString();
           double decimalCertainty = Double.parseDouble(certainty);
           return !(decimalCertainty < 0.95);

       }catch(IOException e) {
           return false;
        }
    }
}
