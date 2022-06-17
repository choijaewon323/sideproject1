# SideProject1
--------------------------
REST API를 구현한 게시판입니다. 프론트엔드는 HTML, CSS, Javascript, JQuery, Bootstrap을 이용하여 만들었고, <br>
백엔드는 Spring Boot, H2database, Spring Data JPA를 사용하였습니다. 

## 구조
- controller
  - api
    - BoardApi.java
    - ReplyApi.java
    - UserApi.java
  - LoginController.java
  - MainController.java
  - UserController.java
- domain
  - board
    - Board.java
    - BoardRepository.java
  - common
    - SessionCommon.java
    - TimeEntity.java
  - reply
    - Reply.java
    - ReplyRepository.java
  - user
    - User.java
    - UserConfirm.java
    - UserRepository.java
- dto
  - board
    - BoardRequestDto.java
    - BoardResponseDto.java
  - reply
    - ReplyRequestDto.java
    - ReplyResponseDto.java
    - ReplyUpdateRequestDto.java
  - user
    - UserPasswordUpdateRequestDto.java
    - UserRequestDto.java
- service
  - BoardService.java
  - ReplyService.java
  - UserService.java

## 로그인
로그인 부분은 세션을 이용하여 구현했습니다. 
```
 HttpSession session = request.getSession(false);
```
getSession의 경우 해당하는 request로부터 session을 받아 올 때 session이 없다면 새로 생성합니다. 하지만 
