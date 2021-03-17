package com.one2tribe.demo.entity;

import com.one2tribe.demo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MessageTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testGetMessages(){
        List<Message> messageList = messageRepository.findAll();
        assertTrue(messageList.size()>0);
    }

}
