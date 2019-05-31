package pl.edu.pw.mini.core.aws;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class AwsS3ServiceTestMock extends AwsS3Service {

    public AwsS3ServiceTestMock() {
        super(null, null);
    }

}
