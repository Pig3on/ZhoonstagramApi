package vua.pavic.ZhoonstagramApi.utils;

import java.io.File;

public interface VisitableImage extends Visitable {
    public File getImage();
    public void setImage(File image);
}
