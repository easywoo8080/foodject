package com.foodject.naverObj;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.foodject.biz.HostShopBiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjFileList {

	@Autowired
	HostShopBiz biz;
	@Value("${NaverAccessKey}")
	String accessKey;

	@Value("${NaverSecretKey}")
	String secretKey;

	@Test
	void contextLoads() {

		final String endPoint = "https://kr.object.ncloudstorage.com";
		final String regionName = "kr-standard";


		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();

		String bucketName = "foodject";
		String objectName = "cust/moon.jpg";
		// list all in the bucket
		try {
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
					.withBucketName(bucketName)
					.withMaxKeys(300).withPrefix("cust/moon.jpg");

			ObjectListing objectListing = s3.listObjects(listObjectsRequest);

			
			int cnt = 0;
			System.out.println("Object List:");
			
			while (true) {
				for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
					System.out.println("    name=" + objectSummary.getKey() + ", size=" + objectSummary.getSize()
							+ ", owner=" + objectSummary.getOwner().getId());
							cnt++;
				}

				if (objectListing.isTruncated()) {
					objectListing = s3.listNextBatchOfObjects(objectListing);
				} else {
					break;
				}
			}
			System.out.println("cnt : " + cnt);

			

		} catch (AmazonS3Exception e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}

		// top level folders and files in the bucket
		try {
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
					.withBucketName(bucketName)
					.withDelimiter("/")
					.withMaxKeys(300);

			ObjectListing objectListing = s3.listObjects(listObjectsRequest);

			System.out.println("Folder List:");
			for (String commonPrefixes : objectListing.getCommonPrefixes()) {
				System.out.println("    name=" + commonPrefixes);
			}

			System.out.println("File List:");
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				System.out.println("    name=" + objectSummary.getKey() + ", size=" + objectSummary.getSize()
						+ ", owner=" + objectSummary.getOwner().getId());
			}
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}

	}

}
