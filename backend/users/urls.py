from django.urls import path

from . import views

urlpatterns = [
    path('users_admin/', views.UserAdminListView.as_view()),
    path('users_admin/<int:pk>/', views.UserAdminDetailView.as_view()),
    path('users_admin/create', views.UserAdminCreateView.as_view()),

    path('users/', views.UserListView.as_view()),
    path('users/<int:pk>/', views.UserDetailView.as_view()),
    path('users/create', views.UserCreateView.as_view()),

]
