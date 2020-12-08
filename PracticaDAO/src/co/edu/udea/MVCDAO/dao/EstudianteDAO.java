//Autor: Santiago Osorio Obando

package co.edu.udea.MVCDAO.dao;
import co.edu.udea.MVCDAO.modelo.EstudianteDTO;

import java.util.ArrayList;

public interface EstudianteDAO {                          //CRUD para gestionar datos
    public boolean createEstudiante(EstudianteDTO estudiante);    //Create
    public EstudianteDTO readEstudiante(String id, char gender);  //Read
    public ArrayList<EstudianteDTO> listarEstudiantes();
    public boolean deleteEstudiante(String id);                   //Delete
    public boolean updateEstudiante(EstudianteDTO estudiante);    //Update
}


