package com.example

import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SQSController(
    private val queueMessagingTemplate: QueueMessagingTemplate
) {

    /**
     * Pull Message from SQS
     */
    @GetMapping("/")
    fun getReport(): String? {
        val queueName = "send-message-queue"
        return queueMessagingTemplate
            .receiveAndConvert(queueName, String::class.java)
    }

    /**
     * Receive Message from SQS
     */
    @SqsListener("send-message-queue")
    fun getMessage(message: String) {
        log.info("Message from SQS Queue - $message")
    }

    companion object {
        private val log = LoggerFactory.getLogger(SQSController::class.java)
    }
}
