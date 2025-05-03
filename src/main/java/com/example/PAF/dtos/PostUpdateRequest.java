package com.example.PAF.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostUpdateRequest {
    private String description;
    private String title;
    private MultipartFile mediaFile0;
    private MultipartFile mediaFile1;
    private MultipartFile mediaFile2;
    private List<String> tags;
}