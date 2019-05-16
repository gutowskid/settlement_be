package pl.edu.pw.mini.core.invoker.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestInvokerConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "pl.edu.pw.mini.rest-client")
    public RestSettings restSettings() {
        return new RestSettings();
    }

    @Rest
    @Bean
    public PoolingHttpClientConnectionManager httpClientConnectionManager(RestSettings restSettings) {
        Registry<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registryBuilder);
        poolingHttpClientConnectionManager.setMaxTotal(restSettings.getMaxConnections());
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(restSettings.getMaxRouteConnections());
        return poolingHttpClientConnectionManager;
    }

    @Rest
    @Bean
    public RequestConfig restRequestConfig(RestSettings restSettings) {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(restSettings.getReadTimeout())
                .setConnectTimeout(restSettings.getReadTimeout())
                .setSocketTimeout(restSettings.getReadTimeout())
                .build();
    }

    @Rest
    @Bean
    public CloseableHttpClient httpClient(@Rest HttpClientConnectionManager connectionManager, @Rest RequestConfig requestConfig) {
        return HttpClientBuilder
                .create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    @Rest
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory(@Rest HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(@Rest HttpComponentsClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new RestResponseErrorHandler());
        return restTemplate;
    }

    @Rest
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean
    public RestInvoker restInvoker(RestSettings restSettings, RestTemplate restTemplate, @Rest ObjectMapper objectMapper) {
        return RestInvokerImpl.builder()
                .defaultApiUrl(restSettings.getUrl())
                .restTemplate(restTemplate)
                .objectMapper(objectMapper)
                .build();
    }
}
