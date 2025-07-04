from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.decorators import login_required
from django.urls import reverse
from accounts.models import PerfilUsuario
from store.models import Articulo, Pedido

@login_required
def administrador_dashboard(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Administrador':
        return redirect('accounts:login')
    
    pedidos = Pedido.objects.all()
    articulos = Articulo.objects.all()
    return render(request, 'dashboards/administrador_dashboard.html', {
        'user': request.user,
        'pedidos': pedidos,
        'articulos': articulos
    })

@login_required
def vendedor_dashboard(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Vendedor':
        return redirect('accounts:login')
    
    pedidos = Pedido.objects.filter(estado__in=['pendiente', 'procesado'])
    articulos = Articulo.objects.all()
    return render(request, 'dashboards/vendedor_dashboard.html', {
        'user': request.user,
        'pedidos': pedidos,
        'articulos': articulos
    })

@login_required
def cliente_dashboard(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Cliente':
        return redirect('accounts:login')
    
    articulos = Articulo.objects.all()
    return render(request, 'dashboards/cliente_dashboard.html', {
        'user': request.user,
        'articulos': articulos
    })