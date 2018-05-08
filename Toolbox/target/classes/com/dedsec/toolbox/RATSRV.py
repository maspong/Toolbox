import socket
import threading

bind_ip ="10.161.6.148"
bind_port=7615
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((bind_ip,bind_port))
server.listen(5)
print"[*] Listening on %s:%d" % (bind_ip,bind_port)
def handle_client(client_socket):
    while True:
	try:
    		results = client_socket.recv(4096)
    		print"[%s] Sent: %s" % (addr[0],results)
		command = raw_input()
		if (command == "disconnect"):
			clienct_socket.close()
			server.close()
			exit()
    		client_socket.send(command)
	except Exception as e:
		print "Connection lost %s" % (addr[0],)
		break
while True:
    print "Starting Server...."
    client,addr= server.accept()
    print "[*] Connection Accepted from %s:%d" % (addr[0],addr[1])
    client_handler = threading.Thread(target=handle_client,args=(client,))
    client_handler.start()
