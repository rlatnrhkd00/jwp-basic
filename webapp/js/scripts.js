$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e){
	e.preventDefault();
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
		type : 'POST',
		url : '/api/qna/addAnswer',
		data : queryString,
		dateType : 'json',
		error : onError,
		success : onSuccess
	});
	
}


function onError(xhr, status){
	alert("로그인을 하세요");
}

function onSuccess(json,status){
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer,new Date(json.createDate),json.contents,json.answerId,json.answerId,json.questionId);
	$(".qna-comment-slipp-articles").prepend(template);
	
	var countTemplate = $("#countTemplate").html();
	var countTemplate2 = countTemplate.format(json.countOfAnswer);
	$("#countAnswer").children().first().replaceWith(countTemplate2);
}

String.prototype.format = function(){
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number){
		return typeof args[number] != 'undefined'
						? args[number] : match;
	});
};

$(".qna-comment").on("click",".form-delete",deleteAnswer);

function deleteAnswer(e){

	e.preventDefault();
	var deleteBtn = $(this);
	var queryString = deleteBtn.closest("form").serialize();

	$.ajax({
		type : 'POST',
		url : '/api/qna/deleteAnswer',
		data : queryString,
		dateType : 'json',
		error : function(xhr, status){
				alert("다른사용자의 글을 삭제할수 없습니다.");
				},
		success : function(json, status){
		
					if(json.status){
						deleteBtn.closest('article').remove();
						var countTemplate = $("#countTemplate").html();
					var countTemplate2 = countTemplate.format(json.countOfAnswer);
					$("#countAnswer").children().first().replaceWith(countTemplate2);
					}
					
				}
	});
}