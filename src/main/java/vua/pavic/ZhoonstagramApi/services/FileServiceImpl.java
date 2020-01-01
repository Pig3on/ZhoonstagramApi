package vua.pavic.ZhoonstagramApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.errors.CantSaveException;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    ServletContext context;

    @Override
    public File saveFile(MultipartFile file) {
        File fileToSave = new File(context.getRealPath("resources/uploads") +"/"+ file.getOriginalFilename());
        if(!fileToSave.exists()){
            fileToSave.mkdirs();
        }
        try {
            file.transferTo(fileToSave);
            return fileToSave;
        } catch (IOException e) {
            throw new CantSaveException();
        }
    }

    @Override
    public File getFile(String filename) {
       return new File(context.getRealPath("resources/uploads") +"/"+ filename);
    }
}
