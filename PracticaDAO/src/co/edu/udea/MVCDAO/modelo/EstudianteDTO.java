//Autor: Santiago Osorio Obando

package co.edu.udea.MVCDAO.modelo;

public class EstudianteDTO {
    private String nombre;
    private String apellidos;
    private String id;
    private char genero;
    
    public EstudianteDTO(){}
    
    public EstudianteDTO(String name, String lastName, String ident, char gender){
        nombre = name;
        apellidos = lastName;
        id = ident;
        genero = gender;
    }
    
    public void setNombre(String name){
        nombre = name;
    } 
    public String getNombre(){
        return nombre;
    }
    
    public void setApellidos(String lastName){
        apellidos = lastName;
    }
    public String getApellidos(){
        return apellidos;
    }
    
    public void setId(String ident){
        id = ident;
    }
    public String getId(){
        return id;
    }
    
    public void setGenero(char gender){
        genero = gender;
    }
    public char getGenero(){
        return genero;
    }
    
    @Override
    public String toString(){    //Retorna nombre:apellidos:ID:género separados de esta manera
        return this.getNombre() + ":" 
                + this.getApellidos() + ":" 
                + this.getId() + ":" 
                + this.getGenero();
    }
}
