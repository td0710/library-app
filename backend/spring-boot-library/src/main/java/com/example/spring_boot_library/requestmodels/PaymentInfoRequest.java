package com.example.spring_boot_library.requestmodels;

import lombok.Data;

@Data
public class PaymentInfoRequest {
    private int amount;
    private String currency;
    private String recieptEmail;
}
