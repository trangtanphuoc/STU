<?php
	// ket noi database

require "dbconnect.php";

//truy van du lieu
//LAY dulieu tu server
$query = "SELECT * FROM tramxang";
//mysqli_query: thuc hien truy van $query
$data = mysqli_query($connect, $query);

//tao mang
$mangTramXang = array();

while($row = mysqli_fetch_assoc($data)){
	array_push($mangTramXang, new TramXang(
	$row['MaTramXang'],
	$row['TenTramXang'],
	$row['DiaChi'],
	$row['Rating'],
	$row['Lat'],
	$row['Lng'],
	$row['MaQuan']));
}

//chuyen mang sang dang json
echo json_encode($mangTramXang);

	class TramXang{
		var $id;
		var $ten;
		var $diachi;
		var $rating;
		var $lng;
		var $lat;
		var $maquan;

		
		function TramXang($Ma,$Ten,$DC,$Rt,$Lt,$Lg,$MQ){
			$this->id=$Ma;
			$this->ten=$Ten;
			$this->diachi=$DC;
			$this->rating=$Rt;
			$this->lng=$Lg;
			$this->lat=$Lt;
			$this->maquan=$MQ;;
		}
	}
?>