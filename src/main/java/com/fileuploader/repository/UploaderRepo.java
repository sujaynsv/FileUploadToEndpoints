package com.fileuploader.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fileuploader.model.Student;

@Repository
public interface UploaderRepo extends MongoRepository<Student, String> {

}
