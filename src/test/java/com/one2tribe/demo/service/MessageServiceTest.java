package com.one2tribe.demo.service;

import com.one2tribe.demo.dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void testGetRandomIntInARange(){
       int actual = messageService.getRandomIntInARange(1,15);
        assertTrue(actual!=0);
    }

    @Test
    public void testGetAllMessages(){
        List<MessageDto> messageDtoList = messageService.getAllMessages();
        assertTrue(messageDtoList.size()>0);
    }

    @Test
    public void testGetRandomMessagesByNumber(){
        int number = 5;
            Set<MessageDto> messageDtoList = messageService.getRandomMessagesByNumber(number);
            assertTrue(messageDtoList.size()>0);
    }





}
