/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Colonia;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
public class ColoniaRowMapper implements RowMapper<Colonia>{

    @Override
    public Colonia mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Colonia colonia = new Colonia();
        
        colonia.setIdColonia(resultSet.getInt("IdColonia"));
        colonia.setNombre(resultSet.getString("Nombre"));
        colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
        
        return colonia;
    }
    
}
