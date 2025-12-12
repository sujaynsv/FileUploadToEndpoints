package com.fileuploader.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fileuploader.model.Student;
import com.fileuploader.service.UploaderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileUploaderController {

@Autowired
private UploaderService uploader;

    @PostMapping("/upload-students")
    public ResponseEntity<List<Student>> upload(@RequestParam("file") MultipartFile file) throws Exception {

        if (file == null || file.isEmpty())
            return ResponseEntity.badRequest().build();

        List<Student> saved = uploader.saveCsv(file);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/getstudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(uploader.getAll());
    }
}
