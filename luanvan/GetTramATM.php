<?php
	// ket noi database

require "dbconnect.php";

//truy van du lieu
//LAY dulieu tu server
$query = "SELECT * FROM tramatm";
//mysqli_query: thuc hien truy van $query
$data = mysqli_query($connect, $query);

//tao mang
$mangTramATM = array();

while($row = mysqli_fetch_assoc($data)){
	array_push($mangTramATM, new TramATM(
	$row['MaTramATM'],
	$row['TenTramATM'],
	$row['DiaChi'],
	$row['Rating'],
	$row['Lat'],
	$row['Lng'],
	$row['MaQuan'],
	$row['MaNH']));
}

//chuyen mang sang dang json
echo json_encode($mangTramATM);

	class TramATM{
		var $id;
		var $ten;
		var $diachi;
		var $rating;
		var $lng;
		var $lat;
		var $maquan;
		var $manh;
		
		function TramATM($Ma,$Ten,$DC,$Rt,$Lt,$Lg,$MQ,$MNH){
			$this->id=$Ma;
			$this->ten=$Ten;
			$this->diachi=$DC;
			$this->rating=$Rt;
			$this->lat=$Lt;
			$this->lng=$Lg;
			$this->maquan=$MQ;
			$this->manh=$MNH;
		}
	}
?>