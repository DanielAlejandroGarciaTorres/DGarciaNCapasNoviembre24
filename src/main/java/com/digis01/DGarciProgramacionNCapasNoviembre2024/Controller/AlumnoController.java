/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.Controller;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.ColoniaDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.EstadoDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.MunicipioDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.SemestreDAOImplementation;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Colonia;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Direccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Municipio;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Semestre;
import jakarta.validation.Valid;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Alumno")
public class AlumnoController {

    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;

    @Autowired
    private SemestreDAOImplementation semestreDAOImplementation;

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @GetMapping
    public String Inicio(Model model) {
        // Model - mandar información por medio de un modelo a la vista
//        Result result = alumnoDAOImplementation.GetAll();
        Result result = alumnoDAOImplementation.GetAllJPA();
        Alumno alumnoBusqueda = new Alumno();
        alumnoBusqueda.Semestre = new Semestre();
        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
//        model.addAttribute("alumnosDireccion", (List<AlumnoDireccion>) result.object);
        model.addAttribute("alumnosDireccion", result.objects);

        return "AlumnoIndex";
    }

    @PostMapping
    public String Inicio(Model model, @ModelAttribute Alumno alumnoBusqueda) {
        // Model - mandar información por medio de un modelo a la vista
        Result result = alumnoDAOImplementation.GetAll(); // GetAllDinamico
        // Result result = alumnoDAOImplementation.GetAllDinamico(alumoBusqueda)
        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
        model.addAttribute("alumnosDireccion", (List<AlumnoDireccion>) result.object);

        return "AlumnoIndex";
    }

    @GetMapping("/form/{IdAlumno}")
    public String Formulario(@PathVariable int IdAlumno, Model model) {

        Result resultSemestre = semestreDAOImplementation.GetAll();
        
        model.addAttribute("semestres", resultSemestre.objects);

        model.addAttribute("estados", estadoDAOImplementation.GetAll().objects);

        if (IdAlumno == 0) { // Agregar
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.Semestre = new Semestre();
            alumnoDireccion.Direccion = new Direccion();
            alumnoDireccion.Direccion.Colonia = new Colonia();

            model.addAttribute("alumnoDireccion", alumnoDireccion);
        } else { // editar

            Result result = alumnoDAOImplementation.GetById(IdAlumno);
            AlumnoDireccion alumnoDireccion = (AlumnoDireccion) result.object;
            model.addAttribute("municipios", municipioDAOImplementation.GetMunicipioByEstado(alumnoDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()).objects);
            model.addAttribute("alumnoDireccion", (AlumnoDireccion) result.object);

        }

        return "AlumnoForm";
    }

    @PostMapping("/form")
    public String Formulario(@Valid @ModelAttribute AlumnoDireccion alumnoDireccion,
            BindingResult bindingResult,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            Model model
    ) {
//        alumnoDAOImplementation.Add(alumnoDireccion);

        if (bindingResult.hasErrors()) {
            model.addAttribute("alumnoDireccion", alumnoDireccion);
            return "AlumnoForm";
        }

        try { // convertir a base 64
            if(!imagenFile.isEmpty()){
                byte[] bytes =  imagenFile.getBytes();
                String imagenBase64 = Base64.getEncoder().encodeToString(bytes);
                
                alumnoDireccion.Alumno.setImagen(imagenBase64);
            }
            
        } catch (Exception ex) {
            System.out.println("----");
        }

        if (alumnoDireccion.Alumno.getIdAlumno() == 0) {
            /*Agregar*/
        } else {

        }

        return "redirect:/Alumno";
    }

    @GetMapping("/GetMunicipioByEstado/{IdEstado}")
    @ResponseBody // JSON
    public Result GetMunicipioByEstado(@PathVariable int IdEstado) {

        return municipioDAOImplementation.GetMunicipioByEstado(IdEstado);
    }

    @GetMapping("/GetColoniaByMunicipio/{IdMunicipio}")
    @ResponseBody
    public Result GetColoniaByMunicipio(@PathVariable int IdMunicipio) {
        return coloniaDAOImplementation.GetColoniaByMunicipio(IdMunicipio);
    }

}
