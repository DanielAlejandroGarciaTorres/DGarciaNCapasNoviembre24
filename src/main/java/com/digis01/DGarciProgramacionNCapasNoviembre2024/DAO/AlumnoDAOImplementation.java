package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;
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
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {
            List<AlumnoDireccion> alumnosDireccion = jdbcTemplate.execute("{CALL AlumnoGetAll(?)}", (CallableStatementCallback<List<AlumnoDireccion>>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                List<AlumnoDireccion> listaAlumnos = new ArrayList<>();
                
                while (resultSet.next()) {
                    AlumnoDireccion alumnoDireccion = new AlumnoDireccion();

                    alumnoDireccion.Alumno = new Alumno();

                    alumnoDireccion.Alumno.setIdAlumno(resultSet.getInt("IdAlumno"));
                    alumnoDireccion.Alumno.setNombre(resultSet.getString("NombreAlumno"));

                    /* Dirección*/
                    listaAlumnos.add(alumnoDireccion);
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

}
