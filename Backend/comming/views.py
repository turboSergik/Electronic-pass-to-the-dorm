import datetime

from rest_framework.response import Response
from rest_framework.views import APIView

from .models import Queue
from .serializers import QueueSerializer
from users.serializers import UserRegularSerializer
from users.models import UserRegular


class PutInQueueView(APIView):

    def get(self, request, pk):

        user_item = UserRegular.objects.get(id=pk)
        if user_item is None:
            return Response(status=400)

        Queue.objects.create(user_item_id=pk)
        return Response(status=201)


class QueueView(APIView):

    def get(self, request):
        queue_items = Queue.objects.order_by('-timestamp')[:25]
        serializer = QueueSerializer(queue_items, many=True)
        return Response(serializer.data)
