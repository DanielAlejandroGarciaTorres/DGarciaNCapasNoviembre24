package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Colonia;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Direccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Estado;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Municipio;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Semestre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // encargado de guardar información en la base de datos
public class AlumnoDAOImplementation implements IAlumnoDAO {

    @Autowired // inyección de dependencias (field, setter, constructor)
    private JdbcTemplate jdbcTemplate; // interfaz de jdbc
    
    @Autowired // conexiones por medio de JPA
    private EntityManager entityManager;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {
            List<AlumnoDireccion> alumnosDireccion = jdbcTemplate.execute("{CALL AlumnoGetAll(?)}", (CallableStatementCallback<List<AlumnoDireccion>>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                List<AlumnoDireccion> listaAlumnos = new ArrayList<>();
                AlumnoRowMapper alumnoRowMapper = new AlumnoRowMapper();
                while (resultSet.next()) {
                    listaAlumnos.add(alumnoRowMapper.mapRow(resultSet, resultSet.getRow()));
                }

                return listaAlumnos;
            });

            result.correct = true;
            result.object = alumnosDireccion;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result Add(AlumnoDireccion alumnoDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result GetById(int IdALumno) {
        Result result = new Result();

        try {
            AlumnoDireccion alumnoDireccion = jdbcTemplate.execute("{CALL AlumnoGetById(?,?)}", (CallableStatementCallback<AlumnoDireccion>) callableStatement -> {
                callableStatement.setInt(1, IdALumno);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
                
                AlumnoRowMapper alumnoRowMapper = new AlumnoRowMapper();
                if (resultSet.next()) {
                    AlumnoDireccion alumnoDireccionRespuesta = alumnoRowMapper.mapRow(resultSet, resultSet.getRow());
                    return alumnoDireccionRespuesta;
                } else {
                    return null;
                }
            });
            result.object = alumnoDireccion;
            result.correct = true;
            
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }
    
    public Result GetAllJPA(){
        Result result = new Result();
        
        try{
            result.objects = new ArrayList<>();
            //JPQL Java persistence query language
            
            TypedQuery<com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Alumno> queryAlumno = entityManager.createQuery("FROM Alumno", com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Alumno.class);
            List<com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Alumno> listaAlumnos = queryAlumno.getResultList();
            
            
            for (com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Alumno alumnoJPA : listaAlumnos) {
                AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
                alumnoDireccion.Alumno = new Alumno();
                alumnoDireccion.Alumno.setIdAlumno(alumnoJPA.getIdAlumno());
                alumnoDireccion.Alumno.setNombre(alumnoJPA.getNombre());
                
                try {
                TypedQuery<com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Alumno.IdAlumno = :pIdAlumno", com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Direccion.class);
                queryDireccion.setParameter("pIdAlumno", alumnoJPA.getIdAlumno());
                com.digis01.DGarciProgramacionNCapasNoviembre2024.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();
                
                alumnoDireccion.Direccion = new Direccion();
                
                alumnoDireccion.Direccion.setCalle(direccionJPA.getCalle());
                } catch (Exception ex){
                    continue;
                } finally {
                    result.objects.add(alumnoDireccion);
                }
                
                /*por cada elemento de alumno*/
                
                //TypedQuery Direccion "From Direccion donde idAlumno = alumnoJPA.IdAlumno"
                
                    // si tiene una direccion la asgino
                    // si no tiene marca un error y se rompe codigo :c 
            }
            
            System.out.println(",");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

}
