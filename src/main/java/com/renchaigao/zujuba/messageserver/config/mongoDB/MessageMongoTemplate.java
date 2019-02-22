//
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
//@EnableMongoRepositories(basePackages = "com.renchaigao.zujuba.messageserver.config.mongoDB", mongoTemplateRef = "messageMongo")
//public class MessageMongoTemplate {
//    @Autowired
//    @Qualifier("messageMongoProperties")
//    private MongoProperties mongoProperties;
//
//    @Primary
//    @Bean(name = "messageMongo")
//    public MongoTemplate statisMongoTemplate() throws Exception {
//        return new MongoTemplate(messageFactory(this.mongoProperties));
//    }
//
//    @Bean
//    @Primary
//    public MongoDbFactory messageFactory(MongoProperties mongoProperties) throws Exception {
////        ServerAddress serverAdress = new ServerAddress(mongoProperties.getUri());
//        ServerAddress serverAdress = new ServerAddress(mongoProperties.getHost(),mongoProperties.getPort());
//        return new SimpleMongoDbFactory(new MongoClient(serverAdress), mongoProperties.getDatabase());
//
//    }
//}
