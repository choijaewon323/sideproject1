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
            '<li class="list-group-item">' +
            '<input hidden class="replyId" value="' + reply.id + '">' +
            '<p>작성자 : <span>' + reply.writer + '</span></p>' +
            '<p class="replyContent">' + reply.content + '</p>' +
            '<p><span>' + reply.createdDate + '</span></p>' +
            '<button class="updateReply btn btn-default">수정</button>' +
            '<button class="deleteReply btn btn-default">삭제</button>' +
            '</li>'

        content.value = '';
    })
})

let tempInnerHTML;
let tempTarget;
let flag = false;

document.getElementById('replies').addEventListener('click', function(e) {
    if (e.target.classList.contains('updateReply')) {
        let li = e.target.parentNode;
        let replyWriter = li.getElementsByClassName('replyWriter')[0];
        let createReplyWriter = document.getElementById('createReplyWriter');

        if (replyWriter.innerText != createReplyWriter.value) {
            alert('댓글을 수정할 권한이 없습니다.');
            return;
        }

        if (flag) {
            tempTarget.innerHTML = tempInnerHTML;
        }

        tempInnerHTML = li.innerHTML;
        let replyId = li.getElementsByClassName('replyId')[0].value;
        let replyContent = li.getElementsByClassName('replyContent')[0];
        let values = replyContent.innerText;

        tempTarget = li;
        flag = true;

        li.innerHTML =
            '<input hidden class="replyId" value="' + replyId + '">' +
            '<p><textarea class="replyUpdateContent">' + values + '</textarea></p>' +
            '<button class="replyUpdateConfirm btn btn-default">수정 완료</button>' +
            '<button class="replyUpdateCancel btn btn-default">취소</button>';

    } else if (e.target.classList.contains('deleteReply')) {
        let li = e.target.parentNode;
        let replyId = li.getElementsByClassName('replyId')[0].value;
        let replyWriter = li.getElementsByClassName('replyWriter')[0];
        let createReplyWriter = document.getElementById('createReplyWriter');

        if (replyWriter.innerText != createReplyWriter.value) {
            alert('댓글을 삭제할 권한이 없습니다.');
            return;
        }

        $.ajax({
            url: '/api/reply/' + replyId,
            method: 'DELETE'
        }).done(function() {
            alert('댓글이 삭제되었습니다.');
            li.remove();
        })
    } else if (e.target.classList.contains('replyUpdateCancel')) {
        let li = e.target.parentNode;
        li.innerHTML = tempInnerHTML;

        flag = false;

    } else if (e.target.classList.contains('replyUpdateConfirm')) {
        let li = e.target.parentNode;
        let replyId = li.getElementsByClassName('replyId')[0].value;
        let content = document.getElementsByClassName('replyUpdateContent')[0].value;

        let data = {
            content: content
        }

        $.ajax({
            url: '/api/reply/' + replyId,
            method: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('댓글이 수정되었습니다.');

            li.innerHTML = tempInnerHTML;

            let replyContent = li.getElementsByClassName('replyContent')[0];
            replyContent.innerText = content;

            flag = false;
        })
    }
})