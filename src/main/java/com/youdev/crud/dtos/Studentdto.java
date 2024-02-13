package com.youdev.crud.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studentdto {
    private Long id;
    private String nom;
    private String prenom;

}
