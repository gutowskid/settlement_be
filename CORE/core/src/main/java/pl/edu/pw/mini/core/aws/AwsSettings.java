package pl.edu.pw.mini.core.aws;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AwsSettings {
    private String bucketName;
    private String accessKey;
    private String secretKey;
    private String regionName;
}
