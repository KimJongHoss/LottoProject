package hg.jh.luko6.controller;

import hg.jh.luko6.entity.ContactRequest;
import hg.jh.luko6.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody ContactRequest request, BindingResult result) {



        emailService.sendContactEmail(request.getName(), request.getEmail(), request.getPhone(), request.getMessage());
        return ResponseEntity.ok("Email sent successfully");
    }
}


















