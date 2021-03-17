package com.one2tribe.demo.controller;

import com.one2tribe.demo.dto.MessageCommandDto;
import com.one2tribe.demo.dto.MessageDto;
import com.one2tribe.demo.service.MessageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public List<MessageDto> getMessageList(){
    return messageService.getAllMessages();
    }

    @PostMapping("/message")
    public MessageDto addNewMessage( @Validated @RequestBody MessageCommandDto messageCommandDto){
         return messageService.saveNewMessage(messageCommandDto);
    }

    @PutMapping("/message/{id}")
    public MessageDto updateMessageById(@PathVariable int id, @Validated @RequestBody MessageCommandDto messageCommandDto){
        return messageService.updateMessage(id, messageCommandDto);
    }

    @GetMapping("/message/random/{number}")
    public Set<MessageDto> getRandomMessages(@PathVariable int number)  {
        return messageService.getRandomMessagesByNumber(number);
    }

}
