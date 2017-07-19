
<?php if(isset($_POST['Sua']))
{	?>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Sửa Trạm Xăng</title>
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
			
		//lấy phần tử theo mã gửi qua từ home
		$query ="select * from tramxang where MaTramXang = '$a'";
		$result = mysqli_query($conn, $query);
		$d=mysqli_fetch_array($result);
		
			//kiem tra các trường nhập có để trống
	if( !isset($_POST['TenTramXang']) || !isset($_POST['DiaChi']) || !isset($_POST['Rating']) || !isset($_POST['Lng']) ||!isset($_POST['Lat'])){
		echo "Bạn hãy sửa theo ý của bạn";
	}
	else{
		$ten = $_POST['TenTramXang'];
		$diachi = $_POST['DiaChi'];
		//kiểm tra rating là số và lớn hơn không và là kiểu double
		if(is_numeric($_POST['Rating']) ){
			if($_POST['Rating'] < 0 && $_POST['Rating'] > 10){
				echo "rating nhập không đúng";
			}
			else
			{
				$rating = $_POST['Rating'];
			}
		}
		//kiểm tra lat là số 
		if(is_numeric($_POST['Lat']) ){
				$lat = $_POST['Lat'];
		}
		
		// kiểm tra lng là số
		if(is_numeric($_POST['Lng']) ){
				$lng = $_POST['Lng'];
		}
		
		//lấy giá trị mã quận theo tên quận
		$query = "SELECT * FROM quan WHERE TenQuan='".$_POST['quan']."'";
		$result = mysqli_query($conn, $query);
		$row = mysqli_fetch_assoc($result);
		$maquan = $row["MaQuan"];
		
		//khi nhan nut sửa
		$sql="UPDATE tramxang SET TenTramXang='$ten',
			DiaChi='$diachi',
			Rating='$rating',
			Lat='$lat',
			Lng='$lng',
			MaQuan='$maquan'
			where MaTramXang='$a'";
		mysqli_query($conn, $sql);
		echo "<script language='javascript'>alert('Sua thanh cong');";
		header("location:tramxang.php");
		exit();
	}
	?>
	<form action= ""  method="post" align="center">
				Tên Trạm :</br>  <input type="text" name = "TenTramXang" value="<?php echo $d['TenTramXang'];?>"></br>
				Địa Chỉ: </br> <input type="text" name = "DiaChi"value="<?php echo $d['DiaChi'];?>"></br>
				Rating: </br> <input type="text" name = "Rating"value="<?php echo $d['Rating'];?>"></br>
				Lat: </br> <input type="text" name = "Lat"value="<?php echo $d['Lat'];?>"></br>
				Lng:  </br><input type="text" name = "Lng"value="<?php echo $d['Lng'];?>"></br>
				<?php
					//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
					if(!$conn)
					{
						echo "Connect Failed!". mysqli_connect_error($conn);
					}

					$query = "SELECT * FROM quan WHERE MaQuan";
					$result = mysqli_query($conn, $query);
					
				?>
				Quận: </br> <select name ="quan" >
							<?php
							 $rows = array();
								 while ($row = $row = mysqli_fetch_assoc($result)) {
									 if($row["MaQuan"] == $d["MaQuan"]){
							?>
									  <option  selected="selected"><?php echo $row["TenQuan"];?></option>
							  <?php  
							  } else{
								  ?>
								  <option ><?php echo $row["TenQuan"];?></option>
							   <?php  }
								 }
										mysqli_close($conn);
							 ?>
							</select></br>
				<button type="submit" name="Sua" value="<?php echo $a ?>">Sửa</button>
	</form>
</body>
<?php }
else echo'Ban k co quyen truy cap';?>