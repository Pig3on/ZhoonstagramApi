package vua.pavic.ZhoonstagramApi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ImageProcessingService {

    void processImage(MultipartFile file);
}
