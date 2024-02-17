package com.youdev.crud.controller;

import com.youdev.crud.dtos.Studentdto;
import com.youdev.crud.services.Studentservices;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final Studentservices studentservices;

    public StudentController(Studentservices studentservices) {
        this.studentservices = studentservices;
    }

    @GetMapping("/studentsList")
    public List<Studentdto> getAllStudents() {
        List<Studentdto> studentdtoList = studentservices.getAllStudents();
        return studentdtoList;
    }

    // Afficher un Batiment par ID
    @GetMapping("/{id}")
    public Studentdto getStudentsById(@PathVariable Long id) {
        Studentdto studentdto = studentservices.getStudentsById(id);
        return  studentdto;
    }

    // Créer un nouveau Batiment
    @PostMapping("/createStudent")
    public Studentdto createStudents(@RequestBody Studentdto studentdto) {
        return  studentservices.createStudents(studentdto);
    }

    /*// Mettre à jour un Batiment existant par ID
    @PutMapping("/{id}")
    public String updateBatiment(@PathVariable Long id, @ModelAttribute @Validated BatimentDTO batimentDTO, Model model) {
        BatimentDTO updatedBatiment = batimentService.updateBatiment(id, batimentDTO);
        if (updatedBatiment != null) {
            model.addAttribute("updatedBatiment", updatedBatiment);
            return "redirect:/batiments/" + updatedBatiment.getId();
        } else {
            // Gérer le cas où le Batiment n'existe pas
            return "notFound";
        }
    }*/

    // Supprimer un Student par ID
    @DeleteMapping("/delete/{id}")
    public void  deleteStudent(@PathVariable Long id) {studentservices.deleteStudents(id);}


}
