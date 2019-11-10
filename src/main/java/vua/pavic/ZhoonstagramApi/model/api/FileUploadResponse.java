package vua.pavic.ZhoonstagramApi.model.api;

import lombok.Data;

@Data
public class FileUploadResponse {
    private String fileName;

    public FileUploadResponse(String fileName) {
        this.fileName = fileName;
    }
}
