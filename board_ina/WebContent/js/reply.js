/**
 * ReplyService Module
 */

const replyService = (function() {

	//전체조회목적
	function list(bno, callback, cp) {
		var reply = {bno : bno}
		$.getJSON(cp + "/replies", reply, function(data){
			if(callback) callback(data);
		 });
	}
	//단일조회목적
	function get(rno, callback, cp) {
		var reply = {rno : rno}
		$.getJSON(cp + "/reply", reply, function(data){
			if(callback) callback(data);
		 });
	}
	//댓글쓰기목적
	function add(reply, callback, cp) {
		var reply = JSON.stringify(reply); // object였던것들이 문자열ㅇ타입으로
		$.post(cp + "/reply", reply, function(data){
			if(callback) callback(data);
		 });
	}
	//댓글수정목적
	function modify(reply, callback, cp) {
		var reply = JSON.stringify(reply); // object였던것들이 문자열ㅇ타입으로
		$.ajax({
			url : cp + "/reply", //ReplyController 가리킴
			success : function(data){
				if(callback) callback(data);
			},
			method : "PUT", //데이터 전송방식
			data : reply
		});
	}
	//댓글삭제목적
	function remove(reply, callback, cp) {
		var reply = JSON.stringify(reply); // object였던것들이 문자열ㅇ타입으로
		$.ajax({
			url : cp + "/reply", //ReplyController 가리킴
			success : function(data){
				if(callback) callback(data);
			},
			method : "DELETE", //데이터 전송방식
			data : reply
		});
	}
	return{ 
		get : get,
		list : list,
		add : add,
		modify : modify,
		remove : remove
	}
})();