<?php
ob_start();
   session_start();
if(!isset($_SESSION['username']))
{
	echo"k thedangnhap";
}
else
{
   
   //$_SESSION['name']='admin';
  
?>
	<head>
		<title>Quản Lý</title>
		<style>
			#menu ul {
				background: #8AD385;
				padding: 0;
				list-style-type: none;
				text-align: left;
			}	
			#menu li {
				width: auto;
				height: 40px;
				line-height: 40px;
				border-bottom: 1px solid #e8e8e8;
				padding: 0 1em;
			}
			#menu li a {
				text-decoration: none;
				color: #333;
				font-weight: bold;
				display: block;
			}
			#menu li:hover {
				background: #CDE2CD;
			}
		 h2{
				text-align: center;
				color: #017572;
				font-size: 50px;
				font-weight:bold;
			 }
			 #content {
				 border: 0px solid #CDCDCD;
				 float:left;
				 padding-left:90px;
				 margin-left: 5px;
				 margin-right: 5px;
				 margin-bottom: 5px;
			}
			#footer{
			 padding-top:170px;
				text-align: center;
				height:10px;
				margin-bottom: 5px;
				margin-top: 25px;
				clear: both;
				border: 0px solid #CDCDCD;
				padding-bottom:0px;
			}
			body{
				font-family: Arial, Tahoma;
				font-size: 12px;
			}
			#left{
				 width: 200px;
				 border: 0px solid #CDCDCD;
				 float:left;
				 margin-bottom: 5px;
			}
			#right{
				 width: 200px;
				 border: 0x solid #CDCDCD;
				 float:right;
				 margin-bottom: 5px;
			}
			#combobox{
				width: 200px;
				margin-bottom: 10px;
			}
		</style>
	</head>
		 
	<body>
	 <h2>Quản Lý Quận Huyện</h2> 
			
			<div id="left">
				<div id="menu">
					<ul>
						<li><a href="tinhthanh.php">Tỉnh-Thành</a></li>
						<li><a href="#">Quận Huyện</a></li>
						<li><a href="nganhang.php">Ngân Hàng</a></li>
						<li><a href="tramatm.php">Trạm ATM</a></li>
						<li><a href="tramxang.php">Trạm Xăng</a></li>
						<li><a href="logout.php" <?php echo $_SESSION['username']; ?>>Thoát</a></li>
					</ul>
				</div>
			</div>
			<div id="content">
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
				<form name="myTinh" action= ""  method="post">
				Tỉnh-Thành: </br> <select id = "combobox" name ="tinhthanh">
							<?php
							 $rows = array();
								 while ($row = $row = mysqli_fetch_assoc($result)) {
							?>
								 <option name ="chon" value="<?php echo $row["TenTinh"];?>"> <?php echo $row["TenTinh"];?></option>
									 
							  <?php  
							  } 
							 ?>
							 
							</select><input type="submit" name="Chon" value="Chọn"/>
				</form>
				<?php
				if(isset($_POST['Chon'])){
					$sql = "SELECT * FROM tinhthanh WHERE TenTinh='".$_POST['tinhthanh']."'";
					$result = mysqli_query($conn, $sql);
					$row = mysqli_fetch_assoc($result);
					$matinh = $row["MaTinh"];
					$query = "SELECT * FROM quan WHERE MaTinh ='".$matinh."'";
					$result = mysqli_query($conn, $query);
				}
				?>
				
				<table align = "center" border = "1">
					<thead>
						<tr align="center" >
							<th width="100">ID</th>
							<th width="500">Tên Quận Huyện</th>	
						</tr>
					</thead>
		
					<tbody>
					 <?php
					 $rows = array();
						 while ($row = $row = mysqli_fetch_assoc($result)) {
					?>
							<tr>
								<td align="center"><?php echo $row["MaQuan"];?></td>
								<td><?php echo $row["TenQuan"];?></td>	<form method="post" action="suaquan.php">
								<td width="50" align="center"><button type = "submit" name = "Sua" value="<?php echo $row["MaQuan"];?>" >
								Sửa </button></form></td>
								<form method="post" action="xoaquan.php">
								<td width="50" align="center"><button type = "submit" name = "Xoa"value="<?php echo $row["MaQuan"];?>">
								Xóa </button></form></td>
								
								
									<!--  <a href="xoaquan.php?idTL=<?php echo $row["MaQuan"];?>"> OK </a> -->
							</tr>
							 <?php  } 
								mysqli_close($conn);
							 ?>
					</tbody>
				</table>
				
					
			</div>
			<div id ="right">
			<form name="myForm" action= ""  method="post" onsubmit="return xulyThem()">
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
				
				Tỉnh-Thành: </br> <select id = "combobox" name ="tinhthanh">
							<?php
							 $rows = array();
								 while ($row = $row = mysqli_fetch_assoc($result)) {
							?>
								 <option value="<?php echo $row["TenTinh"];?>"> <?php echo $row["TenTinh"];?></option>
									 
							  <?php  
							  } 
							 ?>
							 
							</select></br>
				Tên Quận Huyện:  <input type="text" name = "txtTenQuan"></br>
				<input type="submit" name="Them" />
				<button type="reset" name="Huy" >Hủy</button>
			</form>
				<?php 
				if(isset($_POST['Them'])){
					
					$u = "";
					if($_POST['txtTenQuan'] == NULL){
						echo "Vui long nhập lại tên quận";
					}
					else
					 {
					  $u=$_POST['txtTenQuan'];
					 }
					//lấy giá trị mã tinh theo tên tỉnh
					$sql = "SELECT * FROM tinhthanh WHERE TenTinh='".$_POST['tinhthanh']."'";
					$result = mysqli_query($conn, $sql);
					$row = mysqli_fetch_assoc($result);
					$matinh = $row["MaTinh"];

					

					$query = "SELECT * FROM quan WHERE TenQuan='".$u."'";
					$result = mysqli_query($conn, $query);
					 if(mysqli_num_rows($result) != "" ){
						 echo "Tên Quận này đã tồn tại";
					 }
					 else{
						  $query = "INSERT INTO quan VALUES (NULL, '".$u."', '".$matinh."')";
						  
						  $result = mysqli_query($conn, $query);
						  header("Refresh:0");
						  echo "<script language='javascript'>alert('Them thanh cong');";
					 }
				}
				?>
			</div>
			
			<div id="footer">
				<div id="right">
					Copyright ©luanvantimtramatm.esy.es
					Email: trangtanphuoc1517@gmail.com
				</div>
				<div >
					Đây là luận văn của tôi có gì mong mọi người chỉ giáo, thông qua gmail .Cheers </br>
					Chuyện trên thế gian không có đúng, không có sai, không có thiện, không có ác, có gì thiện ác đúng sai chỉ koi tấm lòng mà thôi</br>
					Buồn trong lòng ta, vui trong lòng ta, mọi việc chỉ do ta, ngoại cảnh đối với ta chẳng liên quan gì
				</div>
			</div>
		</div>
	</body>
<?php
} ?>