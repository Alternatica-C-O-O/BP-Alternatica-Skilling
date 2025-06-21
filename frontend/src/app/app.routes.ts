import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ListComponent as CourseListComponent } from './pages/courses/list/list.component';
import { CategoriesComponent } from './pages/courses/categories/categories.component';
import { WebinarsComponent } from './pages/courses/webinars/webinars.component';

import { OrderComponent } from './pages/sales/order/order.component';
import { PaymentsComponent } from './pages/sales/payments/payments.component';
import { ProductsComponent } from './pages/sales/products/products.component';

import { ListComponent as UserListComponent } from './pages/users/list/list.component';
import { RolesComponent } from './pages/users/roles/roles.component';
import { GroupsComponent } from './pages/users/groups/groups.component';
import { LayoutComponent } from './layout/layout.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: '', component: HomeComponent },

      {
        path: 'courses',
        children: [
          { path: 'list', component: CourseListComponent },
          { path: 'categories', component: CategoriesComponent },
          { path: 'webinars', component: WebinarsComponent },
        ],
      },
      {
        path: 'sales',
        children: [
          { path: 'order', component: OrderComponent },
          { path: 'payments', component: PaymentsComponent },
          { path: 'products', component: ProductsComponent },
        ],
      },
      {
        path: 'users',
        children: [
          { path: 'list', component: UserListComponent },
          { path: 'roles', component: RolesComponent },
          { path: 'groups', component: GroupsComponent },
        ],
      },
    ],
  },
  { path: '**', redirectTo: '' },
];
