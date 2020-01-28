package vua.pavic.ZhoonstagramApi.services;

import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.utils.ObjectDetection;
import vua.pavic.ZhoonstagramApi.utils.PigeonDetection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;

@Service
public class PigeonDetectionServiceImpl implements PigeonDetectionService {

    ObjectDetection objectDetection;

    public PigeonDetectionServiceImpl() {
        this.objectDetection = new PigeonDetection();
    }

    @Override
    public double isPigeon(File file) {
       try {
           objectDetection.setupImagePath(file);
           return objectDetection.detect();
       }catch(IOException e) {
           return 0;
        }
    }
}
