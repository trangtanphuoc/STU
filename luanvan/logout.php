<?php
	session_start();
	//unset($_SESSION['username']);
	session_destroy();
	echo $_SESSION['username'];
	$_SESSION['username']='';
	header("Location: index.php");
	exit();
?>