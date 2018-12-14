<?php
if(isset($_GET['email'])){ 

$email = $_GET['email']; 
$message = $_GET['msg']; 

$to = "chu1070y@naver.com"; 
$subject = "Demo email"; 

$headers = 'MIME-Version: 1.0' . "\r\n"; 
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n"; 
$headers .= 'From:' .$email. "\r\n";    
mail($to, $subject, $message, $headers); 
echo'success'; 
} 
else{ echo'error'; } 
?>