<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script >
$(function(){
	$.get("it", {rss:'https://www.hani.co.kr/rss/'}, function(data){
		console.log(data);
		var dataXml = $.parseXML(data);
		console.log(dataXml);
		
		var str = "<table border = '1'>"
		$(dataXml).find("item").each(function(){
			var title = $(this).find("title").text();
			var link = $(this).find("link").text();
			var description = $(this).find("description").text();
			var author = $(this).find("author").text();
			var pubDate = $(this).find("pubDate").text();

			str += "<tr>"
			str += "<td><a href='" + link + "'>" + title + "</a></td>"
			str += "<td>" + author + "</td>"
			str += "<td>" + pubDate + "</td>"
			str += "</tr>"
			console.log(title, link, description, author, pubDate);
		})
		str += "</table>"
		
		$("body").append(str);
	},"text");
})
// $(function(){
// 	$.get("student.xml", function(data){
// 		console.log(data);
// 		var dataXml = $.parseXML(data);
// 		console.log(dataXml);
		
// 		$(dataXml).find("student").each(function(){
// 			var no = $(this).find("no").text();
// 			var name = $(this).find("name").text();
// 			var score = $(this).find("score").text();

// 			console.log(no, name, score);
// 		})
// 	},"text");
// })
</script>
</head>
<body>

</body>
</html>