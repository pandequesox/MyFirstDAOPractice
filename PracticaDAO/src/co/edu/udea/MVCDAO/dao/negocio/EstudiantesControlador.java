//Autor: Santiago Osorio Obando

package co.edu.udea.MVCDAO.dao.negocio;

import co.edu.udea.MVCDAO.dao.implementacion.EstudianteDAOFile;
import co.edu.udea.MVCDAO.dao.implementacion.EstudianteDAOList;
import co.edu.udea.MVCDAO.modelo.EstudianteDTO;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EstudiantesControlador {
    private static EstudianteDAOList daoList;
    private static EstudianteDAOFile daoFile;
    
    public static void main(String[] args) throws IOException {
        implementarEstudianteDAO();
    }
    
    //Método ejecutado en el main() para mostrar la GUI
    public static void implementarEstudianteDAO() throws IOException{
        daoFile = new EstudianteDAOFile();
        daoList = new EstudianteDAOList(daoFile.initializeStudentList());
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        
        while(loop){
            String option = JOptionPane.showInputDialog("Welcome to the Student DataBase\n\n"
                    + "Please select want you want to do:\n\n" 
                    + "1. Show a list of the current students\n"
                    + "2. Search student by ID(You can change his/her information or delete it from the database if you want)\n"
                    + "3. Register new student\n\n"
                    + "0. Exit");
            switch(option){
                case "1":
                    //Check students
                    option1ListOfStudents();
                    daoFile.closeWrtiterAndReader();
                    loop = false;
                    break;
                case "2":
                    option2SearchStudent();
                    break;
                case "3":
                    option3AddStudent();
                    break;
                default:
                    daoFile.closeWrtiterAndReader();
                    loop = false;
                    break;
            }
        }
    }
    
    //Métodos que encierran las opciones que el usuario puede elegir en la GUI principal
    public static void option1ListOfStudents(){
        StudentDBGUI.setListOfStudents(daoList.listarEstudiantes());
        StudentDBGUI.main(null);
    }
    public static void option2SearchStudent(){
        boolean loop = true;
        
        while(loop){
            //pregunta primero el género para reducir la búsqueda haciendo un filtro previamente
            String option = JOptionPane.showInputDialog("Do you know the gender of the student you're looking for?\n\n" 
                    + "If you do:\n" 
                    + "M - Male\n" 
                    + "F - Female\n\n" 
                    + "0 - I don't know it");
            if(!option.equals("M") && !option.equals("F") && !option.equals("0")){
                JOptionPane.showMessageDialog(null, "Please enter valid data");
                continue;
            }
            
            String id = JOptionPane.showInputDialog("Now type the student's ID");
            
            //Invoca al método que llama a los métodos de EstudianteDAOList y EstudianteDAOFile
            loop = searchStudentMenu(option.charAt(0), id);
            
        }
    }
    public static void option3AddStudent(){
        EstudianteDTO estudiante = new EstudianteDTO();
        
        estudiante.setNombre(JOptionPane.showInputDialog("Student's name: "));
        estudiante.setApellidos(JOptionPane.showInputDialog("Student's last name: "));
        estudiante.setId(JOptionPane.showInputDialog("Student's ID: "));
        estudiante.setGenero(JOptionPane.showInputDialog("Student's gender: ").charAt(0));
        
        daoList.createEstudiante(estudiante);
        daoFile.createEstudiante(estudiante);
    }
    
    //Método para buscar al estudiante y manipular su información con EstudianteDAOList y EstudianteDAOFile
    public static boolean searchStudentMenu(char gender, String id){
        EstudianteDTO estudiante = daoList.readEstudiante(id, gender);
        String infoEstudiante = "Name: " + estudiante.getNombre() + "\n" 
                + "Last Name: " + estudiante.getApellidos() + "\n" 
                + "ID: " + estudiante.getId() + "\n" 
                + "Gender: " + (estudiante.getGenero() == 'M' ? "Male" : "Female") + "\n";
        
        String option = JOptionPane.showInputDialog("Student Info:\n\n" + infoEstudiante + "\n-----------------------\n"
                + "What do you want to do next?\n\n" 
                + "1. Update Student's info\n" 
                + "2. Delete Student from database\n"
                + "0. Go back to startup menu");
        
        //Para actualizar la información
        if(option.equals("1")){
            estudiante.setNombre(JOptionPane.showInputDialog("Current student's name: " + estudiante.getNombre() 
                    + "\nType the new one:"));
            estudiante.setApellidos(JOptionPane.showInputDialog("Current student's last name: " + estudiante.getApellidos()
                    + "\nType the new one:"));
            estudiante.setGenero(JOptionPane.showInputDialog("Current student's gender: " + estudiante.getGenero()
                    + "\nType the new one:").charAt(0));
            
            daoList.updateEstudiante(estudiante);
            daoFile.updateEstudiante(estudiante);
        }
        //Para eliminar el estudiante
        else if(option.equals("2")){
            daoList.deleteEstudiante(id);
            daoFile.deleteEstudiante(id);
        }
        //Retorna false para cerrar el ciclo del método option2SearchStudent()
        return false;
    }
}
