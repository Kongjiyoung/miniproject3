package com.many.miniproject1.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserFileService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {
        // 파일 이름과 경로 설정
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // 파일 저장
        File dest = new File(filePath);
        file.transferTo(dest);

        // 파일의 상대 경로 반환
        return filePath;
    }

//    public Resource loadFile(String filename) throws FileNotFoundException {
//        try {
//            Path file = Paths.get(uploadDir).resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new FileNotFoundException("Unable to load file: " + filename);
//            }
//        } catch (MalformedURLException e) {
//            throw new FileNotFoundException("Unable to load file: " + filename + e);
//        }
//    }
}
