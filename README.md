# REST API 방식으로 Google, KaKao 소셜 로그인 구현
* 구글, 카카오 공식 문서를 참고하였음
* 자세한 설명은 https://mjkim.tistory.com/243 블로그 참고
* application.yml 파일에 정보를 입력한 뒤, 스프링 프로젝트를 실행하고 http://localhost:8080 입력
* Google, KaKao 서버에서 유저 인증 완료 이후, 유저의 정보를 jwt로 담아서 클라이언트로 json 형식으로 보낸다.