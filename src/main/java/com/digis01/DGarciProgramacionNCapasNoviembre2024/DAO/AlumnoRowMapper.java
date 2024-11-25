package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Alumno;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Colonia;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Direccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Estado;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Municipio;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Semestre;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/*
Interfaz 
- firma de métodos
- Recurso común a implementar en las clases que ocupen de ella
- declaración de metodos abstractos
 */
public class AlumnoRowMapper implements RowMapper<AlumnoDireccion> {

    @Override
    public AlumnoDireccion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
        alumnoDireccion.Alumno = new Alumno();
        alumnoDireccion.Alumno.setIdAlumno(resultSet.getInt("IdAlumno"));
        alumnoDireccion.Alumno.setNombre(resultSet.getString("NombreAlumno"));
        alumnoDireccion.Alumno.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
        alumnoDireccion.Alumno.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));

        alumnoDireccion.Alumno.setUserName(resultSet.getString("UserName"));
        alumnoDireccion.Alumno.setEmail(resultSet.getString("Email"));
        alumnoDireccion.Alumno.Semestre = new Semestre();
        alumnoDireccion.Alumno.Semestre.setIdSemestre(resultSet.getInt("IdSemestre"));
        alumnoDireccion.Alumno.Semestre.setNombre(resultSet.getString("NombreSemestre"));

        alumnoDireccion.Direccion = new Direccion();
        alumnoDireccion.Direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
        alumnoDireccion.Direccion.setCalle(resultSet.getString("Calle"));
        alumnoDireccion.Direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
        alumnoDireccion.Direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
        alumnoDireccion.Direccion.Colonia = new Colonia();
        alumnoDireccion.Direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
        alumnoDireccion.Direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
        alumnoDireccion.Direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
        alumnoDireccion.Direccion.Colonia.Municipio = new Municipio();
        alumnoDireccion.Direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
        alumnoDireccion.Direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
        alumnoDireccion.Direccion.Colonia.Municipio.Estado = new Estado();
        alumnoDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
        alumnoDireccion.Direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
        
        return alumnoDireccion;

    }

}
