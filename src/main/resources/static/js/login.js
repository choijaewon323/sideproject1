if (document.getElementById('register') !== null) {
    document.getElementById('register').addEventListener('click', function() {
        let data = {
            account: document.getElementById('account').value,
            password: document.getElementById('password').value
        }
    
        $.ajax({
            url: '/api/user',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json'
        }).done(function(result) {
            if (result === true) {
                alert('계정이 등록되었습니다.');
                window.location.href = "/login";
            } else {
                alert('이미 있는 계정입니다.');
            }
        }).fail(function(error) {
            alert(error);
        })
    })
}

if (document.getElementById('login') !== null) {
    document.getElementById('login').addEventListener('click', function() {
        let data = {
            account: document.getElementById('account').value,
            password: document.getElementById('password').value
        }
    
        $.ajax({
            url: '/api/user/auth',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json'
        }).done(function(result) {
            if (result === "USER_CORRECT") {
                alert('로그인 성공');
                window.location.href = "/";
            } else if (result === "ACCOUNT_WRONG") {
                alert('계정이 올바르지 않습니다.');
            } else if (result === "PASSWORD_WRONG") {
                alert('비밀번호가 올바르지 않습니다.');
            }
        }).fail(function(error) {
            alert(error);
        })
    })
}

if (document.getElementById('logout') !== null) {
    document.getElementById('logout').addEventListener('click', function() {
        $.ajax({
            url: '/api/user/logout',
            method: 'POST'
        }).done(function() {
            alert('로그아웃 성공');
            window.location.href = '/login';
        }).fail(function(error) {
            alert(error);
        })
    })
}