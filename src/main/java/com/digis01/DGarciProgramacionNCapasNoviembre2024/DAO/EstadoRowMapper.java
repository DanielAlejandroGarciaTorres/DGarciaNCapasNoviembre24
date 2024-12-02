/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Estado;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
public class EstadoRowMapper implements RowMapper<Estado>{

    @Override
    public Estado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Estado estado = new Estado();
        
        estado.setIdEstado(resultSet.getInt("IdEstado"));
        estado.setNombre(resultSet.getString("Nombre"));
        
        return estado;
    }
    
    
}
