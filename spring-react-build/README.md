# 개발 환경
```
Java 1.8
Spring Boot 2.2.4
NodeJs 14.2.0
React 16.13.1
```
# 주요 스크립트
gradle에서 Node 빌드를 하기 위한 플러그인 "com.moowork.node"을 추가해주었다.
그리고 gradle 빌드 시 리액트 프로젝트 경로에서 "npm run bulid" 명령어가 실행되어 리액트 프로젝트가 빌드되도록 하고, 해당 빌드된 내용을 gradle 빌드에 포함시켰다.
```groovy
plugins {
    id "com.moowork.node" version "${mooworkNodeVersion}"
}

//리액트 프로젝트 경로
//$projectDir은 스프링 부트 root 경로
def webappDir = "$projectDir/frontend/my-app"

task appNpmInstall(type: NpmTask) {
    workingDir = file("${webappDir}")
    args = ["run", "build"]
}
task copyWebApp(type: Copy) {
    from 'frontend/my-app/build'
    into "build/resources/main/static"
}
copyWebApp.dependsOn(appNpmInstall)
compileJava.dependsOn(copyWebApp)
```
# 그래들(Gradle) 빌드
스프링 부트 루트 경로에서 아래 명령어를 실행해주게 되면 그래들 빌드가 되게 된다. 

    gradle clean build

# 빌드 파일 실행
빌드가 되면 스프링 부트 root디렉토리 build폴더와 함께 libs안에 빌드된 jar파일이 있는데 이것을 실행해주면 된다. 해당 경로로 이동해서 아래 명령어 실행

    java -jar springboot-react-example-0.0.1.jar
    
# 확인
아래 경로에 접속하게 되면 연동된 것을 확인할 수 있다.

    localhost:8080