<?php //ob_start();
if(isset($_POST['Xoa'])){
		$a = $_POST['Xoa'];
	
  //$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
			if(!$conn)
			{
				echo "Connect Failed!". mysqli_connect_error($conn);
			}
	
	$sql="DELETE FROM tramatm where MaTramATM='".$a."'";
	mysqli_query($conn, $sql);
	echo "<script language='javascript'>alert('Xóa thành công');";
	echo "location.href='tramatm.php';</script>";
	exit();
	}
	else echo'Ban k co quyen truy cap';
?>