package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.example.demo.domain.User;
import com.example.demo.helper.CSVHelper;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public void fileSave(MultipartFile file) throws NumberFormatException, ParseException {
        try {
            List<User> users = CSVHelper.csvToUsers(file.getInputStream());
            repository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<User> users = repository.findAll();

        ByteArrayInputStream in = CSVHelper.usersToCSV(users);
        return in;
    }

    public List<User> getAllUsers() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public User findOne(String id) {
        return repository.getOne(id);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}