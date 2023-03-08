package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Library {
    private String libraryName;
    private String  libraryAddress;
    private String  libraryNumber;
    private String libraryFacilities;
}
