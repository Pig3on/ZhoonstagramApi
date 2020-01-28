package vua.pavic.ZhoonstagramApi.utils;

import java.io.File;

public class VisitableImageImpl implements VisitableImage {
    private File image;

    public VisitableImageImpl(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
