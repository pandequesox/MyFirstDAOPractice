Made by Santiago Osorio Obando, student of University of Antioquia

In this project you will find a simple implementation of the DAO(Data Access Object) desing pattern.
The program consists in a little and simple database made to save the information of a group of students.

The program's files are:

-class_EstudianteDTO: A simple student class with basic student info; name, ID, gender...
-interface_EstudianteDAO: An interface with the program's basic methods: Create, Read, Update, Delete student (CRUD).
-class_EstudianteDAOList: A class that implements EstudianteDAO interface to manipulate the list of students.
-studentDatabase.txt: File where the student's data is saved.
-class_EstudianteDAOFile: A class that implements EstudianteDAO interface to read and save all the data in a .txt file.
-mainClass_EstudiantesControlador: Class that implements all the classes metioned before, creating a GUI made to manipulate the data.
-mainClass_JFrameForm_StudentDBGUI: Class where all the student's info is shown in a JTable.