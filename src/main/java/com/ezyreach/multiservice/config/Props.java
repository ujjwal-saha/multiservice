package com.ezyreach.multiservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ConfigurationProperties(prefix = "finpay.service")
@EnableConfigurationProperties
@Data
@Component
public class Props {
    private final BaseUrl baseurl=new BaseUrl();
    private final Merchant merchant=new Merchant();
    private final Transaction transaction=new Transaction();
    private final Master master=new Master();


    @Data
    public static class BaseUrl {
        private String kyc;
        private String onboarding;
        private String transaction;
        private String master;
    }

    @Data
    public static class Merchant {
        private String onboarding;
        private String sendotp;
        private String validateotp;
        private String resendotp;
        private String biometric;
        private String statuscheck;
    }

    @Data
    public static class Transaction {  
        private Withdrawal withdrawal;
        private Balance balance;
        private Statement statement;
        private Aadhaar aadhaar;
        private Deposit deposit;
    }

    @Data
    public static class Withdrawal {
        private String withdrawal;
        private String status;
    }

    @Data
    public static class Balance {
        private String balancecheck;      
    }

    @Data
    public static class Statement {
        private String ministatement;      
    }

    @Data
    public static class Aadhaar {
        private String payment;  
        private String paymentstatus;
        private String deposit;
        private String depositacknowledgement;
        private String depositstatus;    
    }

    @Data
    public static class Deposit {
        private String depositwithotp;
        private String validateotp;
        private String transaction;
        private String acknowledgementwithoutotp;
        private String statuscheck;

    }

    @Data
    public static class Master {
        private String state;    
        private String companytype;  
    }

}
