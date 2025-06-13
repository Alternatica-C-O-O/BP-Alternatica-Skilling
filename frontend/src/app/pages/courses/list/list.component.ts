import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';

export interface Curso {
  id: number;
  nombre: string;
  precio: number;
  descripcion: string;
  fechaCreacion: string;
}

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIconModule,
    CommonModule,
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.less',
})
export class ListComponent implements AfterViewInit {
  displayedColumns: string[] = [
    'id',
    'nombre',
    'precio',
    'descripcion',
    'fechaCreacion',
    'actions',
  ];
  dataSource = new MatTableDataSource<Curso>(CURSOS_DATA);

  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  ngAfterViewInit() {
    setTimeout(() => {
      if (this.paginator) {
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  agregarCurso() {
    const nuevo: Curso = {
      id: this.dataSource.data.length + 1,
      nombre: 'Nuevo Curso',
      precio: 0,
      descripcion: 'Descripción del curso',
      fechaCreacion: new Date().toISOString().split('T')[0],
    };
    this.dataSource.data = [...this.dataSource.data, nuevo];
  }

  editarCurso(curso: Curso) {
    const nombreEditado = prompt('Editar nombre del curso:', curso.nombre);
    if (nombreEditado !== null) {
      curso.nombre = nombreEditado;
      this.dataSource.data = [...this.dataSource.data];
    }
  }

  eliminarCurso(curso: Curso) {
    if (confirm(`¿Estás seguro de eliminar el curso "${curso.nombre}"?`)) {
      this.dataSource.data = this.dataSource.data.filter(
        (c) => c.id !== curso.id
      );
    }
  }
}

const CURSOS_DATA: Curso[] = [
  {
    id: 1,
    nombre: 'Curso Angular Básico',
    precio: 150,
    descripcion: 'Introducción a Angular',
    fechaCreacion: '2025-06-01',
  },
  {
    id: 2,
    nombre: 'Curso Spring Boot Intermedio',
    precio: 200,
    descripcion: 'APIs REST con Spring',
    fechaCreacion: '2025-05-15',
  },
];
