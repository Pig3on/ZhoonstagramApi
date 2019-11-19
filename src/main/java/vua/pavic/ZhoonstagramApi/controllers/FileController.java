package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.errors.CantSaveException;
import vua.pavic.ZhoonstagramApi.model.api.FileUploadResponse;

import javax.activation.FileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    ServletContext context;
    @PostMapping
    public FileUploadResponse handleFileUpload(@RequestParam("file") MultipartFile file){
        File fileToSave = new File(context.getRealPath("resources/uploads") +"/"+ file.getOriginalFilename());
        if(!fileToSave.exists()){
            fileToSave.mkdirs();
        }
        try {
            file.transferTo(fileToSave);
        } catch (IOException e) {
            throw new CantSaveException();
        }

       return new FileUploadResponse(file.getOriginalFilename());
    }
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String filename, HttpServletResponse response){
        File img = new File(context.getRealPath("resources/uploads") +"/"+ filename);
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
                    .body(Files.readAllBytes(img.toPath()));
        } catch (IOException e) {
            throw new CantSaveException();
        }
    }
}
