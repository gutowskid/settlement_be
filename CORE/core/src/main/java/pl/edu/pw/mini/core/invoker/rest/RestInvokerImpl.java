package pl.edu.pw.mini.core.invoker.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import pl.edu.pw.mini.core.security.authentication.Context;
import pl.edu.pw.mini.core.security.authentication.Token;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Log4j
@Getter
@Builder
@Setter(AccessLevel.PACKAGE)
public class RestInvokerImpl implements RestInvoker {
    private String defaultApiUrl;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    private String api(String url) {
        return defaultApiUrl + url;
    }

    @Override
    public <T> T get(String url, Map<String, String> params, Class<T> responseType) {
        return get(url, params, responseType, null);
    }

    @Override
    public <T> T get(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference) {
        return get(url, params, responseTypeReference, null);
    }

    public <T> T get(String url, Map<String, String> params, Class<T> responseType, Token token) {
        HttpEntity<?> httpEntity = createHttpEntity(null, token);
        ResponseEntity<T> response = restTemplate.exchange(api(url), HttpMethod.GET, httpEntity, responseType, params);
        return response.getBody();
    }

    public <T> T get(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token) {
        HttpEntity<?> httpEntity = createHttpEntity(null, token);
        ResponseEntity<T> response = restTemplate.exchange(api(url), HttpMethod.GET, httpEntity, responseTypeReference, params);
        return response.getBody();
    }

    @Override
    public <T> T post(String url, Map<String, String> params, Class<T> responseType) {
        return post(url, params, null, responseType, null);
    }

    @Override
    public <T> T post(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference) {
        return post(url, params, null, responseTypeReference, null);
    }

    @Override
    public <T> T post(String url, Map<String, String> params, Class<T> responseType, Token token) {
        return post(url, params, null, responseType, token);
    }

    @Override
    public <T> T post(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token) {
        return post(url, params, null, responseTypeReference, token);
    }

    @Override
    public <T, P> T post(String url, Map<String, String> params, P body, Class<T> responseType) {
        return post(url, params, body, responseType, null);
    }

    @Override
    public <T, P> T post(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference) {
        return post(url, params, body, responseTypeReference, null);
    }

    @Override
    public <T, P> T post(String url, Map<String, String> params, P body, Class<T> responseType, Token token) {
        HttpEntity<?> httpEntity = createHttpEntity(body, token);
        ResponseEntity<T> response = restTemplate.exchange(api(url), HttpMethod.POST, httpEntity, responseType, params);
        return response.getBody();
    }

    @Override
    public <T, P> T post(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference, Token token) {
        HttpEntity<?> httpEntity = createHttpEntity(body, token);
        ResponseEntity<T> response = restTemplate.exchange(api(url), HttpMethod.POST, httpEntity, responseTypeReference, params);
        return response.getBody();
    }

    @Override
    public <T> T put(String url, Map<String, String> params, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T put(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference) {
        return null;
    }

    @Override
    public <T> T put(String url, Map<String, String> params, Class<T> responseType, Token token) {
        return null;
    }

    @Override
    public <T> T put(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token) {
        return null;
    }

    @Override
    public <T, P> T put(String url, Map<String, String> params, P body, Class<T> responseType) {
        return null;
    }

    @Override
    public <T, P> T put(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference) {
        return null;
    }

    @Override
    public <T, P> T put(String url, Map<String, String> params, P body, Class<T> responseType, Token token) {
        return null;
    }

    @Override
    public <T, P> T put(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference, Token token) {
        return null;
    }

    @Override
    public <T> T delete(String url, Map<String, String> params, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T delete(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference) {
        return null;
    }

    @Override
    public <T> T delete(String url, Map<String, String> params, Class<T> responseType, Token token) {
        return null;
    }

    @Override
    public <T> T delete(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token) {
        return null;
    }

    @Override
    public String createRequestUrl(String url, Map<String, String> params) {
        if(params.isEmpty()) {
            return url;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        boolean isFirstParameter = true;
        for(String param : params.keySet()) {
            if(isFirstParameter) {
                buffer.append("?");
                isFirstParameter = false;
            } else {
                buffer.append("&");
            }
            buffer.append(param);
            buffer.append("={");
            buffer.append(param);
            buffer.append("}");
        }
        return buffer.toString();
    }

    private <T> HttpEntity<T> createHttpEntity(T body, Token token) {
        HttpHeaders httpHeaders = createHttpHeaders();
        addTokenToHeaders(httpHeaders, token);
        return new HttpEntity<>(body, httpHeaders);
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.setAccept(mediaTypeList);
        return httpHeaders;
    }

    private void addTokenToHeaders(HttpHeaders httpHeaders, Token token) {
        if(Objects.isNull(token)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth instanceof Context) {
                token = (Token) auth.getDetails();
            }
        }
        if(Objects.nonNull(token)) {
            httpHeaders.add("Authorization", "Token " + token.getTokenString());
        }
    }
}
