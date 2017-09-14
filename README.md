# AsyncTask1

これは、asynctaskを利用したAndroidの非同期処理のサンプルです。

This is a Android programing sample of asynchronous processing using asynctask.

対抗するPHPのコードが必要です。以下のコードを作成し、cloud9等、PHPが動作する環境に配置して下さい

It works with PHP code.Please write the following code, and place it in an PHP environment, such as cloud9.

また、AndroidのコードのURLを修正してください。

Also, fix the Android code URL.

## get1.php
```
<?php
    $ret = 0;
    if(isset($_GET['str'])) {
        $str = $_GET['str'];
        eval(sprintf('$ret=%s;',$str));
    }
    $array = array("ans" => $ret,);
    header("Content-Type: text/javascript; charset=utf-8");
    echo json_encode($array);
?>
```

## post1.php
```
<?php
    $ret = 0;
    if(isset($_POST['str'])) {
        $str = $_POST['str'];
        eval(sprintf('$ret=%s;',$str));
    }
    $array = array("ans" => $ret,);
    header("Content-Type: text/javascript; charset=utf-8");
    echo json_encode($array);
?>
```
