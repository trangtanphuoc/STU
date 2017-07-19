<?php
// ket noi database
require "dbconnect.php";


//truy van du lieu
//LAY dulieu tu server
//$maquan = "5";
$maquan = $_POST['MaQuan'];
$query = "SELECT *  FROM  `tramatm` WHERE MaQuan = '$maquan'";
	
//mysqli_query: thuc hien truy van $query
$data = mysqli_query($connect, $query);

//tao mang
$mangtramATM = array();

//tach ra tung dong
while($row = mysqli_fetch_assoc($data))
{	
	array_push($mangtramATM, new TramATM($row['MaTramATM'], $row['TenTramATM'],$row['DiaChi'], $row['Rating'],$row['Lat'], $row['Lng'],$row['MaQuan'], $row['MaNH']));
}

//chuyen mang sang dang json
echo json_encode($mangtramATM);


//tao class 
class TramATM
{
	var $ID;
	var $Ten;
	var $DiaChi;
	var $Rating;
	var $Lng;
	var $Lat;
	var $MaQuan;
	var $MaNH;

	function TramATM($id, $ten,$diachi,$rating,$lng,$lat,$maquan,$manganhang)
	{
		$this->ID = $id;
		$this->Ten = $ten;
		$this->DiaChi= $diachi;
		$this->Rating = $rating;
		$this->Lng= $lng;
		$this->Lat = $lat;
		$this->MaQuan = $maquan;
		$this->MaNH = $manganhang;
	}
}

if(mysqli_query($connect, $query))
{
	echo "1";//so 1: Them thanh cong
}
else{
	echo "0";//so them bi loi
}
?>