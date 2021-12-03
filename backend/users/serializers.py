from rest_framework import fields, serializers
from .models import UserAdmin, UserRegular


class UserAdminSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserAdmin
        fields = ("login", "name", "surname", "phone", "email",)


class UserAdminRegisterSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserAdmin
        fields = ("login", "name", "surname", "phone", "email", "password")


class UserRegularSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserRegular
        fields = ("login", "name", "surname", "phone", "email", "is_approved", "number_of_room")


class UserRegularRegisterSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserRegular
        fields = ("login", "name", "surname", "phone", "email", "is_approved", "password", "number_of_room")

