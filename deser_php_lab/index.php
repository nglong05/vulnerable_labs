<?php
session_start();
require 'classes.php';

$stored = isset($_SESSION['settings']) ? $_SESSION['settings'] : '';

?>
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>Lab Insecure Deserialization</title></head>
<body>
<h1>User Settings Lab</h1>

<form action="save.php" method="post">
    <textarea name="payload" rows="5" cols="50"
              placeholder="Paste your serialized settings here…"><?= htmlspecialchars($stored) ?></textarea><br>
    <button type="submit">Save Settings</button>
</form>

<form action="load.php" method="post" style="margin-top:1em;">
    <button type="submit">Load (Unserialize) Settings</button>
</form>
</body>
</html>
