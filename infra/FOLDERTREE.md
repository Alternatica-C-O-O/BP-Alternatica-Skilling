infra/
├── docker-compose/                             # [1] Para desarrollo local (PostgreSQL local, RabbitMQ, Kafka)
│   ├── development/
│   │   ├── docker-compose.yml                  
│   │   ├── .env.example
│   │   └── README.md
│   └── README.md
│
├── cloud-providers/                            # [2] Configuraciones de Infraestructura como Código (IaC) por proveedor
│   ├── gcp/                                    
│   │   ├── terraform/                          
│   │   │   ├── main.tf                         
│   │   │   ├── variables.tf
│   │   │   ├── outputs.tf
│   │   │   ├── providers.tf                    # Configuración del proveedor GCP
│   │   │   ├── authentication.tf               
│   │   │   ├── monitoring.tf                   
│   │   │   ├── iam.tf                          
│   │   │   └── README.md
│   │   └── scripts/                            
│   │       ├── terraform-init-gcp.sh
│   │       └── terraform-apply-gcp.sh
│   │
│   ├── supabase/                               # [2b] Supabase (Base de Datos y, si usas, Auth/Storage de Supabase)
│   │   ├── terraform/                          
│   │   │   ├── main.tf                         
│   │   │   ├── variables.tf
│   │   │   ├── outputs.tf
│   │   │   ├── providers.tf                    # Configuración del proveedor Supabase
│   │   │   ├── database.tf                     # Configuración de la base de datos PostgreSQL en Supabase
│   │   │   ├── auth.tf                         
│   │   │   ├── storage.tf                      
│   │   │   ├── edge-functions.tf               
│   │   │   └── README.md
│   │   └── scripts/
│   │       ├── terraform-init-supabase.sh
│   │       └── terraform-apply-supabase.sh
│   │
│   ├── vercel/                                 # [2c] Vercel (Frontend Hosting)
│   │   ├── project-config.json                 
│   │   ├── vercel.json                         
│   │   └── README.md                           
│   │
│   ├── render/                                 # [2d] Render (Backend Hosting)
│   │   ├── render.yaml                         
│   │   └── README.md                          
│   │
│   └── README.md                               # README general para la carpeta cloud-providers
│
├── environments/                               # [3] Archivos de variables de entorno específicas para cada entorno
│   ├── .env.development                        
│   ├── .env.staging                            
│   ├── .env.production                         
│   └── README.md                               
│
├── scripts/                                    # [4] Scripts operativos de infraestructura 
│   ├── deploy-frontend-vercel.sh               
│   ├── deploy-backend-render.sh                
│   ├── setup-gcp-auth.sh                       
│   ├── connect-supabase.sh                     
│   ├── update-render-env-vars.sh               
│   └── README.md
│
├── monitoring/                                 # [5] Opcional: Configuraciones de monitoreo 
│   ├── grafana/
│   │   ├── datasources/
│   │   └── dashboards/
│   └── README.md
│
├── security/                                   # [6] Opcional: Configuraciones de seguridad
│   ├── firewall-rules/                         
│   └── README.md
│
└── README.md                                   