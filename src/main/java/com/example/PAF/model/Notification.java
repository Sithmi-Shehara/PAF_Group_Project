package com.example.PAF.model;

import java.util.Date;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {
    private String userName;
    private String message;
    private boolean seen;
    private Date timestamp;
}
