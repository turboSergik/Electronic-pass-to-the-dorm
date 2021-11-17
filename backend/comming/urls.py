from django.urls import path

from . import views

urlpatterns = [
    path('put/<int:pk>', views.PutInQueueView.as_view()),
    path('get/', views.QueueView.as_view()),
]
