let reply = {
    init() {
        // buttons
        let createReply = document.getElementById('createReply');
        
        let createReplyContent = document.getElementById('createReplyContent');
        let createReplyWriter = document.getElementById('createReplyWriter');
        let boardId = document.getElementById('boardId');
        let replies = document.getElementById('replies');
        
        // updateFlags
        let tempInnerHTML;
        let tempTarget;
        let flag = false;
        
        createReply.addEventListener('click', function() {
            $.ajax({
                url: '/api/reply/' + boardId.value,
                method: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({
                    content: createReplyContent.value,
                    writer: createReplyWriter.value
                }),
                dataType: 'json'
        
            }).done(function(reply) {
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
        
                createReplyContent.value = '';
        
            }).fail(function(error) {
                alert(error);
            })
        })
        
        replies.addEventListener('click', function(event) {
            let classList = event.target.classList;
            let li = event.target.parentNode;

            let replyWriter = li.getElementsByClassName('replyWriter')[0];
            let replyId = li.getElementsByClassName('replyId')[0];
            let replyContent = li.getElementsByClassName('replyContent')[0];
            let replyUpdateContent = document.getElementsByClassName('replyUpdateContent')[0];

            // functions
            let updateReplyFunc = function() {
                if (replyWriter.innerText != createReplyWriter.value) {
                    alert('댓글을 수정할 권한이 없습니다.');
                    return;
                }
        
                if (flag) {
                    tempTarget.innerHTML = tempInnerHTML;
                }
        
                tempInnerHTML = li.innerHTML;
                tempTarget = li;
                flag = true;
        
                li.innerHTML =
                    '<input hidden class="replyId" value="' + replyId.value + '">' +
                    '<p><textarea class="replyUpdateContent">' + replyContent.innerText + '</textarea></p>' +
                    '<button class="replyUpdateConfirm btn btn-default">수정 완료</button>' +
                    '<button class="replyUpdateCancel btn btn-default">취소</button>';
        
            }
        
            let deleteReplyFunc = function() {
                if (replyWriter.innerText != createReplyWriter.value) {
                    alert('댓글을 삭제할 권한이 없습니다.');
                    return;
                }
        
                $.ajax({
                    url: '/api/reply/' + replyId.value,
                    method: 'DELETE'
        
                }).done(function() {
                    alert('댓글이 삭제되었습니다.');
                    li.remove();
                    
                }).fail(function(error) {
                    alert(error);
                })
            }
        
            let replyUpdateConfirmFunc = function() {
                $.ajax({
                    url: '/api/reply/' + replyId.value,
                    method: 'PUT',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        content: replyUpdateContent.value
                    })
        
                }).done(function() {
                    alert('댓글이 수정되었습니다.');
        
                    li.innerHTML = tempInnerHTML;
                    flag = false;
        
                    li.getElementsByClassName('replyContent')[0].innerText = replyUpdateContent.value;
                    
                }).fail(function(error) {
                    alert(error);
                })
            }
        
            let replyUpdateCancelFunc = function() {
                li.innerHTML = tempInnerHTML;
                flag = false;
            }

            // 이벤트 분기
            if (classList.contains('updateReply')) {
                updateReplyFunc();
        
            } else if (classList.contains('deleteReply')) {
                deleteReplyFunc();
        
            } else if (classList.contains('replyUpdateCancel')) {
                replyUpdateCancelFunc();
        
            } else if (classList.contains('replyUpdateConfirm')) {
                replyUpdateConfirmFunc();
            }
        })
    }
}

reply.init();