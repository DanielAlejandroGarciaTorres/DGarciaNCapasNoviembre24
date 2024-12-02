/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN 34
 */
@Repository
public class EstadoDAOImplementation implements IEstadoDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {
        Result result = new Result();
        /*try*/
        jdbcTemplate.execute("{CALL EstadoGetAll(?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
            callableStatement.registerOutParameter(1, Types.REF_CURSOR);
            callableStatement.execute();
            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            EstadoRowMapper estadoRowMapper = new EstadoRowMapper();
            result.objects = new ArrayList<>();
            while (resultSet.next()) {
                result.objects.add(estadoRowMapper.mapRow(resultSet, resultSet.getRow()));
            }
            result.correct = true;
            return result.correct;
        }
        );
        /*catch*/
        
        return result;
    }
    
    
    
}
