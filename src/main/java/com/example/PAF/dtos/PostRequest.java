package com.example.PAF.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostRequest {
    private String title;
    private String userName;
    private String description;
    private MultipartFile imageFile;
}
