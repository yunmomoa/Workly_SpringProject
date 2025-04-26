# 그룹웨어 WORKLY 프로젝트

<br> 

📈  **WORKLY**는 기업 내 효율적인 업무 환경을 위한 통합 그룹웨어 솔루션입니다.  
캘린더, 전자결재, 인사/연차 관리, 내부 채팅 등 다양한 기능을 제공하여 협업 효율을 높입니다.

> 사용자 친화적인 UI와 접근제어 및 보안 관리로 실시간 협업 환경을 지원합니다.

<br>

## 📌 프로젝트 개요

<h3>📅 프로젝트 기간</h3>

  - 2025.01.29 ~ 2025.03.07

<br>

<h3>📝 프로젝트 목표</h3>

  - SpringBoot 및 React를 활용한 풀스택 개발
  - SpringSecurity, JWT를 활용한 인증 및 보안 처리, 실시간 협업 환경 구성
  - AWS EC2 배포 경험 축적

<br>

<h3>👨‍💻 담당 역할</h3>

  - **프로젝트 팀장**
    - 형상관리
    - 프로젝트 일정 관리
      
  - **로그인/인증/보안 처리**
    - JWT 기반 인증 및 인가
    - 로그인 실패 시 임시 비밀번호 메일 발송
    - URL별 권한 설정
      
  - **인사관리 및 연차관리**
    - 사원 CRUD, 연차 CRUD
      
  - **마이페이지 기능**
    - 내 정보 수정, 비밀번호 변경
      
  - **메인화면 기능**
    - 실시간 날씨 조회(OpenWeather API), 출퇴근 기록
      
  - **배포**
    - WEB Server(Nginx) / WAS(Apache Tomcat) 분리 구성


<br>

## 프로젝트 소감

이번 프로젝트에서는 React와 Spring Boot를 활용하여 프론트엔드와 백엔드를 분리한 환경에서 개발을 진행하여, 실무에 가까운 개발 프로세스를 직접 경험할 수 있었습니다. 처음 접하는 두 프레임워크라 초반에는 많은 어려움을 겪었지만 점차 익숙해지면서 Redux, Spring Security 같은 고급 기능들을 적용해보며 기술적으로 많은 성장을 느꼈습니다. 특히 프론트와 백의 역할이 분리된 구조를 통해 자연스럽게 API 설계와 통신 방식에 대한 이해를 높일 수 있었고, 소프트웨어의 특성과 목표에 맞는 아키텍처를 고민하고 선택하는 과정 자체가 매우 의미 있는 경험이었습니다.

이전 프로젝트에서는 Github를 활용하지 못해 직접 소스코드를 병합했고, 그로 인해 많은 디버깅 시간이 필요했습니다. 하지만 이번 프로젝트에서는 형상관리를 주도적으로 맡아 주요 기능 병합 작업을 직접 담당하여 충돌을 최소화하고 안정적인 통합을 진행했습니다. 이를 통해 GitHub를 활용한 협업의 효율성과 형상관리의 중요성을 몸소 느낄 수 있었습니다.

또한 MyBatis 사용 중 과도한 JOIN으로 인한 성능 저하를 경험하여, 객체지향적 설계와 관계형 데이터베이스 간의 구조적 한계를 느꼈고, 이를 계기로 JPA와 같은 ORM 기술에도 관심을 갖게 되었습니다.

이번 프로젝트는 단순한 기능 구현을 넘어 기술 선택과 협업 방식, 아키텍처 구성 등 개발자가 마주하게 되는 다양한 요소들을 경험해볼 수 있었던 소중한 시간이었습니다. 앞으로도 이 경험을 바탕으로 부족한 부분을 보완하고 협업에 강한 백엔드 개발자로 성장하고 싶습니다.


<h2>구조도</h2>

![image](https://github.com/user-attachments/assets/4f3503fb-7b06-4598-8fcf-e1b438985a88)

<h2>USECASE</h2>

![workly_USECASE](https://github.com/user-attachments/assets/4ad85dcf-4cca-4f07-9a4c-04a23baafb6c)


<h2>ERD</h2>

<img width="1450" alt="workly_ERD" src="https://github.com/user-attachments/assets/e6a947fc-b695-4fc7-a5b4-f10add9ef539" />


<h2>시스템 아키텍쳐</h2>

![image](https://github.com/user-attachments/assets/664fcbac-5a98-44c2-a6cb-b55a49bafbfa)

<h2>개발 환경</h2>

![image](https://github.com/user-attachments/assets/12fb34ef-29f2-4648-8478-f59fa628e0b5)

<h2>작업 일정</h2>

![image](https://github.com/user-attachments/assets/34429ea3-3735-420a-9c30-c03d9b14ee02)

