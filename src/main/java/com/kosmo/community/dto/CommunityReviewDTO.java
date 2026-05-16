package com.kosmo.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityReviewDTO {
    private int review_id;
    private String content;
    private String username;
    private int postId;
}