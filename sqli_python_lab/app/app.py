from flask import Flask, render_template, request
import pymysql

app = Flask(__name__)

import os, time

time.sleep(5) # for the db wakeup

connection = pymysql.connect(
    host=os.environ.get("DB_HOST", "localhost"),
    user=os.environ.get("DB_USER", "task2_user"),
    password=os.environ.get("DB_PASSWORD", "task2_password"),
    database=os.environ.get("DB_NAME", "task2"),
    cursorclass=pymysql.cursors.DictCursor
)


def queryMysql(email):
    with connection.cursor() as cursor:
        sql = f"SELECT student_id FROM users WHERE email='{email}'"
        cursor.execute(sql)
        result = cursor.fetchall()
        print(result) # debug
    return result

@app.route('/', methods = ["GET", "POST"])
def lab():
    if request.method == "POST":
        email = request.form.get('email')
        result=queryMysql(email)
        print(len(result))
        if len(result):
            return render_template("index.html", result='Found your email in the database, sending drink')
        else:
            return render_template("index.html", result='Not found your email')        
    return render_template("index.html", result=None)

if __name__ == "__main__":
    app.run(host='0.0.0.0')
