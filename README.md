# SideProject1
--------------------------
REST API를 구현한 게시판입니다. 프론트엔드는 HTML, CSS, Javascript, JQuery, Bootstrap을 이용하여 만들었고, <br>
백엔드는 Spring Boot, MariaDB, H2database, Spring Data JPA를 사용하였습니다. 

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
