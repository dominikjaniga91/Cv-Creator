# CV creator

### Description

The application has been created to build own resume choosing among many templates. Each CV category is a separate entity.
Users choose a CV template, add information to an appropriate category, and finally generate cv.

### Documentation:

https://cvbuilderapp.herokuapp.com/swagger-ui.html

### Website:

https://cv-builder.vercel.app

### Database architecture:

![image](src/main/resources/static/db_schema.png)

### Security:
Application demands user authentication. Users can log to application using email which is unique value.
After successful authentication, the application generates the JSON web token for authorization every request using the 
SHA-256 Secure Hash Algorithm. The user's password is encrypted using the BCryptPasswordEncoder.


### Applied technologies and libraries:

#### Backend:

* Java 11
* Maven - version 3.5.2
* Spring Boot - version 2.3.0
* Spring Security - version 5.2.0
* Json Web Token - version 0.9.1
* Lombok - version 1.18.10
* MySQL - varsion 8.0.16
* Maven - version 3.6.3

#### Frontend:
* React.js 
* CSS 
* Bootstrap
* jsPdf

### To do:
* add functionality to save data to excel or pdf file
* create front end app
