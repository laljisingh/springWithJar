package com.example.library.service;

import com.example.library.model.Library;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private static List<Library> libraryList=new ArrayList<>();
    static {
            libraryList.add(new Library("Learner Library","Allahabad Uttar Pradesh","0989556252","Fully Ac And Best Environmet"));
    }
    public List<Library> findAll() {
        return libraryList;
    }

    public Library findByName(String name) {
        for(int i=0 ; i<libraryList.size() ; i++){
            if (libraryList.get(i).getLibraryName().equals(name)) return libraryList.get(i);
        }
        return null;
    }

    public String addLibrary(Library library) {
        if(libraryList.add(library)){
            return "Library Added Successfully";
        }else{
            return "Library not Added";
        }
    }
}
