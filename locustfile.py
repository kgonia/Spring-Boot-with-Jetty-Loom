import random
import string

from locust import HttpUser, between, task, FastHttpUser, TaskSet


def get_random_string(length):
    # With combination of lower and upper case
    return ''.join(random.choice(string.ascii_letters) for i in range(length))


class WebsiteUser(FastHttpUser):
    # How long a simulated user should wait between executing tasks
    wait_time = between(1, 2)

    @task
    def call_blocking_greeting(self):
        self.client.get(url="/blocking/greeting", params={'name': get_random_string(10)})

    @task
    def call_nonblocking_greeting(self):
        self.client.get(url="/non-blocking/greeting", params={'name': get_random_string(10)})

    @task
    def call_nonblocking_numbers(self):
        self.client.get("/non-blocking/numbers")
