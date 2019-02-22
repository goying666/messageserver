package com.renchaigao.zujuba.messageserver;

import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamMessageInfo;
import com.renchaigao.zujuba.mongoDB.info.user.UserMessagesInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageserverApplicationTests {

	@Resource(name = "messageMongoTemplate")
	MongoTemplate messageMongoTemplate;

	@Resource(name = "normalMongoTemplate")
	MongoTemplate normalMongoTemplate;

	@Test
	public void contextLoads() {
//		UserMessagesInfo userMessagesInfo = new UserMessagesInfo();
//		ArrayList<TeamMessageInfo> arrayList = new ArrayList<>();
//		TeamMessageInfo teamMessageInfo = new TeamMessageInfo();
//		MessageContent messageContent = new MessageContent();
//		messageContent.setId(2L);
//		teamMessageInfo.getMessageNoteInfoArrayList().add(messageContent);
//		arrayList.add(teamMessageInfo);
//		userMessagesInfo.setUserTeamMessage(arrayList);
//		messageMongoTemplate.updateFirst(Query.query(Criteria.where("userTeamMessage").is("26056963aa8a43dfa877e6fa14fe2550")),
//				new Update().push();
//		messageMongoTemplate.save(userMessagesInfo,"333");
//		userMessagesInfo.setId("123");
//		messageMongoTemplate.save(userMessagesInfo,"123");
//		List<MessageContent> messageNoteInfos = mongoTemplate.findAll(MessageContent.class,"Group312ac2c6247b4463b4e8a6bbeb48f40b");
//		messageNoteInfos = null;
//		List<JSONObject> jsonObjectList = mongoTemplate2.findAll(JSONObject.class,"AddressInfo");
//		jsonObjectList = null;
	}

}
