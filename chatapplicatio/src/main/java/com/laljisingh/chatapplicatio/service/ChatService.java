package com.laljisingh.chatapplicatio.service;

import com.laljisingh.chatapplicatio.model.ChatHistory;
import com.laljisingh.chatapplicatio.repository.ChatRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;
    public int sendMessage(ChatHistory chat) {
        ChatHistory save = chatRepository.save(chat);
        return save.getChatId();
    }

    public JSONObject getMessage(int senderId) {
        List<ChatHistory> chatList =  chatRepository.getMessageByuserId(senderId);
        JSONObject responces = new JSONObject();
        if(!chatList.isEmpty()) {
            responces.put("senderId", chatList.get(0).getTo().getUserId());
            responces.put("senderId", chatList.get(0).getTo().getFistName());
        }

        JSONArray receiver = new JSONArray();
        for (ChatHistory chats : chatList) {
            JSONObject receiverObj = new JSONObject();
            receiverObj.put("receiverId",chats.getFrom().getUserId());
            receiverObj.put("receiverName", chats.getFrom().getFistName());
            receiverObj.put("message", chats.getMessage());
            receiver.put(receiverObj);
        }
        responces.put("Results",receiver);

        return  responces;
    }


    public JSONObject getConversation(int senderId, int receiverId) {
        List<ChatHistory> chatList = chatRepository.getConversation(senderId, receiverId);

        JSONObject responce = new JSONObject();
        JSONArray conversation = new JSONArray();

        for (ChatHistory chats : chatList) {
            JSONObject messaObj = new JSONObject();
            messaObj.put("chatId", chats.getChatId());
            messaObj.put("timeStamp", chats.getCreatedDate());
            messaObj.put("sendername", chats.getTo().getFistName());
            messaObj.put("message", chats.getMessage());
            conversation.put(messaObj);
        }
        responce.put("Conversation",conversation);
        return responce;
    }
}
