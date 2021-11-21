from django.db import models

# Create your models here.


class UserAdmin(models.Model):

    login = models.CharField(max_length=20)
    password = models.CharField(max_length=30)

    name = models.CharField(max_length=20)
    surname = models.CharField(max_length=20)
    phone = models.CharField(max_length=20)
    email = models.CharField(max_length=30)

    def __str__(self) -> str:
        return f'{self.name} {self.surname}'


class UserRegular(models.Model):

    login = models.CharField(max_length=20)
    password = models.CharField(max_length=30)

    name = models.CharField(max_length=20)
    surname = models.CharField(max_length=20)
    phone = models.CharField(max_length=20)
    email = models.CharField(max_length=30)
    is_approved = models.BooleanField(default=0)

    def __str__(self) -> str:
        return f'{self.name} {self.surname}'
