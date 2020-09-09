package com.example

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SQSConfiguration {

    @Value("\${cloud.aws.credentials.accessKey}")
    private lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secretKey}")
    private lateinit var secretKey: String

    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(amazonSQSAsync())
    }

    @Primary
    @Bean
    fun amazonSQSAsync(): AmazonSQSAsync? {
        return if (accessKey.isBlank() || secretKey.isBlank()) {
            AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.fromName(region))
                .build()
        } else {
            AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.fromName(region))
                .build()
        }
    }
}
