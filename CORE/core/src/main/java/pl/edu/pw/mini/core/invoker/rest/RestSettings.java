package pl.edu.pw.mini.core.invoker.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestSettings {
    private String url;
    private int maxConnections = 30;
    private int maxRouteConnections = 10;
    private int connectTimeOut = 5000;
    private int readTimeout = 50000;
}
