package com.fileuploader.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileuploader.model.Student;
import com.fileuploader.repository.UploaderRepo;
import com.opencsv.CSVReader;

@Service
public class UploaderServiceImpl implements UploaderService {

	@Autowired
	private UploaderRepo repo;

	public List<Student> saveCsv(MultipartFile file) throws IOException {
		List<Student> saved = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
				CSVReader reader = new CSVReader(br)) {

			String[] rows;
			boolean first = true;

			while ((rows = reader.readNext()) != null) {

				if (first) {
					first = false;
					continue;
				}

				String name = rows.length > 0 ? rows[0].trim() : "";
				String email = rows.length > 1 ? rows[1].trim() : "";

				Integer age = null;
				try {
					age = rows.length > 2 && !rows[2].trim().isEmpty() ? Integer.valueOf(rows[2].trim()) : null;
				} catch (NumberFormatException e) {
					age = null;
				}

				String course = rows.length > 3 ? rows[3].trim() : "";

				Student s = new Student(null, name, email, age, course);
				saved.add(repo.save(s));
			}

		} catch (Exception e) {
			throw new IOException("CSV parse error", e);
		}

		return saved;
	}

	public List<Student> getAll() {
		return repo.findAll();
	}
}
