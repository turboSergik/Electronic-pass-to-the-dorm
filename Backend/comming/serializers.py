from rest_framework import fields, serializers
from .models import Queue
from users.serializers import UserRegularSerializer


class QueueSerializer(serializers.HyperlinkedModelSerializer):
    user_item = UserRegularSerializer(read_only=True)

    class Meta:
        model = Queue
        fields = ("user_item", "timestamp")
