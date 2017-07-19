<?php
// ket noi database
require "dbconnect.php";


//truy van du lieu
//LAY dulieu tu server
//$MaNH="2";
$MaNH=$_POST["MaNH"];
$query = "SELECT * FROM tramATM WHERE MaNH=$MaNH";
	
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

	function TramATM($id, $ten,$diachi,$Rating,$lng,$lat,$maquan,$manganhang)
	{
		$this->ID = $id;
		$this->Ten = $ten;
		$this->DiaChi= $diachi;
		$this->Rating = $Rating;
		$this->Lng= $lng;
		$this->Lat = $lat;
		$this->MaQuan = $maquan;
		$this->MaNH = $manganhang;
	}
}
?>