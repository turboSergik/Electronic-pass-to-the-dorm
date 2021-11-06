from rest_framework.response import Response
from rest_framework.views import APIView

from .models import UserAdmin, UserRegular
from .serializers import UserAdminSerializer, UserRegularSerializer


class UserListView(APIView):

    def get(self, request):
        users = UserRegular.objects.all()
        serializer = UserRegularSerializer(users, many=True)
        return Response(serializer.data)


class UserDetailView(APIView):

    def get(self, request, pk):
        user = UserRegular.objects.get(id=pk)
        serializer = UserRegularSerializer(user)
        return Response(serializer.data)


class UserCreateView(APIView):

    def post(self, request):
        user = UserRegularSerializer(data=request.data)
        if user.is_valid():
            user.save()
        else:
            return Response(status=400)
        return Response(user.data, status=201)


class UserAdminListView(APIView):

    def get(self, request):
        users = UserAdmin.objects.all()
        serializer = UserAdminSerializer(users, many=True)
        return Response(serializer.data)


class UserAdminDetailView(APIView):

    def get(self, request, pk):
        user = UserAdmin.objects.get(id=pk)
        serializer = UserAdminSerializer(user)
        return Response(serializer.data)


class UserAdminCreateView(APIView):

    def post(self, request):
        user = UserAdminSerializer(data=request.data)
        if user.is_valid():
            user.save()
        else:
            return Response(status=400)
        return Response(user.data, status=201)
