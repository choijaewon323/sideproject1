let board = {
    init() {
        // buttons
        let createBoard = document.getElementById('createBoard');
        let updateBoardButton =  document.getElementById('updateBoardButton');
        let updateBoard =  document.getElementById('updateBoard');
        let deleteBoard =  document.getElementById('deleteBoard');

        let title = document.getElementById('title');
        let content = document.getElementById('content');
        let writer = document.getElementById('writer');
        let createReplyWriter = document.getElementById('createReplyWriter');
        let boardWriter = document.getElementById('boardWriter');
        let boardId =  document.getElementById('boardId');
        
        if (createBoard !== null) {
            createBoard.addEventListener('click', function() {
                $.ajax({
                    url: '/api/board',
                    method: 'POST',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        title: title.value,
                        content: content.value,
                        writer: writer.value
                    })
        
                }).done(function() {
                    alert('게시물이 등록되었습니다.');
                    window.location.href = "/";
        
                }).fail(function(error) {
                    alert(error);
                })
            })
        }
        
        if (updateBoardButton !== null) {
            updateBoardButton.addEventListener('click', function() {
                if (createReplyWriter.value !== boardWriter.innerText) {
                    alert('수정할 권한이 없습니다.');
                } else {
                    window.location.href = "/board/update/" + boardId.value;
                }
            })
        }
        
        if (updateBoard !== null) {
            updateBoard.addEventListener('click', function() {
                $.ajax({
                    url: '/api/board/' + boardId.value,
                    method: 'PUT',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        title: document.getElementById('title').value,
                        content: document.getElementById('content').value,
                        writer: document.getElementById('writer').value
                    })
        
                }).done(function() {
                    alert('게시물이 수정되었습니다.');
                    window.location.href = "/board/" + boardId.value;
        
                }).fail(function(error) {
                    alert(error);
                })
            })
        }
        
        if (deleteBoard !== null) {
            deleteBoard.addEventListener('click', function() {
                if (createReplyWriter.value !== boardWriter.innerText) {
                    alert('수정할 권한이 없습니다.');
                } else {
                    $.ajax({
                        url: '/api/board/' + boardId.value,
                        method: 'DELETE'
        
                    }).done(function() {
                        alert('게시물이 삭제되었습니다.');
                        window.location.href = "/";
        
                    }).fail(function(error) {
                        alert(error);
                    })
                }
            })
        }
    }
}

board.init();