<?php
	// ket noi database

require "dbconnect.php";

//truy van du lieu
//LAY dulieu tu server
$query = "SELECT * FROM nganhang";
//mysqli_query: thuc hien truy van $query
$data = mysqli_query($connect, $query);

//tao mang
$mangNh = array();

while($row = mysqli_fetch_assoc($data)){
	array_push($mangNh,
	new NganHang($row['MaNH'], 
		$row['TenNH'] ));
}

//chuyen mang sang dang json
echo json_encode($mangNh);

class NganHang{
	var $id;
	var $ten;
	
	function NganHang($id,$ten){
		$this->id = $id;
		$this->ten = $ten;
	}
}
?>
