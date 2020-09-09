FROM openjdk:13-alpine
MAINTAINER otajisan

VOLUME /tmp

RUN mkdir /spring-aws-sqs-consumer-example
WORKDIR /spring-aws-sqs-consumer-example

ENV JAVA_OPTS=""
COPY ./build/libs/spring-aws-sqs-consumer-example-0.0.1.jar /spring-aws-sqs-consumer-example
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-aws-sqs-consumer-example/spring-aws-sqs-consumer-example-0.0.1.jar" ]