package org.crochetdata.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    public String saveFile(MultipartFile file, String directory) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destinationFile = new File(directory, filename);
        file.transferTo(destinationFile);
        return "/uploads/" + filename;
    }
}
