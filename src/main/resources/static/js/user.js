let user = {
    init() {
        // buttons
        let register = document.getElementById('register');
        let login = document.getElementById('login');
        let logout = document.getElementById('logout');
        let updatePassword = document.getElementById('updatePassword');

        let account = document.getElementById('account');
        let password = document.getElementById('password');
        let passwordConfirm = document.getElementById('passwordConfirm');
        
        if (register !== null) {
            register.addEventListener('click', function() {
                $.ajax({
                    url: '/api/user',
                    method: 'POST',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        account: account.value,
                        password: password.value
                    }),
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
        
        if (login !== null) {
            login.addEventListener('click', function() {
                $.ajax({
                    url: '/api/user/auth',
                    method: 'POST',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        account: account.value,
                        password: password.value
                    }),
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
        
        if (logout !== null) {
            logout.addEventListener('click', function() {
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

        if (updatePassword !== null) {
            updatePassword.addEventListener('click', function() {
                $.ajax({
                    url: '/api/user',
                    method: 'PUT',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        account: account.value,
                        password: password.value,
                        passwordConfirm: passwordConfirm.value
                    }),
                    dataType: 'json'
                    
                }).done(function(result) {
                    if (result === 'PASSWORD_UPDATE_SUCCESS') {
                        alert('비밀번호를 변경하였습니다.');
                        window.location.href = '/user/' + account.value;
                    } else if (result === 'PASSWORD_CONFIRM_NOT_MATCH') {
                        alert('비밀번호가 서로 다릅니다.');
                    }

                }).fail(function(error) {
                    alert(error);
                })
            })
        }
    }
}

user.init();