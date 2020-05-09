package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.errors.CantSaveException;
import vua.pavic.ZhoonstagramApi.model.api.FileUploadResponse;
import vua.pavic.ZhoonstagramApi.services.ImageFileService;
import vua.pavic.ZhoonstagramApi.services.ImageProcessingService;
import vua.pavic.ZhoonstagramApi.utils.VisitableImage;

import javax.activation.FileTypeMap;
import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    ServletContext context;

    // singleton
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    ImageProcessingService imageProcessingService;
    @Autowired
    ImageFileService fileService;

    @PostMapping
    public FileUploadResponse handleFileUpload(@RequestParam("file") MultipartFile file){
       File uploaded =  imageProcessingService.processImage(file);
       return new FileUploadResponse(uploaded.getName());
    }
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String filename){
        VisitableImage img = fileService.getImage(filename);
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img.getImage())))
                    .body(Files.readAllBytes(img.getImage().toPath()));
        } catch (IOException e) {
            throw new CantSaveException();
        }
    }
}
