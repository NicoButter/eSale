from django.shortcuts import render, redirect
from django.urls import reverse
from accounts.models import PerfilUsuario
from store.models import Articulo, Categoria

def home_view(request):
    if request.user.is_authenticated:
        try:
            perfil = PerfilUsuario.objects.get(user=request.user)
            if perfil.id_rol.nombre_rol == 'Administrador':
                return redirect('dashboards:administrador_dashboard')
            elif perfil.id_rol.nombre_rol == 'Vendedor':
                return redirect('dashboards:vendedor_dashboard')
            elif perfil.id_rol.nombre_rol == 'Cliente':
                return redirect('dashboards:cliente_dashboard')
        except PerfilUsuario.DoesNotExist:
            return redirect('accounts:login')
    else:
        # Obtener artículos destacados y categorías
        articulos = Articulo.objects.order_by('-stock')[:4]  # 4 artículos con mayor stock
        categorias = Categoria.objects.all()[:3]  # 3 categorías principales
        context = {
            'articulos': articulos,
            'categorias': categorias,
        }
        return render(request, 'core/landing.html', context)