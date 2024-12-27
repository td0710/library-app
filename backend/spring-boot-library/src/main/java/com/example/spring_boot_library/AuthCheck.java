package com.example.spring_boot_library ;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthCheck {
    public static void main(String[] args) {
        // Lấy thông tin Authentication từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra xem người dùng đã xác thực hay chưa
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("Người dùng chưa được xác thực.");
        } else {
            System.out.println("Người dùng đã xác thực: " + authentication.getName()+"123");
        }
    }
}
