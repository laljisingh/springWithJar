package com.example.library.controller;

import com.example.library.model.Library;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library-api/")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("find-library/name/{name}")
    public Library findLibraryName(@PathVariable String name) {
        return libraryService.findByName(name);
    }

    @GetMapping("find-all")
    public List<Library> findAllLibrary() {
        return libraryService.findAll();
    }
    @PostMapping("add-library")
    public String AddLibrary(@RequestBody Library library){
        return libraryService.addLibrary(library);
    }
}
