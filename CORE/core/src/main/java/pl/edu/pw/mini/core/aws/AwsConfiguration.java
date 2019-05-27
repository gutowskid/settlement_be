package pl.edu.pw.mini.core.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "pl.edu.pw.mini.aws", name = "enabled", matchIfMissing = true)
public class AwsConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "pl.edu.pw.mini.aws")
    public AwsSettings awsSettings() {
        return new AwsSettings();
    }

    @Bean
    public AmazonS3 amazonS3Client (AwsSettings awsSettings) {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsSettings.getAccessKey(), awsSettings.getSecretKey())))
                .withRegion(Regions.fromName(awsSettings.getRegionName()))
                .build();
    }

    @Bean
    public AwsS3Service awsS3Service(AwsSettings awsSettings, AmazonS3 amazonS3) {
        return new AwsS3Service(amazonS3, awsSettings);
    }
}
