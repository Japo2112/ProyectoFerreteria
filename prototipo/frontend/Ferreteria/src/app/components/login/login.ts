import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Seguridad } from '../../services/seguridad';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  username = '';
  password = '';
  mensaje = '';

  constructor(private seguridadService: Seguridad, private router: Router) {}

  login() {
    const credenciales = { username: this.username, password: this.password };

    this.seguridadService.login(credenciales).subscribe({
      next: (usuario) => {
        console.log('Usuario autenticado:', usuario);
        localStorage.setItem('usuario', JSON.stringify(usuario)); // Guardamos sesión
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        if (err.status === 401) {
          this.mensaje = 'Usuario o contraseña incorrectos';
        } else {
          this.mensaje = 'Error de conexión con el servidor';
        }
      }
    });
  }

}
