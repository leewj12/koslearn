package com.kosmo.instructor.controller;

import com.kosmo.instructor.dto.CourseDataDTO;
import com.kosmo.instructor.dto.InstructorDTO;
import com.kosmo.instructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class Instructor {

    @Autowired
    private InstructorService instructorService;



    @GetMapping("/instructor/myinstructor")
    public String myinstructor(){
        return "/instructor/myinstructohome";
    }

    @GetMapping("/instructor/course-register")
    public String courseregister(){
        return "/instructor/course-register";
    }

    @GetMapping("/instructor/profile-register")
    public String profileregister() {
        return "/instructor/profile-register";  // 앞에 슬래시를 제거
    }

    @PostMapping("/instructor/profile-register")
    public String profileregistersave(InstructorDTO instructorDTO, @RequestParam("mfileName") MultipartFile mfileName) {
        // 프로필 이미지 처리
        if (!mfileName.isEmpty()) {
            String origin_filename = mfileName.getOriginalFilename();
            String hashing_filename=UUID.randomUUID().toString()+"_"+origin_filename;
            Path path = Paths.get("src/main/resources/Instructorimages" + File.separator + hashing_filename);
            try {
                Files.write(path, mfileName.getBytes());
                instructorDTO.setOrigin_filename(origin_filename);
                instructorDTO.setHashing_filename(hashing_filename);
                instructorDTO.setFilesize(mfileName.getSize());
            } catch (IOException e) {
                return "파일 업로드 실패";
            }
        }

        // 데이터 저장
        instructorService.saveInstructor(instructorDTO);

        return "/instructor/myinstructohome";
    }

    @GetMapping("/instructor/profile-update")
    public String profileupdate(){
        return "/instructor/profile-update";
    }




    //강의 자료 올리기
    @PostMapping("/instructor/course-register")
    public String courseregisterdata(CourseDataDTO courseDataDTO,
                                     @RequestParam("courseImage") MultipartFile courseImage,
                                     @RequestParam("courseMaterials") List<MultipartFile> courseMaterials) {

        // 1. 이미지 파일 처리
        if (!courseImage.isEmpty()) {
            // 원본 파일명
            String imageFileName = courseImage.getOriginalFilename();
            // UUID를 생성하여 고유한 파일명 만들기
            String uuidFileName = UUID.randomUUID().toString() + "_" + imageFileName;

            // 서버에 저장할 경로
            Path imagePath = Paths.get("src/main/resources/courseimages" + File.separator + uuidFileName);

            try {
                // 이미지 파일을 지정된 경로에 저장
                Files.write(imagePath, courseImage.getBytes());
                // 이미지 파일 이름을 DTO에 설정 (서버에 저장된 파일명으로 설정)
                courseDataDTO.setImage(uuidFileName);

                // 파일 메타데이터를 DTO에 설정 (원본 파일명과 파일 크기)
                courseDataDTO.setOriginalImageName(imageFileName);
                courseDataDTO.setImageSize(courseImage.getSize());

            } catch (IOException e) {
                return "이미지 파일 업로드 실패";
            }
        }

        // 2. 첨부파일 이름만 처리
        List<String> materialsFileNames = new ArrayList<>();
        List<String> originalMaterialsFileNames = new ArrayList<>();
        for (MultipartFile material : courseMaterials) {
            if (!material.isEmpty()) {
                // 원본 파일명
                String materialFileName = material.getOriginalFilename();
                // UUID를 생성하여 고유한 파일명 만들기
                String uuidMaterialFileName = UUID.randomUUID().toString() + "_" + materialFileName;

                // 서버에 저장할 경로
                Path materialPath = Paths.get("src/main/resources/coursefiles" + File.separator + uuidMaterialFileName);

                try {
                    // 첨부파일을 지정된 경로에 저장
                    Files.write(materialPath, material.getBytes());
                    // 첨부파일 이름을 리스트에 추가 (UUID로 저장된 파일명)
                    materialsFileNames.add(uuidMaterialFileName);
                    originalMaterialsFileNames.add(materialFileName);
                } catch (IOException e) {
                    return "첨부파일 업로드 실패";
                }
            }
        }

        // 3. 첨부파일 이름을 DTO에 설정 (UUID로 저장된 파일명 리스트)
        courseDataDTO.setMaterials(String.join(",", materialsFileNames));
        courseDataDTO.setOriginalMaterials(String.join(",", originalMaterialsFileNames));

        // 4. 강의 정보 저장
        instructorService.CourseDatasave(courseDataDTO);

        return "/instructor/myinstructohome";
    }

    @GetMapping("/course-detail")
    public String coursedetail(){
        return "/instructor/course-detail";
    }




}

