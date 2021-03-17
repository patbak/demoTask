package com.one2tribe.demo.service;

import com.one2tribe.demo.dto.MessageCommandDto;
import com.one2tribe.demo.dto.MessageDto;
import com.one2tribe.demo.entity.Message;
import com.one2tribe.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public MessageDto convertMessageToMessageDto(Message message){
        return  new MessageDto(
                message.getId(),
                message.getMessage()
        );
    }

    public List<MessageDto> convertMessageListToMessageDtoList(List<Message> messageList){
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (Message message:messageList){
          MessageDto messageDto = convertMessageToMessageDto(message);
          messageDtoList.add(messageDto);
        }
        return messageDtoList;
    }

    public Message convertMessageCommandDtoToMessage(MessageCommandDto messageCommandDto){
        return new Message(
                messageCommandDto.getContent()
        );
    }

    public List<MessageDto> getAllMessages(){
        List<Message> messageList = messageRepository.findAll();
        return  convertMessageListToMessageDtoList(messageList);
    }

    public MessageDto saveNewMessage(MessageCommandDto messageCommandDto){
        Message message = convertMessageCommandDtoToMessage(messageCommandDto);
        messageRepository.saveAndFlush(message);
        return convertMessageToMessageDto(message);
    }

    public MessageDto updateMessage(int id, MessageCommandDto messageCommandDto){
        Message message = messageRepository.getOne(id);
        message.setMessage(messageCommandDto.getContent());
        messageRepository.saveAndFlush(message);
        return new MessageDto(message.getId(), message.getMessage());
    }

    public Set<MessageDto> getRandomMessagesByNumber(int numberFromUser){
        List<Message> messageList = messageRepository.findAll();
        if(numberFromUser>messageList.size()){
            List<MessageDto> messageDtoList = convertMessageListToMessageDtoList(messageList);
            return convertMessageDtoListToMessageDtoSet(messageDtoList);
        }
        return fillArrayWithRandomMessages(messageList, numberFromUser);
    }

    public Set<MessageDto> convertMessageDtoListToMessageDtoSet(List<MessageDto> messageDtoList){
        Set<MessageDto> messageDtoSet = new HashSet<>();
        for (MessageDto messageDto: messageDtoList){
            messageDtoSet.add(messageDto);
        }
        return messageDtoSet;
    }

    public Set<MessageDto> fillArrayWithRandomMessages(List<Message> messageList, int number){
        int min=1;
        int max=messageList.size();
        Set<MessageDto> messagesDto = new HashSet<>();
       while(messagesDto.size()<number) {
           Message message = messageList.get(getRandomIntInARange(min, max));
           MessageDto messageDto = convertMessageToMessageDto(message);
           messagesDto.add(messageDto);
       }
        return messagesDto;
    }


    public int getRandomIntInARange(int min, int max){
        Random random = new Random();
         return random.ints(min,max)
                 .findFirst()
                 .getAsInt();
    }
}
