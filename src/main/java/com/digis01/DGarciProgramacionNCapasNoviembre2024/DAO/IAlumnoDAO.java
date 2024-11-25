package com.digis01.DGarciProgramacionNCapasNoviembre2024.DAO;

import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.AlumnoDireccion;
import com.digis01.DGarciProgramacionNCapasNoviembre2024.ML.Result;

/*firma de métodos*/

public interface IAlumnoDAO {
    
    Result GetAll();
    
    Result Add(AlumnoDireccion alumnoDireccion);
}
