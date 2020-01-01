package vua.pavic.ZhoonstagramApi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

    File saveFile(MultipartFile f);
    File getFile(String name);
}
