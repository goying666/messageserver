//package com.renchaigao.zujuba.messageserver.config.mongoDB;
//
//import com.mongodb.MongoClient;
//import com.mongodb.ServerAddress;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@Configuration
//@EnableMongoRepositories(basePackages = "com.renchaigao.zujuba.messageserver.domain.mongoDB", mongoTemplateRef = "normalMongo")
//public class NormalMongoTemplate {
//    @Autowired
////    @Qualifier("normalMongoProperties")
//    @Qualifier("NormalMongoConfig")
//    private MongoProperties mongoProperties;
//
//    @Primary
//    @Bean(name = "normalMongo")
//    public MongoTemplate statisMongoTemplate() throws Exception {
//        return new MongoTemplate(normalFactory(this.mongoProperties));
//    }
//
//    @Bean
//    @Primary
//    public MongoDbFactory normalFactory(MongoProperties mongoProperties) throws Exception {
//
//        ServerAddress serverAdress = new ServerAddress(mongoProperties.getHost(),mongoProperties.getPort());
////        ServerAddress serverAdress = new ServerAddress(mongoProperties.getUri());
//        return new SimpleMongoDbFactory(new MongoClient(serverAdress), mongoProperties.getDatabase());
//
//    }
//}
