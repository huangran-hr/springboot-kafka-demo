package club.mydlq.springbootkafkademo.controller;

import club.mydlq.springbootkafkademo.dto.PersonDto;
import club.mydlq.springbootkafkademo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/sync")
    public void sendMessageSync() throws InterruptedException, ExecutionException, TimeoutException {
    	PersonDto p = new PersonDto();
    	p.setId(1);
    	p.setName("Labniz");
        producerService.sendMessageSync("person", p);
    }

    @GetMapping("/async")
    public void sendMessageAsync(){
        producerService.sendMessageAsync("test","异步发送消息测试");
    }

}