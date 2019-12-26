package vua.pavic.ZhoonstagramApi.controllers;

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
import vua.pavic.ZhoonstagramApi.errors.CantSaveException;
import vua.pavic.ZhoonstagramApi.model.api.FileUploadResponse;

import javax.activation.FileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    @PostMapping
    public FileUploadResponse handleFileUpload(@RequestParam("file") MultipartFile file){
        File fileToSave = new File(context.getRealPath("resources/uploads") +"/"+ file.getOriginalFilename());
        if(!fileToSave.exists()){
            fileToSave.mkdirs();
        }
        try {
            file.transferTo(fileToSave);
            Resource resource = new ClassPathResource("saved_model.pb");
            String pathToModel = resource.getFile().getParent();
            SavedModelBundle model = SavedModelBundle.load(pathToModel, "serve");
            Tensor<File> tensor = model.session().runner().fetch("input_blob")
                        .feed("x", Tensor.<File>create(file, File.class))
                        .run().get(0).expect(File.class);
                System.out.println(tensor.intValue());
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
