<?php
// ket noi database
require "dbconnect.php";


//truy van du lieu
//LAY dulieu tu server

$MaQuan=$_POST["MaQuan"];
$query = "SELECT * FROM tramxang WHERE MaQuan=$MaQuan";
	
//mysqli_query: thuc hien truy van $query
$data = mysqli_query($connect, $query);

//tao mang
$mangtramxang = array();

//tach ra tung dong
while($row = mysqli_fetch_assoc($data))
{	
	array_push($mangtramxang, new TramXang($row['MaTramXang'], $row['TenTramXang'],$row['DiaChi'], $row['Rating'],$row['Lat'], $row['Lng'],$row['MaQuan']));
}

//chuyen mang sang dang json
echo json_encode($mangtramxang);


//tao class 
class TramXang
{
	var $ID;
	var $Ten;
	var $DiaChi;
	var $Rating;
	var $Lng;
	var $Lat;
	var $MaQuan;

	function TramXang($id, $ten,$diachi,$Rating,$lng,$lat,$maquan)
	{
		$this->ID = $id;
		$this->Ten = $ten;
		$this->DiaChi= $diachi;
		$this->Rating = $Rating;
		$this->Lng= $lng;
		$this->Lat = $lat;
		$this->MaQuan = $maquan;
	}
}
?>