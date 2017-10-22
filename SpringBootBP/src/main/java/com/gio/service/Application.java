package com.gio.service;

import com.simplify.payments.PaymentsApi;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.Payment;
import com.simplify.payments.exception.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {

        PaymentsApi.PRIVATE_KEY = "lve3krej6OTczd2+IX84grttkIq072oLOtlDAyHNmXB5YFFQL0ODSXAOkNtXTToq";
        PaymentsApi.PUBLIC_KEY = "sbpb_Njc0ODUxMTctNzhmMy00YzY3LTgwMmMtNWM2YjI0MTA0MTg3";



        SpringApplication.run(Application.class, args);
    }
}
