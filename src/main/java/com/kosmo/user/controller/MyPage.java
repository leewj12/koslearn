package com.kosmo.user.controller;

import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.dto.UsersImageDTO;
import com.kosmo.user.service.UserService;
import jakarta.transaction.Transactional;
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
import java.util.UUID;

@Controller
public class MyPage {

    @Autowired
    private UserService userService;

    @PostMapping("/my/profile-update")
    @Transactional
    public String userprofileupdate(UserDTO userDTO, UsersImageDTO usersImageDTO, @RequestParam("id") long id,
                                    @RequestParam("mfilename") MultipartFile mfilename) {

        userDTO.setId(id);
        if (!mfilename.isEmpty()) {
            String origin_filename = mfilename.getOriginalFilename();
            String hashing_filename = UUID.randomUUID().toString() + "_" + origin_filename;
            Path path = Paths.get("src/main/resources/userimages" + File.separator + hashing_filename);
            try {
                Files.write(path, mfilename.getBytes());
                usersImageDTO.setOrigin_filename(origin_filename);
                usersImageDTO.setHashing_filename(hashing_filename);
                usersImageDTO.setFilesize(mfilename.getSize());
                usersImageDTO.setUserId(userDTO.getId());
                userService.saverorupdateImage(usersImageDTO);
            } catch (IOException e) {
                return "파일 업로드 실패";
            }
        }

        userService.updateUser(userDTO);

        return "mypage/mypage";
    }


}
