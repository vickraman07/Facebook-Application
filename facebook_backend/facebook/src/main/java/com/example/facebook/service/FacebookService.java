package com.example.facebook.service;

import com.example.facebook.entity.FacebookEntity;
import com.example.facebook.repository.FacebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FacebookService {

    @Autowired

    private FacebookRepository postRepository;

    public List<FacebookEntity> getAllPosts() {
        return postRepository.findAll();
    }

    public ResponseEntity<?> uploadPost(MultipartFile file, String name, String comment) throws IOException {
        FacebookEntity post = new FacebookEntity();
        post.setName(name);
        post.setComment(comment);
        post.setImg(file.getBytes());
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updatePost(Long id, MultipartFile file, String name, String comment, String comment1) throws IOException {
        Optional<FacebookEntity> existingEntity = postRepository.findById(id);
        if (existingEntity.isPresent()) {
            FacebookEntity post = existingEntity.get();

            if (name != null && !name.isEmpty()) {
                post.setName(name);
            }
            if (comment != null && !comment.isEmpty()) {
                post.setComment(comment);
            }
            if (file != null && !file.isEmpty()) {
                post.setImg(file.getBytes());
            }
            if (comment1 != null && !comment1.isEmpty()) {
                post.setComment1(comment1);
            }

            postRepository.save(post);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
