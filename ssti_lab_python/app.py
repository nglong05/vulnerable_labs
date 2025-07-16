from flask import Flask, request, render_template_string

app = Flask(__name__)

@app.route("/")
def index():
	template = request.args.get("input", "")
	if not template:
        	template = """
        	<form method="GET">
            	<input type="text" name="input" style="width: 700px;">
            	<button type="submit">Submit</button>
        	</form>
        	"""
	return render_template_string(template)

if __name__ == "__main__":
	app.run(host="0.0.0.0", port=8001, debug=True)
