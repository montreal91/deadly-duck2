import http.client
import json
import time

HOST, PORT = "localhost", 8081

connection = http.client.HTTPConnection(host=HOST, port=PORT)
t1 = time.time_ns()
connection.request("GET", "/user/list/")
t2 = time.time_ns()
print(f"request took {(t2 - t1) / 1000000} ms.")
print(json.loads(connection.getresponse().read()))


def create_new_game():
    conn = http.client.HTTPConnection(host=HOST, port=PORT)
    headers = {
        "Content-type": "application/json",
        "Accept": "text/plain"
    }
    new_input = {
        "name": "TestGame",
        "owner": "montreal",
        "participants": ["LeniDuke", "nos_habebit_humus"]
    }
    conn.request("POST", "/game/chaos/new/game/", json.dumps(new_input), headers=headers)
    response = conn.getresponse()
    if response.status == 200:
        print(response.read())
    else:
        print(response.status)
    conn.close()


def start_game():
    conn = http.client.HTTPConnection(host=HOST, port=PORT)
    headers = {
        "Content-type": "application/json",
        "Accept": "text/plain"
    }
    new_input = {
        "gameName": "TestGame",
        "ownerName": "montreal",
    }
    conn.request("POST", "/game/chaos/start/game/", json.dumps(new_input), headers=headers)
    response = conn.getresponse()
    if response.status == 200:
        print(response.read())
    else:
        print(response.status)
    conn.close()


def get_all_games():
    conn = http.client.HTTPConnection(host=HOST, port=PORT)
    headers = {
        "Content-type": "text/plain",
        "Accept": "application/json"
    }

    conn.request("GET", "/game/chaos/game/all/", "montreal", headers=headers)
    response = conn.getresponse()
    if response.status == 200:
        print(response.read())
    else:
        print(response.status)
        print(response.read())
    conn.close()


def load_game():
    conn = http.client.HTTPConnection(host=HOST, port=PORT)
    headers = {
        "Content-type": "application/json",
        "Accept": "text/plain"
    }
    new_input = {
        "gameName": "TestGame",
        "ownerName": "montreal",
    }
    conn.request("POST", "/game/chaos/load/game/", json.dumps(new_input), headers=headers)
    response = conn.getresponse()
    if response.status == 200:
        print(response.read())
    else:
        print(response.status)
    conn.close()


if __name__ == "__main__":
    print("Client prototype.")
    get_all_games()
    create_new_game()
    load_game()
    start_game()
