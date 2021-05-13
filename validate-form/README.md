# 유효성 검증 연습

## Bean Validation(Hibernate)
Bean Validation이라는 이름으로 Java에서 유효성(Validation) 데이터 검증을 위한 메타데이터로 사용하는 방법을 제시하고 있다.(Bean Validation 2.0 JSR-380까지 나와있음)
이것에 대한 구현체인 Hibernate Validator를 사용한 유효성 검증 연습을 해보는 프로젝트

## 어노테이션 정리
|어노테이션|설명|
|-|-|
|@Null|null인지 확인|
|@NotNull|null이 아닌지 확인|
|@NotEmpty|null, 공백이 아닌지 확인|
|@NotBlank|null, 공백, trim된 문자열 길이가 0이 아닌지 확인|
|@Email|이메일 형식인지 확인|
|@AssertTrue|참인지 검사|
|@AssertFalse|거짓인지 검사|
|@Size(min=, max=)|값이 min=과 max= 사이에 있는지 확인|
|@Future|미래 날짜인지 확인|
|@Past|과거날짜인지 확인|
|@FutureOrPresent|현재 또는 미래 날짜인지 확인|
|@PastOrPresent|과거 또는 현재인지 확인|
|@Positive|양수인지 확인|
|@Negative|음수인지 확인|
|@PositiveOrZero|양수이거나 0인지 확인|
|@NegativeOrZero|음수이거나 0인지 확인|
|@Max(value=)|지정된 최대값 이하인지 확인|
|@Min(value=)|지정된 최소값 이상인지 확인|
|@Pattern(regex=, flags=)|주어진 플래그 매치를 고려하여 값이 정규식 regex와 일치하는지 검사|
|@Digits(integer=, fraction=)|값이 integer와 fraction에 의해 지정된 자리수의 숫자인지 확인|
|@DecimalMax(value=, inclusive=)|inclusive가 true이면 value 이하인지 확인, false이면 value보다 미만인지 확인
|@DecimalMin(value=, inclusive=)|inclusive가 true이면 value 초과인지 확인, false일 때 value보다 이상인지 확인|
|@URL|URL형식인지 확인|