<!DOCTYPE html>
<html>
  <head><meta charset="utf-8"/><title>FreeMarker SSTI Lab</title></head>
  <body>
    <h1>FreeMarker SSTI Demo</h1>
    <form action="/render" method="post">
      <textarea name="payload" rows="8" cols="60">
Hello, ${"world"}!
      </textarea><br/>
      <button type="submit">Render</button>
    </form>
  </body>
</html>

