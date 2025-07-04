from django.db import models
from django.contrib.auth.models import User

class Rol(models.Model):
    id_rol = models.AutoField(primary_key=True)
    nombre_rol = models.CharField(max_length=50, unique=True, choices=[
        ('Administrador', 'Administrador'),
        ('Vendedor', 'Vendedor'),
        ('Cliente', 'Cliente')
    ])

    class Meta:
        db_table = 'Roles'

    def __str__(self):
        return self.nombre_rol

class Cliente(models.Model):
    id_cliente = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=100)
    dni = models.CharField(max_length=20, unique=True)

    class Meta:
        db_table = 'Clientes'

    def __str__(self):
        return self.nombre

class Direccion(models.Model):
    id_direccion = models.AutoField(primary_key=True)
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, db_column='id_cliente')
    calle = models.CharField(max_length=200)
    ciudad = models.CharField(max_length=100)
    codigo_postal = models.CharField(max_length=20, null=True, blank=True)
    pais = models.CharField(max_length=100, null=True, blank=True)
    es_principal = models.BooleanField(default=False)

    class Meta:
        db_table = 'Direcciones'

    def __str__(self):
        return f"{self.calle}, {self.ciudad}"

class Telefono(models.Model):
    id_telefono = models.AutoField(primary_key=True)
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, db_column='id_cliente')
    numero = models.CharField(max_length=20)
    tipo = models.CharField(max_length=20, choices=[
        ('movil', 'Móvil'),
        ('fijo', 'Fijo'),
        ('trabajo', 'Trabajo')
    ], default='movil')
    es_principal = models.BooleanField(default=False)

    class Meta:
        db_table = 'Telefonos'

    def __str__(self):
        return self.numero

class CorreoElectronico(models.Model):
    id_correo = models.AutoField(primary_key=True)
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, db_column='id_cliente')
    correo = models.EmailField(max_length=100, unique=True)
    es_principal = models.BooleanField(default=False)

    class Meta:
        db_table = 'Correos_Electronicos'

    def __str__(self):
        return self.correo

class PerfilUsuario(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, db_column='id_cliente')
    id_rol = models.ForeignKey(Rol, on_delete=models.PROTECT, db_column='id_rol')

    class Meta:
        db_table = 'Perfiles_Usuario'

    def __str__(self):
        return f"Perfil de {self.user.username}"

    @property
    def rol(self):
        return self.id_rol.nombre_rol