src/app/
├── core/             
│   ├── auth/
│   │   ├── auth.guard.ts
│   │   ├── auth.interceptor.ts
│   │   ├── auth.service.ts
│   │   └── ...
│   ├── data/
│   │   ├── api.service.ts      
│   │   ├── cache.service.ts    
│   │   └── ...
│   ├── guards/
│   │   ├── auth.guard.ts
│   │   ├── can-deactivate.guard.ts
│   │   └── ...
│   ├── interceptors/
│   │   ├── auth.interceptor.ts
│   │   ├── error.interceptor.ts
│   │   └── ...
│   ├── models/            
│   │   ├── patient.model.ts
│   │   ├── appointment.model.ts
│   │   ├── medical-record.model.ts
│   │   ├── user.model.ts
│   │   └── ...
│   ├── services/          
│   │   ├── notification.service.ts
│   │   ├── loading.service.ts
│   │   ├── utils.service.ts
│   │   └── ...
│   ├── core.module.ts     
│   └── ...
├── shared/            
│   ├── components/
│   │   ├── button/
│   │   ├── table/
│   │   ├── modal/
│   │   ├── form-field/
│   │   └── ...
│   ├── directives/
│   │   ├── highlight.directive.ts
│   │   ├── ...
│   ├── pipes/
│   │   ├── formatDate.pipe.ts
│   │   ├── ...
│   ├── shared.module.ts   
│   └── ...
├── modules/           
│   ├── patient/
│   │   ├── components/
│   │   │   ├── patient-list/
│   │   │   ├── patient-detail/
│   │   │   ├── patient-form/
│   │   │   └── ...
│   │   ├── services/     
│   │   ├── patient-routing.module.ts
│   │   ├── patient.module.ts
│   │   └── ...
│   ├── appointment/
│   │   ├── ...
│   ├── medical-record/
│   │   ├── ...
│   ├── medication/
│   │   ├── ...
│   ├── auth/              
│   │   ├── components/
│   │   ├── services/
│   │   ├── auth-routing.module.ts
│   │   ├── auth.module.ts
│   │   └── ...
│   └── ... (otros módulos funcionales)
├── app-routing.module.ts  
├── app.component.ts    
├── app.module.ts        
└── ...
├── assets/            
└── environments/      
