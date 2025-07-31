import os, base64, pickle
from flask import Flask, render_template, request, flash

app = Flask(__name__)
app.secret_key = os.urandom(16)

def encode_order(order: dict) -> str:
    raw = pickle.dumps(order, protocol=pickle.HIGHEST_PROTOCOL)
    return base64.b64encode(raw).decode()

def decode_order(code: str) -> dict:
    raw = base64.b64decode(code)
    return pickle.loads(raw)

@app.route("/", methods=["GET"])
def index():
    return render_template("index.html")

@app.route("/order", methods=["POST"])
def order():
    items_txt = request.form.get("items", "")
    if items_txt.strip():
        items = [x.strip() for x in items_txt.split(",") if x.strip()]
    else:
        items = request.form.getlist("preset")

    address = request.form.get("address", "").strip()

    if not items or not address:
        flash("Cần nhập ít nhất một món ăn và địa chỉ!")
        return render_template("index.html")

    order_obj = {"items": items, "address": address}
    code = encode_order(order_obj)
    return render_template("order.html", code=code, order=order_obj)

@app.route("/bot", methods=["POST"])
def bot():
    code = request.form.get("code", "")
    try:
        order_obj = decode_order(code)  # RCE
        return render_template("delivery.html", order=order_obj)
    except Exception as e:
        flash(e)
        return render_template("index.html")

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)

