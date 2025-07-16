<?php
require __DIR__ . '/../vendor/autoload.php';

$template = $_GET['template'] ?? '';
$name = $_GET['name'] ?? '';

if (!$template || !$name) {
	echo '<form method="GET">
		<input type="text" name="template" style="width: 400px;"><br>
		<input type="text" name="name"><br>
		<button type="submit">Render</button>
	</form>';
	exit;
}

$twig = new \Twig\Environment(
	new \Twig\Loader\ArrayLoader([]), [
		'cache' => false,
		'debug' => true,
	]
);

$twig->addExtension(new \Twig\Extension\DebugExtension());

echo $twig->createTemplate($template)->render(['name' => $name]);

