package com.example.spring_boot_library.controller;

import com.example.spring_boot_library.entity.Message;
import com.example.spring_boot_library.requestmodels.AdminQuestionRequest;
import com.example.spring_boot_library.service.MessageService;
import com.example.spring_boot_library.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
            @RequestBody Message message) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"") ;
        messageService.postMessage(message, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value = "Authorization") String token,
                           @RequestBody AdminQuestionRequest adminQuestionRequest
                           ) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"") ;
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"") ;
        if(admin==null || !admin.equals("admin")){
            throw new Exception("Administration page only.") ;
        }
        messageService.putMessage(adminQuestionRequest, userEmail);
    }
}
