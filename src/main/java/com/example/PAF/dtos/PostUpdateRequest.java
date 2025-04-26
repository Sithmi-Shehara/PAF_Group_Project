package com.example.PAF.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostUpdateRequest {
    private String description;
    private String title;
    private MultipartFile file;
}