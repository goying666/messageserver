//package com.renchaigao.zujuba.messageserver.config.mongoDB;
//
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class MultipleMongoProperties {
//    @Bean(name="normalMongoProperties")
//    @Primary
//    @ConfigurationProperties(prefix="spring.data.mongodb.normal")
//    public MongoProperties normalMongoProperties() {
//        System.out.println("-------------------- normalMongoProperties init ---------------------");
//        return new MongoProperties();
//    }
//
//    @Bean(name="messageMongoProperties")
//    @ConfigurationProperties(prefix="spring.data.mongodb.message")
//    public MongoProperties messageMongoProperties() {
//        System.out.println("-------------------- listMongoProperties init ---------------------");
//        return new MongoProperties();
//    }
//
//}
