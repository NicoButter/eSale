from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.models import User
from django.contrib import messages
from django.urls import reverse
from django.http import HttpResponseRedirect
from accounts.models import PerfilUsuario, Cliente, Rol, CorreoElectronico
import re

def login_view(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            try:
                perfil = PerfilUsuario.objects.get(user=user)
                if perfil.id_rol.nombre_rol == 'Administrador':
                    return HttpResponseRedirect(reverse('dashboards:administrador_dashboard'))
                elif perfil.id_rol.nombre_rol == 'Vendedor':
                    return HttpResponseRedirect(reverse('dashboards:vendedor_dashboard'))
                elif perfil.id_rol.nombre_rol == 'Cliente':
                    return HttpResponseRedirect(reverse('dashboards:cliente_dashboard'))
                return HttpResponseRedirect(reverse('dashboards:cliente_dashboard'))
            except PerfilUsuario.DoesNotExist:
                messages.error(request, 'Perfil de usuario no encontrado')
                return render(request, 'accounts/login.html')
        else:
            messages.error(request, 'Credenciales inválidas')
            return render(request, 'accounts/login.html')
    return render(request, 'accounts/login.html')

def logout_view(request):
    logout(request)
    messages.success(request, 'Has cerrado sesión exitosamente.')
    return render(request, 'accounts/logout.html')

def register_view(request):
    if request.method == 'POST':
        try:
            username = request.POST['username']
            email = request.POST['email']
            password = request.POST['password']
            nombre = request.POST['nombre']
            dni = request.POST['dni']
            
            # Validaciones
            if not username or not email or not password or not nombre or not dni:
                messages.error(request, 'Todos los campos son obligatorios')
                return render(request, 'accounts/register.html')
            
            # Validar formato de correo
            if not re.match(r'^[\w\.-]+@[\w\.-]+\.\w+$', email):
                messages.error(request, 'Correo electrónico inválido')
                return render(request, 'accounts/register.html')
            
            # Validar longitud de contraseña (mínimo 8 caracteres)
            if len(password) < 8:
                messages.error(request, 'La contraseña debe tener al menos 8 caracteres')
                return render(request, 'accounts/register.html')
            
            # Validar formato de DNI (8 dígitos numéricos)
            if not re.match(r'^\d{8}$', dni):
                messages.error(request, 'El DNI debe tener 8 dígitos numéricos')
                return render(request, 'accounts/register.html')
            
            # Verificar si el usuario, email o DNI ya existen
            if User.objects.filter(username=username).exists():
                messages.error(request, 'El nombre de usuario ya está en uso')
                return render(request, 'accounts/register.html')
            if User.objects.filter(email=email).exists():
                messages.error(request, 'El correo electrónico ya está registrado')
                return render(request, 'accounts/register.html')
            if Cliente.objects.filter(dni=dni).exists():
                messages.error(request, 'El DNI ya está registrado')
                return render(request, 'accounts/register.html')
            
            # Crear el usuario (la señal en signals.py crea Cliente, PerfilUsuario, CorreoElectronico)
            user = User.objects.create_user(username=username, email=email, password=password)
            
            # Actualizar el nombre y DNI en Cliente (creado por la señal)
            cliente = Cliente.objects.get(nombre=username)  # La señal usa username como nombre inicial
            cliente.nombre = nombre
            cliente.dni = dni
            cliente.save()
            
            # Actualizar el correo en CorreoElectronico (creado por la señal)
            correo = CorreoElectronico.objects.get(id_cliente=cliente)
            correo.correo = email
            correo.save()
            
            # Autenticar y loguear al usuario
            login(request, user)
            messages.success(request, 'Registro exitoso. Bienvenido!')
            return redirect('dashboards:cliente_dashboard')
        
        except Rol.DoesNotExist:
            messages.error(request, 'Error: El rol Cliente no está configurado en la base de datos')
            return render(request, 'accounts/register.html')
        except Exception as e:
            messages.error(request, f'Error al registrar: {str(e)}')
            return render(request, 'accounts/register.html')
    
    return render(request, 'accounts/register.html')