import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { VERSION as CDK_VERSION } from '@angular/cdk';
import { VERSION as MAT_VERSION} from '@angular/material/core';
import { AppComponent } from './app/app.component';

console.info("Angular CDK version", CDK_VERSION.full);
console.info("Angular Material version", MAT_VERSION.full);

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
