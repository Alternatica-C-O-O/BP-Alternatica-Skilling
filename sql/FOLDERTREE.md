sql/
├── migrations/                                 
│   ├── flyway/                                 
│   │   ├── V1__create_users_table.sql          
│   │   ├── V2__create_courses_table.sql        
│   │   ├── V3__add_enrollment_table.sql        
│   │   └── V4__add_index_to_users.sql          
│   ├── liquibase/                              
│   │   ├── changelog.xml                       
│   │   ├── users-changelog.xml                 
│   │   └── courses-changelog.xml               
│   └── README.md                               
│
├── seed-data/                                  
│   ├── development/                            
│   │   ├── users_dev_data.sql                  
│   │   ├── courses_dev_data.sql                
│   │   └── admin_user.sql                      
│   ├── test/                                   
│   │   ├── users_test_data.sql
│   │   └── dummy_course_data.sql
│   └── production/                             
│       └── initial_config_data.sql             
│
├── stored-procedures/                          
│   ├── calculate_grades.sql
│   └── update_course_progress.sql
│
├── views/                                      
│   ├── active_students_view.sql
│   └── course_enrollments_summary.sql
│
├── functions/                                  
│   ├── get_user_course_status.sql
│   └── check_course_completion.sql
│
├── docker-entrypoint-initdb.d/                 
│   ├── 01_create_databases.sql                 
│   ├── 02_create_roles.sql                     
│   └── 03_init_schemas.sql                     
│
└── README.md                                   