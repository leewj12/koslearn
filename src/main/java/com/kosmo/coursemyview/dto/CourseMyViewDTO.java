package com.kosmo.coursemyview.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
@NoArgsConstructor
public class CourseMyViewDTO {
    private String title;
    private String url;
    private String materials;
    private String description;
    private String summary;

}
