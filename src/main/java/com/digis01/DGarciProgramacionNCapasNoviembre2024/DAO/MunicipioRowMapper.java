/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Municipio;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
public class MunicipioRowMapper implements RowMapper<Municipio>{

    @Override
    public Municipio mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        
        Municipio municipio = new Municipio();
        
        municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
        municipio.setNombre(resultSet.getString("Nombre"));
        
        return municipio;  
    }
    
}
