package pl.edu.pw.mini.core.invoker.rest;

import com.google.common.io.CharStreams;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import pl.edu.pw.mini.core.exceptions.BusinessException;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                        || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        String body = CharStreams.toString(new InputStreamReader(clientHttpResponse.getBody()));
        throw new BusinessException(clientHttpResponse.getStatusCode().toString(), "External Service Error: " + body);
    }
}
