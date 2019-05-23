package pl.edu.pw.mini.core.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.AllArgsConstructor;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;
import pl.edu.pw.mini.model.aws.FileDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@AllArgsConstructor
public class AwsS3Service {

    private AmazonS3 s3Client;
    private AwsSettings awsSettings;

    public String getFile(Long id) throws IOException {
        S3Object s3Object = s3Client.getObject(awsSettings.getBucketName(), id.toString());
        return Base64Utils.encodeToString(StreamUtils.copyToByteArray(s3Object.getObjectContent()));
    }

    public void addFile(FileDto fileDto, Long key) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                awsSettings.getBucketName(),
                key.toString(),
                new ByteArrayInputStream(Base64Utils.decodeFromString(fileDto.getContent())),
                new ObjectMetadata()
        ).withCannedAcl(CannedAccessControlList.Private);
        s3Client.putObject(putObjectRequest);
    }

    public void deleteFile(Long key) {
        s3Client.deleteObject(awsSettings.getBucketName(), key.toString());
    }
}
