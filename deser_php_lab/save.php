<?php
session_start();
$_SESSION['settings'] = trim($_POST['payload'] ?? '');
header('Location: index.php');
exit;
