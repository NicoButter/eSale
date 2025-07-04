from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.decorators import login_required
from django.urls import reverse
from accounts.models import PerfilUsuario, Cliente
from store.models import Articulo, Carrito, ItemCarrito, Pedido, ItemPedido, Categoria
from django.db.models import Sum
from django.core.exceptions import PermissionDenied

# Vista para clientes: Agregar al carrito
@login_required
def agregar_al_carrito(request, articulo_id):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Cliente':
        raise PermissionDenied("Solo los clientes pueden agregar al carrito")
    
    articulo = get_object_or_404(Articulo, id_articulo=articulo_id)
    cliente = perfil.id_cliente
    carrito, created = Carrito.objects.get_or_create(id_cliente=cliente)
    
    item, created = ItemCarrito.objects.get_or_create(
        id_carrito=carrito,
        id_articulo=articulo,
        defaults={'cantidad': 1}
    )
    if not created:
        item.cantidad += 1
        item.save()
    
    return redirect('dashboards:cliente_dashboard')

# Vista para clientes: Ver carrito
@login_required
def ver_carrito(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Cliente':
        raise PermissionDenied("Solo los clientes pueden ver el carrito")
    
    cliente = perfil.id_cliente
    carrito = get_object_or_404(Carrito, id_cliente=cliente)
    items = ItemCarrito.objects.filter(id_carrito=carrito)
    total = sum(item.id_articulo.precio * item.cantidad for item in items)
    
    return render(request, 'store/carrito.html', {
        'carrito': carrito,
        'items': items,
        'total': total
    })

# Vista para clientes: Realizar pedido
@login_required
def realizar_pedido(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Cliente':
        raise PermissionDenied("Solo los clientes pueden realizar pedidos")
    
    cliente = perfil.id_cliente
    carrito = get_object_or_404(Carrito, id_cliente=cliente)
    items = ItemCarrito.objects.filter(id_carrito=carrito)
    
    if not items:
        return render(request, 'store/carrito.html', {'error': 'El carrito está vacío'})
    
    total = sum(item.id_articulo.precio * item.cantidad for item in items)
    pedido = Pedido.objects.create(id_cliente=cliente, total=total)
    
    for item in items:
        ItemPedido.objects.create(
            id_pedido=pedido,
            id_articulo=item.id_articulo,
            cantidad=item.cantidad,
            precio_unitario=item.id_articulo.precio
        )
        item.id_articulo.stock -= item.cantidad
        item.id_articulo.save()
    
    items.delete()  # Vaciar el carrito
    return redirect('store:ver_pedido', pedido_id=pedido.id_pedido)

# Vista para clientes: Ver historial de pedidos
@login_required
def ver_pedido(request, pedido_id):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Cliente':
        raise PermissionDenied("Solo los clientes pueden ver sus pedidos")
    
    pedido = get_object_or_404(Pedido, id_pedido=pedido_id, id_cliente=perfil.id_cliente)
    items = ItemPedido.objects.filter(id_pedido=pedido)
    
    return render(request, 'store/pedido.html', {
        'pedido': pedido,
        'items': items
    })

# Vista para vendedores/administradores: Crear artículo
@login_required
def crear_articulo(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol not in ['Vendedor', 'Administrador']:
        raise PermissionDenied("Solo vendedores y administradores pueden crear artículos")
    
    if request.method == 'POST':
        nombre = request.POST['nombre']
        descripcion = request.POST['descripcion']
        precio = request.POST['precio']
        stock = request.POST['stock']
        id_categoria = request.POST['id_categoria']
        Articulo.objects.create(
            nombre=nombre,
            descripcion=descripcion,
            precio=precio,
            stock=stock,
            id_categoria_id=id_categoria
        )
        return redirect('dashboards:vendedor_dashboard')
    
    categorias = Categoria.objects.all()
    return render(request, 'store/crear_articulo.html', {'categorias': categorias})

# Vista para vendedores/administradores: Procesar pedido
@login_required
def procesar_pedido(request, pedido_id):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol not in ['Vendedor', 'Administrador']:
        raise PermissionDenied("Solo vendedores y administradores pueden procesar pedidos")
    
    pedido = get_object_or_404(Pedido, id_pedido=pedido_id)
    if request.method == 'POST':
        estado = request.POST['estado']
        pedido.estado = estado
        pedido.save()
        return redirect('dashboards:vendedor_dashboard')
    
    return render(request, 'store/procesar_pedido.html', {'pedido': pedido})

# Vista para administradores: Reporte de ventas
@login_required
def reporte_ventas(request):
    perfil = get_object_or_404(PerfilUsuario, user=request.user)
    if perfil.id_rol.nombre_rol != 'Administrador':
        raise PermissionDenied("Solo los administradores pueden ver reportes")
    
    pedidos = Pedido.objects.filter(estado='entregado').aggregate(total_ventas=Sum('total'))
    return render(request, 'store/reporte_ventas.html', {'total_ventas': pedidos['total_ventas']})