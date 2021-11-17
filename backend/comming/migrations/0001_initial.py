# Generated by Django 3.2.9 on 2021-12-05 20:02

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('users', '0003_userregular_is_approved'),
    ]

    operations = [
        migrations.CreateModel(
            name='Queue',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('timestamp', models.DateTimeField(auto_now=True)),
                ('some_trash_column', models.CharField(max_length=20)),
                ('user_item', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='users.userregular')),
            ],
        ),
    ]
