package vua.pavic.ZhoonstagramApi.services;

import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.utils.VisitableImage;

import java.io.File;

public interface ImageFileService {

    VisitableImage saveImage(MultipartFile f);
    VisitableImage getImage(String name);
}
