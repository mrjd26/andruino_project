#server.py
import requests
from socket import *
import serial
from thread import *

HOST=''
PORT = 29876
ADDR=(HOST,PORT)
BUFSIZE=4096

serv=socket(AF_INET,SOCK_STREAM)

serv.bind((ADDR))
serv.listen(5)

print "listening..."

#conn,addr = serv.accept()

def serverthread(conn):
    
    data = conn.recv(1024)
    print data
    ndx=data.find("=")
    start = ndx+1


    number = ""
    
    while data[start] != " ":
        number += data[start]
        start+=1

    print number

    conn.send('HTTP/1.1 200 OK\r\n')
    conn.send("Content-Type: text/html\r\n\r\n")
    conn.send('<html><body><h1>Hello World</body></html>')

    if int(number) % 2 == 0:
        arduinoWrite="y"
    else:
        arduinoWrite="n"

    ser = serial.Serial('/dev/ttyACM1',9600)
    ser.write(arduinoWrite)
    conn.close()

while True:
    conn,addr = serv.accept()
    print conn
    print addr
    print "connected..."
    start_new_thread(serverthread,(conn,))
    

    
