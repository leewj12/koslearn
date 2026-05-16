package com.kosmo.course.mapper;

import com.kosmo.course.dto.CourseviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CourseviewMapper {

    // 페이징 처리된 강의 목록 조회
    List<CourseviewDTO> getCourseListWithPagination(@Param("start") int start,
                                                    @Param("end") int end,
                                                    @Param("searchQuery") String searchQuery);

    // 검색어를 반영한 전체 강의 수 조회
    int getTotalCourseCount(@Param("searchQuery") String searchQuery);
}
