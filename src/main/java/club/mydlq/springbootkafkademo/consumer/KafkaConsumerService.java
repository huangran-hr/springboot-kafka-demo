package club.mydlq.springbootkafkademo.consumer;

import javax.annotation.Resource;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import club.mydlq.springbootkafkademo.dto.PersonDto;

@Service
public class KafkaConsumerService {
	@Resource
	private ObjectMapper objectMapper;

    @KafkaListener(topics = {"test"}, groupId = "group1", containerFactory="kafkaListenerContainerFactory")
	//@KafkaHandler
    public void kafkaListener(String message){
        System.out.println(message);
    }

	@KafkaListener(topics = {"person"}, groupId = "group2", containerFactory="kafkaListenerContainerFactory")
    public void personListener(String message){
    	PersonDto person = null;
		try {
			person = objectMapper.readValue(message, PersonDto.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        System.out.println(person);
    }
}
