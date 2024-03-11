package com.example.facebook.controller;

import com.example.facebook.entity.FacebookEntity;
import com.example.facebook.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
public class FacebookController {

    @Autowired
    private FacebookService postService;

    @GetMapping
    public List<FacebookEntity> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<?> uploadPost(@RequestParam("file") MultipartFile file,
                                        @RequestParam("name") String name,
                                        @RequestParam("comment") String comment) throws IOException {
        return postService.uploadPost(file, name, comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id,
                                        @RequestParam(value = "file", required = false) MultipartFile file,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "comment", required = false) String comment,
                                        @RequestParam(value = "comment1", required = false) String comment1) throws IOException {
        return postService.updatePost(id, file, name, comment, comment1);
    }
}