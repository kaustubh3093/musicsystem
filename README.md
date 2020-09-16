# musicsystem
Name: Kaustubh Singh

-----------------------------------------------------------------------
-----------------------------------------------------------------------
Following are the commands and the instructions to run the project.

####################Instruction to Import ########################
1) Clone the git project into your local directory 
2) Import the project into any IDE of your choice 

####################  Instruction to Run  ########################
1) Go inside the application properties and change the value of hibernate ddl from validate to create
2) Right click on MusicsystemApplication.java class
3) Run As -> Java application
4) Close the Spring boot app
5) Again go inside the application properties file and change hibernate ddl to validate
6) Right click on MusicsystemApplication.java class
7) Run As -> Java application
8) It will start your application and I have set port to 7070 which is under resouces --> application.properties file

######################### Endpoints ##############################
1) http://localhost:7070/api/main 
--> It will start your project and you will see two link one to register and one to login

2) Once you click on the registration link you can register using the form in the next page
3) When you click on login and enter the credentials and if there is no user present then it will throw an error page
4) Else if there is user in the login table and with normal user role then you will go to the next page
5) If he is admin you will go to the admin page
6) I have not implemented normal and admin page and I have commented the steps needed to get done.

##################### Desceiption #################################
--> Worked with Spring Boot app to initialize the project
--> Used MySQL to persist the data
