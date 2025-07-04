from django.db import models
from accounts.models import Cliente

class Categoria(models.Model):
    id_categoria = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=100, unique=True)

    class Meta:
        db_table = 'Categorias'

    def __str__(self):
        return self.nombre

class Articulo(models.Model):
    id_articulo = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=100)
    descripcion = models.TextField(blank=True)
    precio = models.DecimalField(max_digits=10, decimal_places=2)
    stock = models.PositiveIntegerField()
    id_categoria = models.ForeignKey(Categoria, on_delete=models.PROTECT, db_column='id_categoria')

    class Meta:
        db_table = 'Articulos'

    def __str__(self):
        return self.nombre

class Carrito(models.Model):
    id_carrito = models.AutoField(primary_key=True)
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, db_column='id_cliente')
    creado_en = models.DateTimeField(auto_now_add=True)

    class Meta:
        db_table = 'Carritos'

    def __str__(self):
        return f"Carrito de {self.id_cliente.nombre}"

class ItemCarrito(models.Model):
    id_item = models.AutoField(primary_key=True)
    id_carrito = models.ForeignKey(Carrito, on_delete=models.CASCADE, db_column='id_carrito')
    id_articulo = models.ForeignKey(Articulo, on_delete=models.CASCADE, db_column='id_articulo')
    cantidad = models.PositiveIntegerField()

    class Meta:
        db_table = 'Items_Carrito'
        unique_together = ('id_carrito', 'id_articulo')

    def __str__(self):
        return f"{self.cantidad} x {self.id_articulo.nombre}"

class Pedido(models.Model):
    id_pedido = models.AutoField(primary_key=True)
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, db_column='id_cliente')
    fecha = models.DateTimeField(auto_now_add=True)
    estado = models.CharField(max_length=20, choices=[
        ('pendiente', 'Pendiente'),
        ('procesado', 'Procesado'),
        ('enviado', 'Enviado'),
        ('entregado', 'Entregado')
    ], default='pendiente')
    total = models.DecimalField(max_digits=10, decimal_places=2)

    class Meta:
        db_table = 'Pedidos'

    def __str__(self):
        return f"Pedido {self.id_pedido} de {self.id_cliente.nombre}"

class ItemPedido(models.Model):
    id_item = models.AutoField(primary_key=True)
    id_pedido = models.ForeignKey(Pedido, on_delete=models.CASCADE, db_column='id_pedido')
    id_articulo = models.ForeignKey(Articulo, on_delete=models.CASCADE, db_column='id_articulo')
    cantidad = models.PositiveIntegerField()
    precio_unitario = models.DecimalField(max_digits=10, decimal_places=2)

    class Meta:
        db_table = 'Items_Pedido'
        unique_together = ('id_pedido', 'id_articulo')

    def __str__(self):
        return f"{self.cantidad} x {self.id_articulo.nombre}"