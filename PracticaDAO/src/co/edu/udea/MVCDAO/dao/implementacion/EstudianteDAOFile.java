//Autor: Santiago Osorio Obando

package co.edu.udea.MVCDAO.dao.implementacion;
import co.edu.udea.MVCDAO.dao.EstudianteDAO;
import co.edu.udea.MVCDAO.modelo.EstudianteDTO;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstudianteDAOFile implements EstudianteDAO{
    BufferedReader inputStream;
    PrintWriter output;
    
    public EstudianteDAOFile(){
        try{  //Inicializamos nuestro BufferedReader para leer el archivo con los estudiantes existentes
            inputStream = new BufferedReader(new FileReader("studentDatabase.txt"));
            output = new PrintWriter(new FileWriter("studentDatabase.txt", true));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    //Método para leer los archivos y crear el ArrayList de estudiantes para inicializar el programa en EstudianteDAOList
    public ArrayList<EstudianteDTO> initializeStudentList() throws IOException{
        ArrayList<EstudianteDTO> studentList = new ArrayList<EstudianteDTO>();
        String iterador;
        
        while((iterador = inputStream.readLine()) != null){
            String[]info = iterador.split(":");
            
            //info[0] = Nombre ; info[1] = Apellidos ; info[2] = ID ; info[3] = Género
            studentList.add(new EstudianteDTO(info[0], info[1], info[2], info[3].charAt(0)));
        }
        return studentList;
    }
    
    //Método para crear un 2Darray y poder operar con los datos de los estudiantes en esta clase
    public String[][] returnDataArray() throws IOException{
        List<String[]> lines = new ArrayList<String[]>();
        String iterador;
        
        //Inicializamos de nuevo el Buffer para que lea las líneas desde el inicio
        inputStream = new BufferedReader(new FileReader("studentDatabase.txt"));
        
        while((iterador = inputStream.readLine()) != null){
            String[]info = iterador.split(":");
            lines.add(info);
        }
        
        String[][]matrix = new String[lines.size()][4];
        matrix = lines.toArray(matrix);
        
        return matrix;
    }
    
    //Método para cerrar los Reader y Writer
    public boolean closeWrtiterAndReader() throws IOException{
        try{
            inputStream.close();
            output.close();
            return true;
        }
        catch(IOException e){
            System.out.println(e);
            return false;
        }       
    }
    
    //Este método convierte nuestro FileWriter en sobreescribible o no sobreescribible
    public void makePrintWriterAppendable(boolean appendable) throws IOException{ 
        output.close();                                                     
        output = new PrintWriter(new FileWriter("studentDatabase.txt", appendable));
    }
    
    //Métodos CRUDL
    @Override
    public boolean createEstudiante(EstudianteDTO estudiante) {
        try{
            output.println(estudiante);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public EstudianteDTO readEstudiante(String id, char gender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EstudianteDTO> listarEstudiantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEstudiante(String id) {
        try {
            String[][]studentList = returnDataArray();
            boolean studentFound = false;
            StringBuilder wholeText = new StringBuilder(); 
            
            for(int i = 0; i < studentList.length; i++){
                if(id.equals(studentList[i][2])){  //studentList[i][2] es el ID del estudiante [i]
                    studentFound = true;
                }
                //Si no coincide con el estudiante construye un StringBuilder con los datos de los demás estudiantes
                else{
                    wholeText.append(studentList[i][0] + ":" //[0] - Nombre
                            + studentList[i][1] + ":"        //[1] - Apellidos
                            + studentList[i][2] + ":"        //[2] - ID
                            + studentList[i][3] + "\n");     //[3] - Género
                }
            }
            
            //Si encuentra al estudiante, sobreescribe de nuevo el archivo con el StringBuilder donde fue omitido el estudiante
            //eliminado
            if(studentFound){
                makePrintWriterAppendable(false);  //Se adapta el archivo para ser sobreescrito de cero
                output.print(wholeText.toString());
                makePrintWriterAppendable(true);
            }
            
            return studentFound;
        } 
        
        catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean updateEstudiante(EstudianteDTO estudiante) {
        try {
            String[][]studentList = returnDataArray();
            boolean studentFound = false;
            StringBuilder wholeText = new StringBuilder(); 
            
            for(int i = 0; i < studentList.length; i++){
                if(estudiante.getId().equals(studentList[i][2])){  //studentList[i][2] es el ID del estudiante [i]
                    studentFound = true;
                    wholeText.append(estudiante + "\n");
                }
                //Si no coincide con el estudiante construye un StringBuilder con los datos de los demás estudiantes
                else{
                    wholeText.append(studentList[i][0] + ":"
                            + studentList[i][1] + ":" 
                            + studentList[i][2] + ":" 
                            + studentList[i][3] + "\n");
                }
            }
            
            //Si encuentra al estudiante, sobreescribe de nuevo el archivo con el StringBuilder donde fue omitido el estudiante
            //actualizado
            if(studentFound){
                makePrintWriterAppendable(false);
                output.print(wholeText.toString());
                makePrintWriterAppendable(true);
            }
            return studentFound;
        } 
        
        catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
}
