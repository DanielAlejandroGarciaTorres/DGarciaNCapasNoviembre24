/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.Controller;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.ResultExcel;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ALIEN 34
 */
@Controller
@RequestMapping("/CargaMasiva")
public class CargaMasivaController {
    
    @GetMapping
    public String Inicio(){
        return "CargaMasivaIndex";
    }
    
    @PostMapping
    public String Inicio(@RequestParam MultipartFile archivo, Model model, HttpSession session){
        if (archivo != null && !archivo.isEmpty()) {
            //procesar
//            String archivonombre = archivo.getOriginalFilename();
//            String[] archivoCortado = archivonombre.split("\\.");
//            String extencionArchivo = archivoCortado[1];
            
            String fileExtension  = archivo.getOriginalFilename().split("\\.")[1];
            
            if (fileExtension.equals("txt")){
                ProcesarArchivo(archivo);
            } else { // xlsx
                String root = System.getProperty("user.dir"); //ruta base del proecto
                String path = "src/main/resources/static/archivos";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String absolutePath = root + "/" + path + "/" + fecha + archivo.getOriginalFilename();
                //try /throw / throws
                try {
                    archivo.transferTo(new File(absolutePath)); // copio el archivo en la capeta archivos
                    List<AlumnoDireccion> listaAlumno = LecturaArchivo(new File(absolutePath));
                    List<ResultExcel> listaErrores = ValidarDatos(listaAlumno);
                    
                    if (listaErrores.isEmpty()) {
                        session.setAttribute("path", absolutePath);
                        model.addAttribute("listaErrores", listaErrores);
                        model.addAttribute("archivoCorrecto", true);
                    }else {
                        model.addAttribute("listaErrores", listaErrores);
                        model.addAttribute("archivoCorrecto", false);
                    }
                    
                } catch (Exception ex) {
                    
                }
                
            }
            
        } else  {
            //Ingresa un archivo para mostrar
        }
        
        return "CargaMasivaIndex";
    }

    @GetMapping("/procesar")
    public String CargaMasiva (HttpSession session){
        
        String absolutePath = session.getAttribute("path").toString();
        
        List<AlumnoDireccion> alumnosDireccion = LecturaArchivo(new File(absolutePath));
        
        
        /*
        agregarlos
        */
        session.removeAttribute("path");
        
        return "CargaMasivIndex";
    }
    
    private boolean ProcesarArchivo(MultipartFile archivo){
        
        try{
            InputStream inputStream = archivo.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            String linea = "";
            
            while((linea = bufferedReader.readLine()) != null){
                String[] campos = linea.split("\\|");
                
                AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
                alumnoDireccion.Alumno = new Alumno();
                alumnoDireccion.Alumno.setNombre(campos[0]);
                alumnoDireccion.Alumno.setApellidoPaterno(campos[1]);
                
                //alumnoDAOImplementation.Add(alumnoDireccion);
            }
            
        }catch(Exception ex){
            return false;
        }
        
        return true;
    }

    private List<AlumnoDireccion> LecturaArchivo(File archivo) {
        List<AlumnoDireccion> listaAlumnos = new ArrayList<>();
        
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(archivo))){
            Sheet workSheet = workbook.getSheetAt(0);
            
            for (Row row : workSheet) {
                AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
                alumnoDireccion.Alumno = new Alumno();
                alumnoDireccion.Alumno.setNombre(row.getCell(0).toString());
                alumnoDireccion.Alumno.setApellidoPaterno(row.getCell(1).toString());
                
                //alumnoDireccion.Alumno.setFechaNacimiento(row.getCell(3).getDateCellValue());
                  
                listaAlumnos.add(alumnoDireccion);
            }
        }catch (Exception ex){
            
        }
        
        return listaAlumnos;
        
    }

    private List<ResultExcel> ValidarDatos(List<AlumnoDireccion> listaAlumno) {
        int fila = 1;
        String errorMessage = "";
        List<ResultExcel> listaErrores = new ArrayList<>();
        
        for (AlumnoDireccion alumnoDireccion : listaAlumno) {
            if (alumnoDireccion.Alumno.getNombre().equals("")) {
                errorMessage = "Nombre sin información";
                listaErrores.add(new ResultExcel(fila, errorMessage));
            }
            if (alumnoDireccion.Alumno.getApellidoPaterno().equals("")){
                errorMessage = "Apellido paterno sin información";
                listaErrores.add(new ResultExcel(fila, errorMessage));
            }
            
            fila++;
        }
        
        return listaErrores;
    }
}
