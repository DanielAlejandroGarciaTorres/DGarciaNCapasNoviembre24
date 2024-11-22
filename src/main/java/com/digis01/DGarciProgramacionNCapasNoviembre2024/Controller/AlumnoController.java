/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.Controller;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Alumno")
public class AlumnoController {

    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;
    
    @GetMapping
    public String Inicio(Model model){
        
        Result result = alumnoDAOImplementation.GetAll();
        
        /* intenatar mandar a la vista*/
        
        return "AlumnoIndex";
    }
    
}
