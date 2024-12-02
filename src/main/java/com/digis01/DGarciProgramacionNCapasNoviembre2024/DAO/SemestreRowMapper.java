/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Semestre;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
public class SemestreRowMapper implements RowMapper<Semestre>{

    @Override
    public Semestre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        
        Semestre semestre = new Semestre();
        
        semestre.setIdSemestre(resultSet.getInt("IdSemestre"));
        semestre.setNombre(resultSet.getString("Nombre"));
        
        return semestre;
    }
    
}
