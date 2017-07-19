<?php if(isset($_POST['Sua']))
{	?>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Sửa Ngân Hàng</title>
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
		
		$query ="select * from nganhang where MaNH = ".$a;
		$result = mysqli_query($conn, $query);
		$d=mysqli_fetch_array($result);
	
		if(!isset($_POST['TenNH'])){
			echo "Xin vui lòng nhập lại tên ngân hàng";
		}
		else{
			$tennganhang = $_POST['TenNH'];
			$sql="update nganhang set TenNH='".tennganhang."' where MaNH='".$a."'";
			mysqli_query($conn, $sql);
			header("location:nganhang.php");
			exit();
		}
	?>
	<form action="" method="post" name="form1" align="center">
		<table align="left" width="400">
			<tr>
				<td align="right">
					Tên Ngân Hàng
				</td>
				<td>
					<input type="text" name="TenNH" value="<?php echo $d['TenNH'];?>" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="hidden" name="MaNH" value="<?php echo $_POST['Sua'];?>" />
					<input type="submit" name="Sua" value="<?php echo $_POST['Sua'];?>"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<?php }
else echo'Ban k co quyen truy cap';?>