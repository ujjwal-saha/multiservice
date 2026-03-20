package com.ezyreach.multiservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@Configuration
public class WebClientConfig {

    @Autowired
    private Props props;

    @Value("${proxy.host:}")
    private String proxyHost;

    @Value("${proxy.port:8888}")
    private int proxyPort;

    @Value("${proxy.enabled:false}")
    private boolean proxyEnabled;


    @Bean
    public WebClient transactionWebClient(WebClient.Builder builder) {
        return builder
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .baseUrl(props.getBaseurl().getTransaction())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient kycWebClient(WebClient.Builder builder) {
        return builder
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .baseUrl(props.getBaseurl().getKyc())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient onboardingWebClient(WebClient.Builder builder) {
        return builder
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .baseUrl(props.getBaseurl().getOnboarding())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

     @Bean
    public WebClient masterWebClient(WebClient.Builder builder) {
        return builder
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .baseUrl(props.getBaseurl().getMaster())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    private HttpClient getHttpClient() {
        if (!proxyEnabled || proxyHost.isEmpty()) {
            return HttpClient.create();
        }

        return HttpClient.create()
            .proxy(proxy -> proxy
                .type(ProxyProvider.Proxy.HTTP)
                .host(proxyHost)
                .port(proxyPort));
    }
}
