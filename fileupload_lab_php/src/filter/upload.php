<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['file'])) {
$allowed = ['png', 'jpg', 'htaccess'];
$fname = $_FILES['file']['name'];
$ext = strtolower(pathinfo($fname, PATHINFO_EXTENSION));
if (!in_array($ext, $allowed)) {
	die("invalid ext");
}
//move file
$dst = __DIR__ . '/uploads/' . basename($fname);
if (move_uploaded_file($_FILES['file']['tmp_name'], $dst)) {
	echo "uploaded to: uploads/" . htmlspecialchars(basename($fname));
}
else {
	echo "upload failed";
}

exit();
}
?>
<!DOCTYPE html>
<html>
<head><title>upload php 1</title></head>
<body>
  <h1>Upload a file</h1>
  <form method="POST" enctype="multipart/form-data">
    <input type="file" name="file"><br><br>
    <button type="submit">Upload</button>
  </form>
</body>
</html>
