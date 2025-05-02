package com.example.PAF.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequest {
    private String title;
    private String userName;
    private String headline;
    private String description;
    private MultipartFile imageFile;
    private List<String> tags;
}
