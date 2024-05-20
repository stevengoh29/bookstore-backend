package com.example.demo.module.common.upload;

import com.example.demo.util.ResponseTemplate;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/upload")
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    UploadService storageService;

    @Autowired
    ResponseTemplate<?> responseTemplate;

    @PostMapping("/single")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {
        try {
            String path = storageService.save(file, token);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UploadController.class, "getFile", path).build().toString();
            return ResponseEntity.status(HttpStatus.OK).body(responseTemplate.build(200, "Uploaded", url));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("error");
        }
    }

    @PostMapping("/multiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("file") List<MultipartFile> files, @RequestHeader("Authorization") String token) {
        String message = "";
        try {
            storageService.save(files, token);

//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileUpload>> getListFiles() {
        List<FileUpload> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            System.out.println(filename);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UploadController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileUpload(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) throws IOException {
        UrlResource file = storageService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/png")
                .body(file);
    }
}
