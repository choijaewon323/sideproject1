if (document.getElementById('createBoard') !== null) {
    document.getElementById('createBoard').addEventListener('click', function() {
        let title = document.getElementById('title').value;
        let content = document.getElementById('content').value;
        let writer = document.getElementById('writer').value;
        let data = {
            title: title,
            content: content,
            writer: writer
        }

        $.ajax({
            url: '/api/board',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('게시물이 등록되었습니다.');
            window.location.href = "/";
        }).fail(function(error) {
            alert(error);
        })
    })
}

if (document.getElementById('updateBoardButton') !== null) {
    document.getElementById('updateBoardButton').addEventListener('click', function() {
        if (document.getElementById('createReplyWriter').value !== document.getElementById('boardWriter').innerText) {
            alert('수정할 권한이 없습니다.');
        } else {
            let boardId = document.getElementById('boardId');

            window.location.href = "/board/update/" + boardId.value;
        }
    })
}

if (document.getElementById('updateBoard') !== null) {
    document.getElementById('updateBoard').addEventListener('click', function() {
        let title = document.getElementById('title').value;
        let content = document.getElementById('content').value;
        let writer = document.getElementById('writer').value;
        let id = document.getElementById('boardId').value;
        let data = {
            title: title,
            content: content,
            writer: writer
        }

        $.ajax({
            url: '/api/board/' + id,
            method: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('게시물이 수정되었습니다.');
            window.location.href = "/board/" + id;
        }).fail(function(error) {
            alert(error);
        })
    })
}

if (document.getElementById('deleteBoard') !== null) {
    document.getElementById('deleteBoard').addEventListener('click', function() {
        let id = document.getElementById('boardId').value;

        if (document.getElementById('createReplyWriter').value !== document.getElementById("boardWriter").innerText) {
            alert('수정할 권한이 없습니다.');
        } else {
            $.ajax({
                url: '/api/board/' + id,
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