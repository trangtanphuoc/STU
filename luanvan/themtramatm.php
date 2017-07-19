<?php //ob_start();
	//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
		if(!$conn)
		{
			echo "Connect Failed!". mysqli_connect_error($conn);
		}
	
	
	
	//kiem tra các trường nhập có để trống
	if( $_POST['TenTramATM'] == NULL || $_POST['DiaChi'] == NULL || $_POST['Rating'] == NULL ||$_POST['Lng'] == NULL ||$_POST['Lat'] == NULL){
		echo "không được để trống bạn ơi";
	}
	
	$ten = $_POST['TenTramATM'];
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
	else{
		echo "rating nhập không phải là số";
	}
	
	//kiểm tra lat là số 
	if(is_numeric($_POST['Lat']) ){
			$lat = $_POST['Lat'];
	}
	else{
		echo "lat nhập không phải là số";
	}
	
	// kiểm tra lng là số
	if(is_numeric($_POST['Lng']) ){
			$lng = $_POST['Lng'];
	}
	else{
		echo "lat nhập không phải là số";
	}
	
	//lấy giá trị mã ngân hàng theo tên ngân hàng
	$query = "SELECT * FROM nganhang WHERE TenNH='".$_POST['nganhang']."'";
	$result = mysqli_query($conn, $query);
	$row = mysqli_fetch_assoc($result);
	$manh = $row["MaNH"];
	
	//lấy giá trị mã quận theo tên quận
	$query = "SELECT * FROM quan WHERE TenQuan='".$_POST['quan']."'";
	$result = mysqli_query($conn, $query);
	$row = mysqli_fetch_assoc($result);
	$maquan = $row["MaQuan"];
	
	
	

	$sl = "INSERT INTO tramatm VALUES (NULL, '$ten','$diachi','$rating','$lnt','$lat','$maquan','$manh')";
    if( mysqli_query($conn, $sl))
    {
        echo "<script language='javascript'>alert('Them thanh cong');";
		echo "location.href='tramatm.php';</script>";
    }
?>