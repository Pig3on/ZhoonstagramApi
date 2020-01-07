package vua.pavic.ZhoonstagramApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.errors.NotAPigeonException;
import vua.pavic.ZhoonstagramApi.utils.PigeonVisitor;
import vua.pavic.ZhoonstagramApi.utils.VisitableImage;
import vua.pavic.ZhoonstagramApi.utils.VisitableImageImpl;

@Service
public class ImageProcessingServiceImpl implements ImageProcessingService {

    private PigeonDetectionService pigeonDetectionService;
    private ImageFileService fileService;

    @Autowired
    public ImageProcessingServiceImpl(PigeonDetectionService pigeonDetectionService, ImageFileService fileService) {
        this.pigeonDetectionService = pigeonDetectionService;
        this.fileService = fileService;
    }

    @Override
    public void processImage(MultipartFile image) {
        VisitableImage savedFile = fileService.saveImage(image);
        PigeonVisitor pigeonVisitor = new PigeonVisitor(pigeonDetectionService);
        savedFile.accept(pigeonVisitor);
        if (!pigeonVisitor.isPigeon()) {
            savedFile.getImage().delete();
            throw new NotAPigeonException("This image is not of a pigeon");
        }
    }
}

