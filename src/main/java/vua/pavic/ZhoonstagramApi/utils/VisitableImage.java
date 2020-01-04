package vua.pavic.ZhoonstagramApi.utils;

import java.io.File;

public class VisitableImage implements Visitable {
    private File image;

    public VisitableImage(File image) {
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
