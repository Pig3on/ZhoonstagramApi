package vua.pavic.ZhoonstagramApi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;

public class NullImage implements VisitableImage {

    @Autowired
    ServletContext context;



    @Override
    public File getImage() {
        try {
            return ResourceUtils.getFile("classpath:nothing.png");
        } catch (FileNotFoundException e) {
            return new File("stub.png");
        }
    }

    @Override
    public void setImage(File image) {
        // nothing
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
