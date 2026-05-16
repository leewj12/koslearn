package com.kosmo.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UsersImageDTO {
    private String origin_filename;
    private String hashing_filename;
    private long filesize;
    private long userId;
}
