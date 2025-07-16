<?php
require __DIR__ . '/../vendor/autoload.php';

$template  = $_GET['template'] ?? '';
$name = $_GET['name'] ?? '';

if (!$template || !$name) {
    echo '<form method="GET">
        <input name="template">
        <input name="name">
        <button>Run</button>
    </form>';
    exit;
}

$smarty = new Smarty();
$smarty->setTemplateDir(__DIR__);
$smarty->setCompileDir(sys_get_temp_dir() . '/smarty-compile');
if (!is_dir($smarty->getCompileDir())) {
    mkdir($smarty->getCompileDir(), 0777, true);
}

$smarty->assign('name', $name);
$smarty->display('string:' . $template);

