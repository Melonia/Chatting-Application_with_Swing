<?php
$id=$_POST["id"];
$password=$_POST["password"];
$name=$_POST["name"];

$mysql_host="localhost";
$mysql_user="davichiar1";
$mysql_password="a1b1c1**";
$mysql_db="davichiar1";

$conn=mysqli_connect($mysql_host,$mysql_user,$mysql_password,$mysql_db);

if(!$conn){
	die("연결 실패: " . mysqli_connect_error());
}

$sql=" INSERT INTO chatting_user (id, password, name) VALUES ('$id', '$password','$name');";

if(mysqli_query($conn, $sql)){
	echo "가입 완료";
}
else{
	echo "가입 실패: " . mysqli_error($conn);
}
mysqli_close($conn);
?>