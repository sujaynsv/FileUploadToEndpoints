package com.fileuploader.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fileuploader.model.Student;

public interface UploaderService {
	 List<Student> saveCsv(MultipartFile file) throws IOException;
	 List<Student> getAll();
}
