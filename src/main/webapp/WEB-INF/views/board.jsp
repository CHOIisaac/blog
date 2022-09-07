<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h2>게시물 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form action="" id="form">
        <input type="hidden" name="bno" value="${boardDto.bno}">
        <input type="text" name="title" value="${boardDto.title}" ${mode=="new" ? '' : 'readonly="readonly"'}>
        <textarea name="content" id="" cols="30" rows="10" ${mode=="new" ? '' : 'readonly="readonly"'}>${boardDto.content}</textarea>
        <button type="button" id="writeBtn" class="btn">등록</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    $(document).ready(function (){
        $('#listBtn').on("click", function (){
            location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize} ";
        });

        $('#removeBtn').on("click", function (){
            if(!confirm("정말로 삭제하시겠습니다?")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/board/remove'/>?age=${page}&pageSize=${pageSize}");
            form.attr("method", "POST");
            form.submit();

        });

        $('#writeBtn').on("click", function (){
            let form = $('#form');
            form.attr("action", "<c:url value='/board/remove'/>");
            form.attr("method", "POST");
            form.submit();

        });
    });
</script>
</body>
</html>