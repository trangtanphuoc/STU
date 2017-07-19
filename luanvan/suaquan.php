
<?php if(isset($_POST['Sua']))
{	?>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Sửa Quận</title>
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
		
		$query ="select * from quan where MaQuan = ".$a;
		$result = mysqli_query($conn, $query);
		$d=mysqli_fetch_array($result);
	
		if(!isset($_POST['TenQuận'])){
			echo "Xin vui lòng nhập lại tên quận";
		}
		
		else{
			$tenquan = $_POST['TenQuận'];
			$query = "SELECT * FROM quan WHERE TenQuan='".$tenquan."'";
			$result = mysqli_query($conn, $query);
		
			if(mysqli_num_rows($result) != "" ){
				 echo "Tên Quận này đã tồn tại";
			}
			else{
				//lấy giá trị mã quận theo tên quận
				$query = "SELECT * FROM tinhthanh WHERE TenTinh='".$_POST['tinhthanh']."'";
				$result = mysqli_query($conn, $query);
				$row = mysqli_fetch_assoc($result);
				$matinh = $row["MaTinh"];
				//update
				$sql="update quan set TenQuan='".$tenquan."',
				MaTinh ='".$matinh."'
				where MaQuan='".$_POST['Sua']."'";
				echo $sql;
				mysqli_query($conn, $sql);
				header("location:home.php");
				exit();
			}
		}
	?>
	<form action="" method="post" name="form1" align="center">
		<table align="left" width="400">
			<tr>
				<td align="right">
					Tên Quận:
				</td>
				<td>
					<input type="text" name="TenQuận" value="<?php echo $d['TenQuan'];?>" />
				</td>
			</tr>
			<tr>
				<td>
					Tỉnh-Thành:
				</td>
				<td>
					<?php
					//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
					if(!$conn)
					{
						echo "Connect Failed!". mysqli_connect_error($conn);
					}

					$query = "SELECT * FROM tinhthanh WHERE MaTinh";
					$result = mysqli_query($conn, $query);
					
				?>
				</br> <select name ="tinhthanh" >
							<?php
							 $rows = array();
								 while ($row = $row = mysqli_fetch_assoc($result)) {
									 if($row["MaTinh"] == $d["MaTinh"]){
							?>
									  <option  selected="selected"><?php echo $row["TenTinh"];?></option>
							  <?php  
							  } else{
								  ?>
								  <option ><?php echo $row["TenTinh"];?></option>
							   <?php  }
								 }
										mysqli_close($conn);
							 ?>
							</select></br>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="hidden" name="MaQuan" value="<?php echo $_POST['Sua'];?>" />
					<input type="submit" name="Sua" value="<?php echo $_POST['Sua'];?>"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<?php }
?>