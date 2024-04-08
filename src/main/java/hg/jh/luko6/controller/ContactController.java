package hg.jh.luko6.controller;

import hg.jh.luko6.entity.ContactRequest;
import hg.jh.luko6.service.EmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@Validated
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody ContactRequest request, BindingResult result) {
        if (result.hasErrors()) {
            // 검증 실패 시, 에러 메시지를 수집하여 반환
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errors);
        }

        emailService.sendContactEmail(request.getName(), request.getEmail(), request.getPhone(), request.getMessage());
        return ResponseEntity.ok("Email sent successfully");
    }
}