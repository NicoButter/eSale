from django.urls import path
from . import views

app_name = 'dashboards'

urlpatterns = [
    path('administrador/', views.administrador_dashboard, name='administrador_dashboard'),
    path('vendedor/', views.vendedor_dashboard, name='vendedor_dashboard'),
    path('cliente/', views.cliente_dashboard, name='cliente_dashboard'),
]