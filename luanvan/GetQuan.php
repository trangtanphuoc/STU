<?php
	// ket noi database

require "dbconnect.php";

//truy van du lieu
//LAY dulieu tu server
$query = "SELECT * FROM quan";
//mysqli_query: thuc hien truy van $query
$data = mysqli_query($connect, $query);

//tao mangquan
$mangQuan = array();

while($row = mysqli_fetch_assoc($data)){
	array_push($mangQuan,
	new Quan($row['MaQuan'], 
		$row['TenQuan'] ));
}

//chuyen mang sang dang json
echo json_encode($mangQuan);

class Quan{
	var $id;
	var $ten;
	
	function Quan($id,$ten){
		$this->id = $id;
		$this->ten = $ten;
	}
}
?>