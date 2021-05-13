# RequestBody, ModelAttribute를 테스트해보는 예제

## @ModelAttribute

HTTP 클라이언트가 보내온 요청에서 Parameter 값들을 VO의 Setter를 통해 바인딩 해준다.

## @RequestBody

HTTP 클라이언트가 보내온 요청에서 Body 값들을 ObjectMapper를 통해 변환해서 VO에 값들을 매핑해준다.

## 어떻게 구분해서 사용할까?

값을 파라미터로 넘기냐, Body에 담아서 넘기냐에 따라서 선택하면 될 것 같다.