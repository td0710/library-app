package com.example.spring_boot_library.controller;


import com.example.spring_boot_library.entity.Book;
import com.example.spring_boot_library.requestmodels.AddBookRequest;
import com.example.spring_boot_library.service.AdminService;
import com.example.spring_boot_library.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value = "Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }

        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader(value = "Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }

        adminService.decreaseBookQuantity(bookId);
    }

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(name = "Authorization") String token,
                           @RequestBody AddBookRequest addBookRequest
                           ) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"") ;
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only") ;
        }
        adminService.postBook(addBookRequest);
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(name = "Authorization") String token,
                          @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"") ;
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only") ;
        }

        adminService.deleteBook(bookId);
    }
}
