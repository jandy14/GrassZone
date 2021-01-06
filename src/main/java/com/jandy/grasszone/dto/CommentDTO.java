package com.jandy.grasszone.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {
    private int commentID;
    private int postID;
    private String contents;
    private int author;
    private Timestamp creationTime;
    private String authorName;
}
