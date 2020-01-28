package vua.pavic.ZhoonstagramApi.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public abstract class ObjectDetection {

    // template
    HttpURLConnection con;
    public void setupImagePath(File image) throws IOException {
        throw new NotImplementedException();
    }
    public double detect() throws IOException {
        throw new NotImplementedException();
    }
}
