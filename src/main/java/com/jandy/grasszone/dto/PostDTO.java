package com.jandy.grasszone.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {
    private int postID;
    private String title;
    private String contents;
    private int postType;
    private Timestamp creationTime;
    private int author;
    private String authorName;
    private String postTypeName;
    private int views;

    public PostDTO(String title, String contents, int postType, int author) {
        this.title = title;
        this.contents = contents;
        this.postType = postType;
        this.author = author;
    }
}
