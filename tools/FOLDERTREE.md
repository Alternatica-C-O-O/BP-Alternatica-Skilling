tools/
├── linters/                                    # [1] Configuraciones de linters para diferentes lenguajes
│   ├── .eslintrc.js                            
│   ├── .prettierrc.js                          
│   ├── .editorconfig                           
│   ├── checkstyle.xml                          
│   ├── pmd-ruleset.xml                         
│   └── ...
│
├── hooks/                                      # [2] Hooks de Git (usualmente gestionados por Husky)
│   ├── pre-commit                              
│   ├── commit-msg                              
│   └── ...
│
├── ci-cd/                                      # [3] Scripts y configuraciones específicas para CI/CD 
│   ├── build-all.sh                            
│   ├── deploy-dev.sh                           
│   ├── update-k8s-manifests.sh                 
│   └── ...
│
├── scripts/                                    # [4] Scripts de utilidad general para desarrolladores
│   ├── setup-env.sh                            
│   ├── run-local-backend.sh                    
│   ├── run-local-frontend.sh                   
│   ├── generate-api-docs.sh                    
│   ├── cleanup-docker.sh                       
│   └── ...
│
├── templates/                                  # [5] Plantillas para archivos o componentes
│   ├── new-microservice-template/              
│   │   ├── pom.xml.tpl
│   │   ├── Dockerfile.tpl
│   │   └── src/main/java/com/lms/service_template/
│   ├── new-component-angular.tpl               
│   └── ...
│
├── README.md                                   
└── .gitignore                        