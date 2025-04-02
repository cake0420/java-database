package com.cake7.database.controller;

import com.cake7.database.model.dto.SignUpRequestDTO;
import com.cake7.database.service.SignUpServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")  // 엔드포인트에 공통 URL이 있다면 추가됨

public class SignUpController {
    private final SignUpServiceImpl signUpService;

    public SignUpController(SignUpServiceImpl signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> doPost(@RequestBody SignUpRequestDTO signUpRequestDTO) throws Exception {

        try {
            signUpService.signUp(signUpRequestDTO);
            return ResponseEntity.ok("회원가입 성공");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 이메일입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생!");
        }
    }
}
