package com.ezyreach.multiservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ConfigurationProperties(prefix = "aeps.service")
@EnableConfigurationProperties
@Data
@Component
public class AEPSProps {
    private final Endpoint endpoint=new Endpoint();
    private final Header header=new Header();

    @Data
    public static class Endpoint {
        private String baseUrl;
        private String outletLoginStatus;
        private String outletLogin;
        private String cashWithdrawal;
        private String cashDeposit;
        private String balanceInquiry;
        private String miniStatement;
        private String banks;
    }

    @Data
    public static class Header {
        private String authCode;
        private String clientId;
        private String clientSecret;
        private String outletId;
    }

}


