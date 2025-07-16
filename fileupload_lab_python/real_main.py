import os
import shutil
import threading
import time

from flask import Flask, render_template_string, request, session

app = Flask(__name__)
app.secret_key = 'SUPER_SECRET'

UPLOAD_FOLDER = os.path.join(os.path.dirname(__file__), 'uploads')
BACKUP_FOLDER = os.path.join(os.path.dirname(__file__), '6C3n6DvHRC2Q')
os.makedirs(UPLOAD_FOLDER, exist_ok=True)
os.makedirs(BACKUP_FOLDER, exist_ok=True)

def restore_helper():
    while True:
        try:
            original = os.path.join(BACKUP_FOLDER, 'helper.py')
            target = os.path.join(os.path.dirname(__file__), 'utils', 'helper.py')
            if not os.path.exists(original):
                print("❗️ Backup helper.py is missing.")
            else:
                shutil.copy(original, target)
        except Exception as e:
            print(f"[!] Restore error: {e}")
        time.sleep(30) 

#@app.before_serving
def start_restore_thread():
    t = threading.Thread(target=restore_helper)
    t.daemon = True
    t.start()

@app.route('/uploader', methods=['POST'])
def upload_file():
    if request.method == 'POST':
        f = request.files['files']

        if 'main' in f.filename:
            return 'File upload error'

        if 'num_file' in session:
            session['num_file'] += 1
        else:
            session['num_file'] = 0

        file_path = os.path.join(UPLOAD_FOLDER, f.filename)
        print(file_path)
        f.save(file_path)

        try:
            from utils import helper
            helper.rename_file(file_path, os.path.join(UPLOAD_FOLDER, str(session['num_file']) + '.txt'))
        except Exception as e:
            return f"Lỗi xử lý file: {e}"

        return 'Tải file thành công.'

@app.route('/')
def index():
    name = request.args.get('name', 'Hacker')
    total = session.get('num_file', 0)
    template = """
    <html>
    <head>
        <title>Retro Uploader</title>
        <style>
            body {
                background-color: black;
                color: #00FF00;
                font-family: monospace;
                padding: 20px;
            }
            input, button {
                background-color: black;
                color: #00FF00;
                border: 1px solid #00FF00;
                padding: 5px;
                font-family: monospace;
            }
        </style>
    </head>
    <body>
        <h2>Welcome, {{name}}. Pwned by me</h2>
        <p>You have uploaded {{total}} files.</p>
        <form method="POST" action="/uploader" enctype="multipart/form-data">
            <input type="file" name="files">
            <button type="submit">Upload</button>
        </form>
    </body>
    </html>
    """
    return render_template_string(template, name=name, total=total)

if __name__ == '__main__':
    t = threading.Thread(target=restore_helper)
    t.daemon = True
    t.start()
    app.run(debug=False, host='0.0.0.0', port=8008, use_reloader=True, threaded=True)