.
├── .github/                                
│   └── workflows/
│       ├── backend-ci.yml                  
│       ├── frontend-ci.yml                 
│       └── docs-deploy.yml                 
│
├── .vscode/                                
│   ├── settings.json
│   ├── extensions.json
│   └── launch.json
│
├── assets/                                 
│   ├── images/
│   ├── logos/
│   └── ...
│
├── backend/                                
│   ├── pom.xml                             
│   ├── api-gateway/                        
│   │   ├── src/main/java/...
│   │   ├── Dockerfile
│   │   └── pom.xml
│   ├── users-service/                      
│   │   ├── src/main/java/...
│   │   ├── Dockerfile
│   │   └── pom.xml
│   ├── courses-service/                    
│   │   ├── src/main/java/...
│   │   ├── Dockerfile
│   │   └── pom.xml
│   ├── enrollment-service/                 
│   │   ├── src/main/java/...
│   │   ├── Dockerfile
│   │   └── pom.xml
│   ├── notifications-service/              
│   │   ├── src/main/java/...
│   │   ├── Dockerfile
│   │   └── pom.xml
│   └── shared-libraries/                   
│       ├── common-models/                  
│       │   └── pom.xml
│       ├── common-clients/                 
│       │   └── pom.xml
│       └── common-security/                
│           └── pom.xml
│
├── docs/                                   
│   ├── bundler/                            
│   │   ├── src/
│   │   ├── Cargo.toml
│   │   └── typst.toml
│   ├── packages/preview/                   
│   │   ├── @skilling-design/
│   │   │   ├── src/
│   │   │   └── template/
│   │   ├── @skilling-documentation/
│   │   ├── @skilling-lifecycle/            
│   │   └── @skilling-presentation/
│   ├── website/                            
│   │   ├── blog/
│   │   ├── docs/                           
│   │   │   ├── intro.md
│   │   │   ├── backend/
│   │   │   ├── frontend/
│   │   │   ├── infra/
│   │   │   └── ...
│   │   ├── src/
│   │   ├── static/
│   │   ├── docusaurus.config.ts
│   │   └── package.json
│   ├── package.json                        
│   └── README.md
│
├── frontend/                               
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   ├── services/                   
│   │   │   └── models/
│   │   ├── assets/
│   │   └── environments/
│   ├── angular.json
│   ├── package.json
│   ├── tsconfig.json
│   └── ...
│
├── infra/                                  
│   ├── docker-compose/                     
│   │   └── development/
│   │       └── docker-compose.yml
│   ├── cloud-providers/                    
│   │   ├── gcp/                            
│   │   │   ├── terraform/                  
│   │   │   └── scripts/
│   │   ├── supabase/                       
│   │   │   ├── terraform/                  
│   │   │   └── scripts/
│   │   ├── vercel/                         
│   │   │   ├── project-config.json
│   │   │   └── vercel.json
│   │   └── render/                         
│   │       └── render.yaml
│   ├── environments/                       
│   │   ├── .env.development
│   │   ├── .env.staging
│   │   └── .env.production
│   ├── scripts/                            
│   │   ├── deploy-frontend-vercel.sh
│   │   ├── deploy-backend-render.sh
│   │   └── setup-gcp-auth.sh
│   ├── monitoring/                         
│   │   └── grafana/
│   └── security/                           
│       └── network-policies/
│
├── sql/                                    
│   ├── migrations/
│   │   ├── flyway/                         
│   │   └── README.md
│   ├── seed-data/
│   │   ├── development/
│   │   └── test/
│   └── docker-entrypoint-initdb.d/         
│
├── tools/                                  
│   ├── linters/                            
│   │   ├── .eslintrc.js
│   │   └── .prettierrc.js
│   ├── hooks/                              
│   │   ├── pre-commit
│   │   └── commit-msg
│   ├── ci-cd/                              
│   ├── scripts/                            
│   │   ├── setup-env.sh
│   │   └── generate-api-docs.sh
│   └── templates/                          
│
├── .editorconfig                           
├── .gitignore                              
├── FOLDERTREE.md                           
├── LICENSE                                 
├── pnpm-lock.yaml                          
├── pnpm-workspace.yaml                     
├── README.md                               
├── SECURITY.md                             
├── TODO.md                                 
└── package.json                            