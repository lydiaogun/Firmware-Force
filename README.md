# Prerequisites

We require you to have postgresql, maven, jdk, and npm/node.js installed.


# Now to run the program

1. Please open you terminal and run: psql -U postgres -c "CREATE DATABASE \"FirmwareForce\";

2. Once the database has been created please make your way to Backend/src/main/resources/application.properties and ensure that the lines: "spring.datasource.username=postgres" and "spring.datasource.password=FirmwareForce" match you username and password for PostgreSQL or the application will not run succesfully.

3. Please then open a terminal and navigate to FirmwareForce/Backend and run the command mvn spring-boot:run which will start the back end of our application.

4. Next please open a seperate terminal and leave the backend running as it is, within this terminal navigate to FirmwareForce/Frontend and run the command npm run dev which will start the front end of our application.

5. Then using a web browser of your choice open http://localhost:3000/ at which point you will be presented with our home page and you are free to explore our application.

6. Please be advised two user accounts have been pre-made for your use, Account one has Admin privileges which emulates a council member and you can sign in with the credentials: Username = professorA and Password = AdminProfessor.2026 . Another account, Account two has been set up with only standard priveleges emulating a community member and you can sign in to this account with the credentials: Username = professorC and Password = CommunityProfessor.2026 . You are also able to explore some regions of our application signed out.

# There is also a deployed version of this exact git repository on:

