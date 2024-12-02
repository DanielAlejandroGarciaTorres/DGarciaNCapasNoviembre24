/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.EstadoRowMapper;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO.IMunicipioDAO;
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
public class MunicipioDAOImplementation implements IMunicipioDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetMunicipioByEstado(int IdEstado) {
        Result result = new Result();
        /*try*/
        jdbcTemplate.execute("{CALL GetMunicipioByEstado(?,?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
            callableStatement.setInt(1, IdEstado);
            callableStatement.registerOutParameter(2, Types.REF_CURSOR);
            callableStatement.execute();
            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
            MunicipioRowMapper municipioRowMapper = new MunicipioRowMapper();
            result.objects = new ArrayList<>();
            while (resultSet.next()) {
                result.objects.add(municipioRowMapper.mapRow(resultSet, resultSet.getRow()));
            }
            result.correct = true;
            return result.correct;
        }
        );
        /*catch*/
        
        return result;
    }
    
    
    
}
