package com.laljisingh.chatapplicatio.controller;

import com.laljisingh.chatapplicatio.model.ChatHistory;
import com.laljisingh.chatapplicatio.model.Status;
import com.laljisingh.chatapplicatio.model.Users;
import com.laljisingh.chatapplicatio.repository.ChatRepository;
import com.laljisingh.chatapplicatio.repository.StatusRepository;
import com.laljisingh.chatapplicatio.repository.UserRepository;
import com.laljisingh.chatapplicatio.service.ChatService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/chat")
public class ChatHistoryController {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatService chatService;
    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        JSONObject request=new JSONObject(message);

        JSONObject errorList=validateMessage(request);
        if(errorList.isEmpty()){
            ChatHistory chat = setChatHistory(request);
            int chatId = chatService.sendMessage(chat);
            return new ResponseEntity<String>("Successfull :- "+chatId, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/get-message")
    public ResponseEntity<String> getMessageById(@Nullable @RequestParam int senderId){
        JSONObject responce = chatService.getMessage(senderId);
        return new ResponseEntity<String>(responce.toString(), HttpStatus.OK);
    }

    @GetMapping("/get/conversation")
    public ResponseEntity<String> getCOnversationOfTwoUser(@RequestParam int senderId,@RequestParam int receiverId){
        JSONObject responce = chatService.getConversation(senderId, receiverId);
        return new ResponseEntity<String>(responce.toString(), HttpStatus.OK);
    }
    private ChatHistory setChatHistory(JSONObject request) {
        ChatHistory chat = new ChatHistory();
        chat.setMessage(request.getString("message"));
        int sender = request.getInt("sender");
        int receiver = request.getInt("receiver");
        Users sendersObj = userRepository.findById(sender).get();
        Users receiverObj = userRepository.findById(receiver).get();

        chat.setFrom(sendersObj);
        chat.setTo(receiverObj);
        Status status = statusRepository.findById(1).get();
        chat.setStatusId(status);

        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        chat.setCreatedDate(createdTime);
        return chat;
    }

    private JSONObject validateMessage(JSONObject request) {
        JSONObject errorObj = new JSONObject();
        if (!request.has("sender")){
            errorObj.put("sender","missing parameter");
        }
        if (!request.has("receiver")){
            errorObj.put("receiver","missing parameter");
        }
        if (!request.has("message") || request.getString("message").isEmpty()){
            errorObj.put("message","missing parameter or message is Empty");
        }
        return  errorObj;
    }

}
