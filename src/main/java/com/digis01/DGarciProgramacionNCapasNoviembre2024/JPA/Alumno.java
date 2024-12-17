package com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Alumno {
    
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalumno")
    private int IdAlumno;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    
//    @Column(name = "fechanacimiento")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date FechaNacimiento;
//    
    @Column(name = "username")
    private String UserName;
    
    @Column(name = "email")
    private String Email;
    
//    @Column(name = "password")
//    private String Password;
//    
    @ManyToOne
    @JoinColumn(name = "idsemestre") //FK
    public Semestre Semestre; 
    
//    @Lob
//    @Column(name = "Imagen")
//    private String Imagen;
    
    public Alumno() {
    }

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.IdAlumno = IdAlumno;
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


//    public Date getFechaNacimiento() {
//        return FechaNacimiento;
//    }
//
//    public void setFechaNacimiento(Date FechaNacimiento) {
//        this.FechaNacimiento = FechaNacimiento;
//    }

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

//    public String getPassword() {
//        return Password;
//    }
//
//    public void setPassword(String Password) {
//        this.Password = Password;
//    }

    public Semestre getSemestre() {
        return Semestre;
    }

    public void setSemestre(Semestre Semestre) {
        this.Semestre = Semestre;
    }
    
//     public String getImagen() {
//        return Imagen;
//    }
//
//    public void setImagen(String Imagen) {
//        this.Imagen = Imagen;
//    }
    
}
