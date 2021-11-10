document.getElementById('createReply').addEventListener('click', function() {
    let content = document.getElementById('createReplyContent');
    let writer = document.getElementById('createReplyWriter');
    let boardId = document.getElementById('boardId').value;
    let data = {
        content: content.value,
        writer: writer.value
    }

    $.ajax({
        url: '/api/reply/' + boardId,
        method: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json'
    }).done(function(reply) {
        let replies = document.getElementById('replies');

        alert('댓글이 등록되었습니다.');

        replies.innerHTML +=
            '<li>' +
            '<input hidden class="replyId" value="' + reply.id + '">' +
            '<p>작성자 : <span>' + reply.writer + '</span></p>' +
            '<p>' + reply.content + '</p>' +
            '<p><span>' + reply.createdDate + '</span></p>' +
            '<button class="updateReply">수정</button>' +
            '<button class="deleteReply">삭제</button>' +
            '</li>'

        content.value = '';
        writer.value = '';
    })
})