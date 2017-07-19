
<?php if(isset($_POST['Sua']))
{	?>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Sửa Tỉnh-Thành</title>
</head>
<body>
	<?php 
	$a=$_POST['Sua'];
	
		//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
			if(!$conn)
			{
				echo "Connect Failed!". mysqli_connect_error($conn);
			}
		
		$query ="select * from tinhthanh where MaTinh = ".$a;
		$result = mysqli_query($conn, $query);
		$d=mysqli_fetch_array($result);
	
		if(!isset($_POST['Tentỉnh'])){
			echo "Xin vui lòng nhập lại tên tỉnh-thành";
		}
		else{
			$tentinh = $_POST['Tentỉnh'];
			$query = "SELECT * FROM tinhthanh WHERE TenTinh='".$tentinh."'";
			$result = mysqli_query($conn, $query);
		
			if(mysqli_num_rows($result) != "" ){
				 echo "Tên Tỉnh-Thành này đã tồn tại";
			}
			else{
			$sql="update tinhthanh set TenTinh='".$tentinh."' where MaTinh='".$_POST['Sua']."'";
			echo $sql;
			mysqli_query($conn, $sql);
			header("location:tinhthanh.php");
			exit();
			}
		}
	?>
	<form action="" method="post" name="form1" align="center">
		<table align="left" width="400">
			<tr>
				<td align="right">
					Tên Tỉnh
				</td>
				<td>
					<input type="text" name="Tentỉnh" value="<?php echo $d['TenTinh'];?>" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="hidden" name="MaTinh" value="<?php echo $_POST['Sua'];?>" />
					<input type="submit" name="Sua" value="<?php echo $_POST['Sua'];?>"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<?php }
?>