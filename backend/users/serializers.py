from rest_framework import fields, serializers
from .models import UserAdmin, UserRegular


class UserAdminSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserAdmin
        fields = ("name", "surname", "phone", "email")


class UserRegularSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserRegular
        fields = ("name", "surname", "phone", "email")

