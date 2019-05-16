package pl.edu.pw.mini.core.invoker.rest;

import org.springframework.core.ParameterizedTypeReference;
import pl.edu.pw.mini.core.security.authentication.Token;

import java.util.Map;

public interface RestInvoker {

    <T> T get(String url, Map<String, String> params, Class<T> responseType);
    <T> T get(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference);

    <T> T get(String url, Map<String, String> params, Class<T> responseType, Token token);
    <T> T get(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token);

    <T> T post(String url, Map<String, String> params, Class<T> responseType);
    <T> T post(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference);

    <T> T post(String url, Map<String, String> params, Class<T> responseType, Token token);
    <T> T post(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token);

    <T, P> T post(String url, Map<String, String> params, P body, Class<T> responseType);
    <T, P> T post(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference);

    <T, P> T post(String url, Map<String, String> params, P body, Class<T> responseType, Token token);
    <T, P> T post(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference, Token token);

    <T> T put(String url, Map<String, String> params, Class<T> responseType);
    <T> T put(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference);

    <T> T put(String url, Map<String, String> params, Class<T> responseType, Token token);
    <T> T put(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token);

    <T, P> T put(String url, Map<String, String> params, P body, Class<T> responseType);
    <T, P> T put(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference);

    <T, P> T put(String url, Map<String, String> params, P body, Class<T> responseType, Token token);
    <T, P> T put(String url, Map<String, String> params, P body, ParameterizedTypeReference<T> responseTypeReference, Token token);

    <T> T delete(String url, Map<String, String> params, Class<T> responseType);
    <T> T delete(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference);

    <T> T delete(String url, Map<String, String> params, Class<T> responseType, Token token);
    <T> T delete(String url, Map<String, String> params, ParameterizedTypeReference<T> responseTypeReference, Token token);

    String createRequestUrl(String url, Map<String, String> params);
}

