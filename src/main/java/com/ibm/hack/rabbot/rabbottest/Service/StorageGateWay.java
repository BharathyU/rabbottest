package com.ibm.hack.rabbot.rabbottest.Service;

import com.ibm.cloud.objectstorage.ClientConfiguration;
import com.ibm.cloud.objectstorage.auth.AWSCredentials;
import com.ibm.cloud.objectstorage.auth.AWSStaticCredentialsProvider;
import com.ibm.cloud.objectstorage.client.builder.AwsClientBuilder;
import com.ibm.cloud.objectstorage.oauth.BasicIBMOAuthCredentials;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3ClientBuilder;
import com.ibm.cloud.objectstorage.services.s3.model.ListObjectsRequest;
import com.ibm.cloud.objectstorage.services.s3.model.ObjectListing;
import com.ibm.cloud.objectstorage.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StorageGateWay {


    public String verifyFileInStorage(String fileName){


        String bucketName = "rabbottestbucket";  // eg my-unique-bucket-name
               String apiKey = "xsAmKexXBYeWhQSZkTnSPh48BOOR67T2NbeyJBg0PEFL"; // eg "W00YiRnLW4k3fTjMB-oiB-2ySfTrFBIQQWanc--P3byk"
        String serviceInstanceId = "crn:v1:bluemix:public:cloud-object-storage:global:a/1d21b2de131d4331a2b332469fa09039:8785c148-320a-4c07-b57e-b5596b3207bd::"; // eg "crn:v1:bluemix:public:cloud-object-storage:global:a/3bf0d9003abfb5d29761c3e97696b71c:d6f04d83-6c4f-4a62-a165-696756d63903::"
        String endpointUrl = "https://s3.ap.cloud-object-storage.appdomain.cloud"; // this could be any service endpoint

        String location = "us-geo";

        System.out.println("Current time: " + LocalDateTime.now());
        AmazonS3 cosClient = createClient(apiKey, serviceInstanceId, endpointUrl, location);



        listObjects(cosClient, bucketName);

        return "OK";
    }


    public static AmazonS3 createClient(String apiKey, String serviceInstanceId, String endpointUrl, String location)
    {
        AWSCredentials credentials = new BasicIBMOAuthCredentials(apiKey, serviceInstanceId);
        ClientConfiguration clientConfig = new ClientConfiguration()
                .withRequestTimeout(5000)
                .withTcpKeepAlive(true);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, location))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfig)
                .build();
    }


    public static void listObjects(AmazonS3 cosClient, String bucketName)
    {
        System.out.println("Listing objects in bucket " + bucketName);
        ObjectListing objectListing = cosClient.listObjects(new ListObjectsRequest().withBucketName(bucketName));
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
        }
        System.out.println();
    }

}
