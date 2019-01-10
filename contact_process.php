<?php

    $to = "julia_bristow@gwu.edu";
    $from = $_REQUEST['email'];
    $name = $_REQUEST['name'];
    $subject = $_REQUEST['subject'];
    $number = $_REQUEST['number'];
    $cmessage = $_REQUEST['message'];

    $headers = "From: $from";
	$headers = "From: " . $from . "\r\n";
	$headers .= "Reply-To: ". $from . "\r\n";
	$headers .= "MIME-Version: 1.0\r\n";
	$headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";

    $subject = "You have a message from your website!";

    $logo = 'img/logo.png';
    $link = '#';

	$body = "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><title>Express Mail</title></head><body>";
	$body .= "<table style='width: 100%;'>";
	$body .= "<thead style='text-align: center;'><tr><td style='border:none;' colspan='2'>";
	$body .= "<a href='{$link}'><img src='{$logo}' alt=''></a><br><br>";
	$body .= "</td></tr></thead><tbody><tr>";
	$body .= "<td style='border:none;'><strong>Name:</strong> {$name}</td>";
	$body .= "<td style='border:none;'><strong>Email:</strong> {$from}</td>";
	$body .= "</tr>";
	$body .= "<tr><td style='border:none;'><strong>Subject:</strong> {$csubject}</td></tr>";
	$body .= "<tr><td></td></tr>";
	$body .= "<tr><td colspan='2' style='border:none;'>{$cmessage}</td></tr>";
	$body .= "</tbody></table>";
	$body .= "</body></html>";

	$to = 'julia_bristow@gwu.edu'; // replace this mail with yours
	$firstname = $_POST["fname"];
	$email= $_POST["email"];
	$headers = 'MIME-Version: 1.0' . "\r\n";
	$headers .= "From: " . $email . "\r\n"; // Sender's E-mail
	$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
	$message ='<table style="width:100%">
	        <tbody><tr>
	                <td>'.$firstname.'  '.$laststname.'</td>
	        </tr>
	        <tr><td>Email: '.$email.'</td></tr>
	</tbody></table>';
	if (@mail($to, $email, $message, $headers))
	{
	    echo 'The message has been sent.';
	}else{
	    echo 'failed';
	}

    $send = mail($to, $subject, $body, $headers);

?>