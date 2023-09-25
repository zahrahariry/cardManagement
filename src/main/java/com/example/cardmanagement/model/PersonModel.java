package com.example.cardmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonModel {

    private Long id;
    private String nationalId;
    private String phoneNumber;
    private String address;
    private String name;
    private String family;
}
