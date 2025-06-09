backend/
├── pom.xml                                    
│
├── api-gateway/                               
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/lms/apigateway/
│   │   │   │       ├── ApiGatewayApplication.java
│   │   │   │       └── config/                
│   │   │   └── resources/
│   │   │       ├── application.yml            
│   │   │       └── bootstrap.yml              
│   │   └── test/
│   ├── Dockerfile                             
│   └── pom.xml                                
│                                              
├── users-service/                             
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/lms/users/
│   │   │   │       ├── UsersServiceApplication.java
│   │   │   │       ├── config/                
│   │   │   │       ├── controller/            
│   │   │   │       ├── model/                 
│   │   │   │       ├── repository/            
│   │   │   │       └── service/               
│   │   │   └── resources/
│   │   │       ├── application.yml            
│   │   │       └── bootstrap.yml              
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
│                                               
├── courses-service/                            
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/lms/courses/
│   │   │   │       ├── CoursesServiceApplication.java
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── model/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── bootstrap.yml
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
│                                               
├── enrollment-service/                         
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/lms/enrollment/
│   │   │   │       ├── EnrollmentServiceApplication.java
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── model/
│   │   │   │       ├── repository/
│   │   │   │       ├── service/
│   │   │   │       └── client/                 
│   │   │   │                                   
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── bootstrap.yml
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
│
├── notifications-service/                      
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/lms/notifications/
│   │   │   │       ├── NotificationsServiceApplication.java
│   │   │   │       ├── listener/               
│   │   │   │       ├── service/                
│   │   │   │       └── model/                  
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── bootstrap.yml
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
│
├── shared-libraries/                           
│   ├── common-models/                          
│   │   ├── src/main/java/
│   │   │   └── com/lms/shared/models/
│   │   │       ├── UserDTO.java                
│   │   │       └── CourseDTO.java
│   │   └── pom.xml                             
│   │                                            
│   ├── common-clients/                         
│   │   ├── src/main/java/
│   │   │   └── com/lms/shared/clients/
│   │   │       ├── UserClient.java             
│   │   │       └── CourseClient.java           
│   │   └── pom.xml                             
│   │
│   └── common-security/                        
│       ├── src/main/java/
│       │   └── com/lms/shared/security/
│       │       └── JwtUtil.java
│       │       └── CustomUserDetailsService.java
│       └── pom.xml                             
│
└── README.md                                   