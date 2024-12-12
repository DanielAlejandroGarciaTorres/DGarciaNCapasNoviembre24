/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.Controller;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Controller;
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
    public String Inicio(@RequestParam MultipartFile archivo){
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
                } catch (Exception ex) {
                    
                }
                
            }
            
        } else  {
            //Ingresa un archivo para mostrar
        }
        
        return "CargaMasivaIndex";
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

    private List<AlumnoDireccion> LecturaArchivo(File file) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
