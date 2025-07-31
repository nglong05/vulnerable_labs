<?php
class Wekeup{
    public $tmp;

    function __wakeup(){
        $this->tmp->getFileVul();
    }
}

class Read{
    public $filename;

    function getFileVul(){
        echo file_get_contents($this->filename);
    }
}
?>