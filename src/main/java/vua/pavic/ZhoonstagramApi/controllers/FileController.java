package vua.pavic.ZhoonstagramApi.controllers;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import sun.misc.IOUtils;
import vua.pavic.ZhoonstagramApi.errors.CantSaveException;
import vua.pavic.ZhoonstagramApi.model.api.FileUploadResponse;
import vua.pavic.ZhoonstagramApi.services.PigeonDetectionService;

import javax.activation.FileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    ServletContext context;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    PigeonDetectionService pigeonDetectionService;

    @PostMapping
    public FileUploadResponse handleFileUpload(@RequestParam("file") MultipartFile file){
        File fileToSave = new File(context.getRealPath("resources/uploads") +"/"+ file.getOriginalFilename());
        if(!fileToSave.exists()){
            fileToSave.mkdirs();
        }
        try {
            file.transferTo(fileToSave);
            if(!pigeonDetectionService.isPigeon(fileToSave)){
                fileToSave.delete();
                throw new CantSaveException("Not a Pigeon");
            }
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
