/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.ML;

/**
 *
 * @author ALIEN 34
 */
public class Alumno {
    
    private int IdAlumno;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String UserName;
    private String Email;
    // propiedad de navegación 
    public Semestre Semestre;

    public Alumno(){
        
    }
    
    public Alumno(String Nombre, String ApellidoPaterno, String ApellidoMaterno) {
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
    }
    
    public void setIdAlumno(int idAlumno){
        this.IdAlumno = idAlumno;
    }
    
    public int getIdAlumno(){
        return this.IdAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
