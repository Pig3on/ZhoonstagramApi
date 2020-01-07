package vua.pavic.ZhoonstagramApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.errors.CantSaveException;
import vua.pavic.ZhoonstagramApi.utils.NullImage;
import vua.pavic.ZhoonstagramApi.utils.VisitableImage;
import vua.pavic.ZhoonstagramApi.utils.VisitableImageImpl;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageFileService {

    @Autowired
    ServletContext context;

    @Autowired
    VisitableImage nullImage;

    @Override
    public VisitableImage saveImage(MultipartFile file) {
        File fileToSave = new File(context.getRealPath("resources/uploads") +"/"+ file.getOriginalFilename());
        if(!fileToSave.exists()){
            fileToSave.mkdirs();
        }
        try {
            file.transferTo(fileToSave);
            return new VisitableImageImpl(fileToSave);
        } catch (IOException e) {
            throw new CantSaveException();
        }
    }

    @Override
    public VisitableImage getImage(String filename) {
       File fileToGet = new File(context.getRealPath("resources/uploads") +"/"+ filename);

       if(fileToGet.exists()){
           return new VisitableImageImpl(fileToGet);
       }else{
           return nullImage;
       }
    }
}
