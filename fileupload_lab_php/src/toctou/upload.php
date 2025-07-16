<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['file'])) {
	$upload_dir = __DIR__ . '/uploads/';
	$fname = basename($_FILES['file']['name']);
	$file_dir = $upload_dir . $fname;

	move_uploaded_file($_FILES['file']['tmp_name'], $file_dir);
	// dummy check
	if (check_virus($file_dir) && check_file_type($file_dir)) {
		echo "uploaded file " . htmlspecialchars($file_dir);
	}
	else {
		unlink($file_dir);
		http_response_code(403);
		echo "error uploading file";
	}
	exit();
}

function check_virus(string $fileName): bool {
	usleep(50*1000);
	return true;
}
function check_file_type(string $fileName): bool {
	$ext = strtolower(pathinfo($fileName, PATHINFO_EXTENSION));
	if ($ext !== "jpg" && $ext !== "png") {
		echo "only JPG & PNG files are allowed\n";
		return false;
    }
	return true;
}
?>

<!DOCTYPE html>
<html>
<head><title>upload php 2</title></head>
<body>
  <h1>Upload your avatar (some small picture is enough)</h1>
  <form method="POST" enctype="multipart/form-data">
    <input type="file" name="file"><br><br>
    <button type="submit">Upload</button>
  </form>
</body>
</html>
