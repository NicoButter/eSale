from django.urls import path
from store import views

app_name = 'store'

urlpatterns = [
    path('carrito/agregar/<int:articulo_id>/', views.agregar_al_carrito, name='agregar_al_carrito'),
    path('carrito/', views.ver_carrito, name='ver_carrito'),
    path('pedido/realizar/', views.realizar_pedido, name='realizar_pedido'),
    path('pedido/<int:pedido_id>/', views.ver_pedido, name='ver_pedido'),
    path('articulo/crear/', views.crear_articulo, name='crear_articulo'),
    path('pedido/procesar/<int:pedido_id>/', views.procesar_pedido, name='procesar_pedido'),
    path('reporte/ventas/', views.reporte_ventas, name='reporte_ventas'),
]