import datetime
import django.utils.timezone

from django.db import models
from users.models import UserRegular

# Create your models here.


class Queue(models.Model):

    user_item = models.ForeignKey(
        UserRegular,
        on_delete=models.CASCADE,
    )
    timestamp = models.DateTimeField(auto_now=True)

    def __str__(self) -> str:
        return f'{self.user_item} {self.timestamp}'
