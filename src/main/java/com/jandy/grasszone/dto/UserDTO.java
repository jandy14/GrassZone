package com.jandy.grasszone.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private int userNum;
    private String name;
    private String password;
    private String info;
    private Timestamp creationTime;
}
