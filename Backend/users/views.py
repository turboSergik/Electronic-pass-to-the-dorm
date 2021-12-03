from rest_framework.response import Response
from rest_framework.views import APIView

from .models import UserAdmin, UserRegular
from .serializers import UserAdminSerializer, UserRegularSerializer, UserRegularRegisterSerializer, UserAdminRegisterSerializer


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


class UpdateUserApproveView(APIView):

    def post(self, request):

        user_id = request.data['id']
        is_approved = request.data['is_approved']
        UserRegular.objects.filter(id=user_id).update(is_approved=is_approved)
        user = UserRegular.objects.get(id=user_id)
        serializer = UserRegularSerializer(user)
        return Response(serializer.data)


class UserLoginView(APIView):

    def post(self, request):

        login = request.data['login']
        password = request.data['password']
        user = UserRegular.objects.filter(login=login, password=password)
        if len(user):
            user = user[0]

        if not user:
            return Response(_build_error("Invalid login or pasword"), status=404)

        serializer = UserRegularSerializer(user)
        return Response(serializer.data)


class UserCreateView(APIView):

    def post(self, request):
        user = UserRegularRegisterSerializer(data=request.data)

        if user.is_valid():
            existing = UserRegular.objects.filter(login=request.data["login"])
            if len(existing):
                return Response(_build_error("Login is exist"), status=406)
            user.save()
        else:
            return Response(_build_error("Bad request"), status=400)

        user_to_return = UserRegularSerializer(data=request.data)
        user_to_return.is_valid()

        return Response(user_to_return.data, status=201)


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
        user = UserAdminRegisterSerializer(data=request.data)
        if user.is_valid():
            user.save()
        else:
            return Response(status=400)

        user_to_return = UserAdminSerializer(data=request.data)
        user_to_return.is_valid()

        return Response(user_to_return.data, status=201)


class UserAdminLoginView(APIView):

    def post(self, request):
        login = request.data['login']
        password = request.data['password']
        user = UserAdmin.objects.filter(login=login, password=password)
        if len(user):
            user = user[0]
        if not user:
            return Response(_build_error("Invalid login or password"), status=404)

        serializer = UserRegularSerializer(user)
        return Response(serializer.data)


def _build_error(msg: str):
    return {"Error": msg}
