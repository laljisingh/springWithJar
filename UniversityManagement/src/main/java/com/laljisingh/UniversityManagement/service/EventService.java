package com.laljisingh.UniversityManagement.service;

import com.laljisingh.UniversityManagement.model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private static ArrayList<Event> event=new ArrayList<>();
   static {
       event.add(new Event(1,"welcome","uttar pradesh","15-03-2023","01:30 PM","02:00 PM"));
   }

    public String addEvent(Event newEvent) {
        if(event.add(newEvent)){
            return "Event Added Successfully";
        }else{
            return "Event Not Added";
        }
    }
    public Event findEvent(int id){
        for(int i=0 ; i<event.size() ; i++){
            if(event.get(i).getEventId()==id){
                return event.get(i);
            }
        }
        return null;
    }
    public String updateEvent(Event newEvent, int id) {
        Event et=findEvent(id);
        et.setEventId(newEvent.getEventId());
        et.setEventName(newEvent.getEventName());
        et.setLocationOfEvent(newEvent.getLocationOfEvent());
        et.setDate(newEvent.getDate());
        et.setStartTime(newEvent.getStartTime());
        et.setEndTime(newEvent.getEndTime());
        return "Updated Successfully";
    }

    public String deleteEvent(int eventId) {
        Event st=findEvent(eventId);
        if(event.remove(st)){
            return "deleted";
        }else{
            return "Event Not Found";
        }
    }

    public List<Event> getAllEventByDate(String date) {
       ArrayList<Event> eventDate=new ArrayList<>();
       for(int i=0 ; i<event.size() ; i++){
           if(event.get(i).getDate().equals(date)){
               eventDate.add(event.get(i));
           }
       }
       return eventDate;
    }
}
