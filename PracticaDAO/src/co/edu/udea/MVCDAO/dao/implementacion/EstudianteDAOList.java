//Autor: Santiago Osorio Obando

package co.edu.udea.MVCDAO.dao.implementacion;
import co.edu.udea.MVCDAO.dao.EstudianteDAO;
import co.edu.udea.MVCDAO.modelo.EstudianteDTO;

import java.util.ArrayList;

public class EstudianteDAOList implements EstudianteDAO{
    private ArrayList<EstudianteDTO> listaEstudiantes;
    //Lista de estudiantes hombres y mujeres para hacer un filtro de búsqueda
    private ArrayList<EstudianteDTO> hombres;
    private ArrayList<EstudianteDTO> mujeres;
    
    public EstudianteDAOList(ArrayList<EstudianteDTO> lista){
        //Inicializamos todas las listas donde se almacenarán los estudiantes
        listaEstudiantes = lista;
        hombres = new ArrayList<EstudianteDTO>();
        mujeres = new ArrayList<EstudianteDTO>();
        
        for(EstudianteDTO estudiante : listaEstudiantes){
            if(estudiante.getGenero() == 'M'){
                hombres.add(estudiante);
            }
            else{
                mujeres.add(estudiante);
            }
        }
    }

    //Métodos CRUDL
    @Override
    public boolean createEstudiante(EstudianteDTO estudiante) {
        if(estudiante.getGenero() == 'M'){
            hombres.add(estudiante);
        }
        else{
            mujeres.add(estudiante);
        }
        
        return listaEstudiantes.add(estudiante);
    }

    @Override
    public EstudianteDTO readEstudiante(String id, char gender) {
        switch(gender){
            case 'M':
                for(EstudianteDTO estudiante : hombres){
                    if(estudiante.getId().equals(id)){
                        return estudiante;
                    }
                }
                break;
            case 'F':
                for(EstudianteDTO estudiante : mujeres){
                    if(estudiante.getId().equals(id)){
                        return estudiante;
                    }
                }
                break;
            
            //En caso de no saber el género, simplemente buscará en la lista principal   
            default:
                for(EstudianteDTO estudiante : listaEstudiantes){
                    if(estudiante.getId().equals(id)){
                        return estudiante;
                    }
                }
                break;
        }
        
        return null;
    }
    @Override
    public ArrayList<EstudianteDTO> listarEstudiantes() {
        return listaEstudiantes;
    }
    @Override
    public boolean deleteEstudiante(String id) {
        for(EstudianteDTO estudiante : listaEstudiantes){
            if(estudiante.getId().equals(id)){
                listaEstudiantes.remove(estudiante);
                
                //Luego elimina al estudiante de la lista de hombres o mujeres de acuerdo a su género
                if(estudiante.getGenero() == 'M'){
                    hombres.remove(estudiante);
                }
                else{
                    mujeres.remove(estudiante);
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean updateEstudiante(EstudianteDTO estudianteAConsultar) {
        for(EstudianteDTO estudiante : listaEstudiantes){
            if(estudiante.getId().equals(estudianteAConsultar.getId())){
                listaEstudiantes.remove(estudiante);
                listaEstudiantes.add(estudianteAConsultar);
                
                //Luego actualiza también las listas independientes de hombres y mujeres
                if(estudiante.getGenero() == 'M'){
                    hombres.remove(estudiante);
                    hombres.add(estudianteAConsultar);
                }
                else{
                    mujeres.remove(estudiante);
                    mujeres.add(estudianteAConsultar);
                }
                return true;
            }
        }
        return false;
    }
}
