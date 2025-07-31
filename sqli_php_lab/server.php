<?php
$server = "db";
$username = "task2_user";
$password = "task2_password";
$dbname = "task2";

$connection = new mysqli($server, $username, $password, $dbname);

if (isset($_GET['input'])) {
    $decoded = str_rot13($_GET['input']);
    #$decoded = $_GET['input'];
    $query = "SELECT * FROM users WHERE email = '$decoded'";
    $result = $connection->query($query);

    $rows_number = $result->num_rows;
    if ($rows_number) {
        echo "Email recieved! Drinks are on their way to your house.";
    } 
    else {
        echo "Email not found";
    }
}

$connection->close();
?>
