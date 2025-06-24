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
    │   │   │   │           └── GatewayConfig.java
    │   │   │   └── resources/
    │   │   │       ├── application.yml            
    │   │   │       └── bootstrap.yml              
    │   │   └── test/
    │   ├── Dockerfile                             
    │   └── pom.xml                                
    │
    ├── service-discovery/                         
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/servicediscovery/
    │   │   │   │       └── ServiceDiscoveryApplication.java
    │   │   │   └── resources/
    │   │   │       └── application.yml
    │   │   └── test/
    │   ├── Dockerfile
    │   └── pom.xml
    │
    ├── config-server/                             
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/configserver/
    │   │   │   │       └── ConfigServerApplication.java
    │   │   │   └── resources/
    │   │   │       └── application.yml
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
    │   │   │   │       │   └── UserController.java
    │   │   │   │       ├── model/                 
    │   │   │   │       │   ├── User.java
    │   │   │   │       │   ├── Role.java
    │   │   │   │       │   └── Permission.java
    │   │   │   │       ├── repository/            
    │   │   │   │       │   ├── UserRepository.java
    │   │   │   │       │   └── RoleRepository.java
    │   │   │   │       └── service/               
    │   │   │   │           └── UserService.java
    │   │   │   └── resources/
    │   │   │       ├── application.yml            
    │   │   │       └── bootstrap.yml              
    │   │   └── test/
    │   ├── Dockerfile
    │   └── pom.xml
    │
    ├── curriculum-service/                        
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/curriculum/
    │   │   │   │       ├── CurriculumServiceApplication.java
    │   │   │   │       ├── config/
    │   │   │   │       ├── controller/
    │   │   │   │       │   ├── ModelController.java
    │   │   │   │       │   └── CourseCatalogController.java
    │   │   │   │       ├── model/
    │   │   │   │       │   ├── EducationalModel.java  
    │   │   │   │       │   ├── CurriculumProfile.java 
    │   │   │   │       │   ├── Competency.java        
    │   │   │   │       │   ├── StudyPlan.java         
    │   │   │   │       │   ├── CourseOffering.java    
    │   │   │   │       │   └── Module.java            
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   └── resources/
    │   │   │       ├── application.yml
    │   │   │       └── bootstrap.yml
    │   │   └── test/
    │   ├── Dockerfile
    │   └── pom.xml
    │
    ├── resource-planning-service/                 
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/resourceplanning/
    │   │   │   │       ├── ResourcePlanningServiceApplication.java
    │   │   │   │       ├── config/
    │   │   │   │       ├── controller/
    │   │   │   │       │   ├── ScheduleController.java
    │   │   │   │       │   └── ResourceController.java
    │   │   │   │       ├── model/
    │   │   │   │       │   ├── AcademicPeriod.java    
    │   │   │   │       │   ├── ScheduleAssignment.java 
    │   │   │   │       │   ├── PhysicalSpace.java     
    │   │   │   │       │   └── VirtualPlatform.java   
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   │       └── client/                
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
    │   │   │   │       │   └── EnrollmentController.java
    │   │   │   │       ├── model/
    │   │   │   │       │   ├── Enrollment.java      
    │   │   │   │       │   └── Attendance.java      
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   │       └── client/                 
    │   │   │   │       └── event/                  
    │   │   │   │           ├── EnrollmentEventPublisher.java
    │   │   │   │           └── EnrollmentEventListener.java
    │   │   │   └── resources/
    │   │   │       ├── application.yml
    │   │   │       └── bootstrap.yml
    │   │   └── test/
    │   ├── Dockerfile
    │   └── pom.xml
    │
    ├── content-service/                          
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/content/
    │   │   │   │       ├── ContentServiceApplication.java
    │   │   │   │       ├── config/
    │   │   │   │       ├── controller/
    │   │   │   │       │   └── ContentController.java
    │   │   │   │       ├── model/
    │   │   │   │       │   └── LearningResource.java 
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   └── resources/
    │   │   │       ├── application.yml
    │   │   │       └── bootstrap.yml
    │   │   └── test/
    │   ├── Dockerfile
    │   └── pom.xml
    │
    ├── assessments-service/                      
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/assessments/
    │   │   │   │       ├── AssessmentsServiceApplication.java
    │   │   │   │       ├── config/
    │   │   │   │       ├── controller/
    │   │   │   │       │   └── AssessmentController.java
    │   │   │   │       │   └── GradeController.java
    │   │   │   │       ├── model/
    │   │   │   │       │   ├── Assessment.java      
    │   │   │   │       │   └── Grade.java           
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   │       └── client/                 
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
    │   │   │   │       │   └── EnrollmentNotificationListener.java
    │   │   │   │       ├── service/                
    │   │   │   │       │   └── NotificationService.java
    │   │   │   │       └── model/                  
    │   │   │   │           └── Notification.java
    │   │   │   └── resources/
    │   │   │       ├── application.yml
    │   │   │       └── bootstrap.yml
    │   │   └── test/
    │   ├── Dockerfile
    │   └── pom.xml
    │
    ├── reporting-service/                          
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/lms/reporting/
    │   │   │   │       ├── ReportingServiceApplication.java
    │   │   │   │       ├── controller/
    │   │   │   │       │   └── ReportController.java
    │   │   │   │       ├── model/
    │   │   │   │       │   └── ReportDefinition.java 
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/                
    │   │   │   │       └── client/                 
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
    │   │   │       ├── CourseDTO.java              
    │   │   │       └── EnrollmentDTO.java
    │   │   │       └── NotificationEvent.java      
    │   │   └── pom.xml                             
    │   │
    │   ├── common-clients/                         
    │   │   ├── src/main/java/
    │   │   │   └── com/lms/shared/clients/
    │   │   │       ├── UserClient.java             
    │   │   │       ├── CourseClient.java           
    │   │   │       └── EnrollmentClient.java
    │   │   │       └── ResourceClient.java
    │   │   └── pom.xml                             
    │   │
    │   └── common-security/                        
    │       ├── src/main/java/
    │       │   └── com/lms/shared/security/
    │       │       ├── JwtUtil.java                
    │       │       └── CustomUserDetailsService.java 
    │       │       └── SecurityConfig.java         
    │       └── pom.xml                            
    │
    ├── README.md                                   
    └── Dockerfile                                  