<?php
session_start();
require 'classes.php';

if (!empty($_SESSION['settings'])) {
    $obj = unserialize($_SESSION['settings']);
}
else {
    echo "No settings saved.";
}
