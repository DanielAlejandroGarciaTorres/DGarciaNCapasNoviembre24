/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.Controller;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Colonia;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Direccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Semestre;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Alumno")
public class AlumnoController {

    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;
    
    @GetMapping
    public String Inicio(Model model){
        // Model - mandar información por medio de un modelo a la vista
        Result result = alumnoDAOImplementation.GetAll();
        
        model.addAttribute("alumnosDireccion", (List<AlumnoDireccion>) result.object);
        
        return "AlumnoIndex";
    }
    
    @GetMapping("/form/{IdAlumno}")
    public String Formulario(@PathVariable int IdAlumno, Model model){
        
        if (IdAlumno == 0 ) { // Agregar
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.Semestre = new Semestre();
            alumnoDireccion.Direccion = new Direccion();
            alumnoDireccion.Direccion.Colonia = new Colonia();
            
            model.addAttribute("alumnoDireccion",alumnoDireccion);
        } else { // editar
            
            Result result = alumnoDAOImplementation.GetById(IdAlumno);
            
            model.addAttribute("alumnoDireccion", (AlumnoDireccion) result.object);
        
        }
        
        
        return "AlumnoForm";
    }
    
    @PostMapping("/form")
    public String  Formulario(@Valid @ModelAttribute AlumnoDireccion alumnoDireccion,
            BindingResult bindingResult,
            Model model
            ) {
//        alumnoDAOImplementation.Add(alumnoDireccion);

        if(bindingResult.hasErrors()){
            model.addAttribute("alumnoDireccion", alumnoDireccion);
            return "AlumnoForm";
        }
    
        if(alumnoDireccion.Alumno.getIdAlumno() == 0) {
            /*Agregar*/
        } else  {
            
        }
        
        return "redirect:/Alumno";
    }
    
    
}
