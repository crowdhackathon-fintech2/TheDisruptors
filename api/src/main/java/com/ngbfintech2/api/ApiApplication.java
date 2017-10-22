package com.ngbfintech2.api;

import com.simplify.payments.PaymentsApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
        PaymentsApi.PRIVATE_KEY = "lve3krej6OTczd2+IX84grttkIq072oLOtlDAyHNmXB5YFFQL0ODSXAOkNtXTToq";
        PaymentsApi.PUBLIC_KEY = "sbpb_Njc0ODUxMTctNzhmMy00YzY3LTgwMmMtNWM2YjI0MTA0MTg3";
		SpringApplication.run(ApiApplication.class, args);
	}
}
