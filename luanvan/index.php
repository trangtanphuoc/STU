<?php
   ob_start();
   session_start();
?>
 
<?
   // error_reporting(E_ALL);
   // ini_set("display_errors", 1);
?>
 
<html lang = "en">
   
   <head>
      <title>Đăng Nhập</title>
      <!-- <link href = "css/bootstrap.min.css" rel = "stylesheet">  -->
      
      <style>
         body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #ADABAB;
         }
         
         .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
            color: #017572;
         }
         
         .form-signin .form-signin-heading,
         .form-signin .checkbox {
            margin-bottom: 10px;
         }
         
         .form-signin .checkbox {
            font-weight: normal;
         }
         
         .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
         }
         
         .form-signin .form-control:focus {
            z-index: 2;
         }
         
         .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
            border-color:#017572;
         }
         
         .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
            border-color:#017572;
         }
         
         h2{
            text-align: center;
            color: #017572;
         }
      </style>
      
   </head>
	
   <body>
      
      <h2>Đăng Nhập</h2> 
      <div class = "container form-signin">
         
         <?php
            $msg = '';
            
            if (isset($_POST['login']))
				{
				
               if ($_POST['username'] == 'admin' && 
                  $_POST['password'] == 'admin') {
                  $_SESSION['valid'] = true;
                  $_SESSION['timeout'] = time();
                  $_SESSION['username'] = 'admin';
                  header("Location: home.php");
                  echo 'Đăng nhập thành công';
               }else {
                  $msg = 'Tên đăng nhập hay mật khẩu sai rồi';
               }
            }			
         ?>
      </div> <!-- /container -->
      
      <div class = "container">
      
         <form class = "form-signin" role = "form" action=""
          method = "post">
            <h4 class = "form-signin-heading"><?php echo $msg; ?></h4>
            <input type = "text" onkeypress	='return event.charCode >=65 && evet.charCode <=90 || event.charCode >=97 &&event.charCode<=122' class = "form-control" 
               name = "username" placeholder = "username = abc" 
               required autofocus></br>
            <input type = "password" onkeypress	='return event.charCode >=65 && evet.charCode <=90 || event.charCode >=97 &&event.charCode<=122' class = "form-control"
               name = "password" placeholder = "password = 123" required>
            <button class = "btn btn-lg btn-primary btn-block" type = "submit" 
               name = "login" >Login</button>
         </form>
			
   
      </div> 
      
   </body>
</html>