1. 주제 및 서비스 목표

YOGI 어플리케이션은 바쁜 현대인들이 보다 효율적으로 약속을 잡고 일정을 관리할 수 있도록 돕기 위해 설계되었다. 특히, 팀 프로젝트, 그룹 미팅, 개인 스케줄 등 다양한 상황에서 발생하는 일정 조율의 어려움을 해결하는 것을 목표로 하였다. 사용자는 간단한 조작만으로 일정을 공유하고, AI 기반 기능을 통해 최적의 약속 시간을 도출하며, 이를 통해 시간 낭비를 최소화할 수 있다. YOGI는 단순한 일정 관리 도구를 넘어 사용자들에게 편리하고 직관적인 경험을 제공하는 것을 지향한다.



2. 어플 기능

2.1 사용자 인증 기능

A)	로그인 및 회원가입
•	기능: 사용자가 ID와 비밀번호로 로그인하거나 새 계정을 생성할 수 있습니다.
•	화면:
    o	activity_login.xml – 로그인 화면
    o	activity_signup.xml – 회원가입 화면
•	설명: 로그인에 성공하면 그룹 목록 화면으로 이동하며, 향후 백엔드와의 연동을 통해 보안성을 강화할 예정입니다.

2.2 그룹 관리 기능

A)	그룹 목록 보기
•	기능: 사용자가 참여 중인 그룹 목록을 확인할 수 있습니다.
•	화면:
    o	activity_group.xml – 그룹 목록 화면
•	설명: 그룹 이름과 함께 ‘이름 변경’, ‘입장하기’ 버튼을 통해 그룹을 관리합니다.

B)	그룹 생성 및 참여
•	기능: 새로운 그룹을 생성하거나 이미 존재하는 그룹에 참여할 수 있습니다.
•	화면:
    o	popup_create_schedule.xml – 그룹 생성 팝업
    o	popup_join_group.xml – 그룹 참여 팝업
•	설명: 그룹을 생성하면 고유 코드를 발급받고, 해당 코드를 통해 다른 사용자가 그룹에 참여할 수 있습니다.

2.3 캘린더 및 일정 관리 기능

A) 캘린더 보기
•	기능: 그룹별 캘린더에서 일정을 확인할 수 있습니다.
•	화면:
    o	activity_calendar.xml – 그룹 캘린더 화면
•	설명: 캘린더에 약속 날짜와 시간을 색상으로 구분하여 표시합니다.

B) 약속 제안 및 장소 정하기
•	기능: 약속을 제안하고, 편리하게 최종 약속 시간을 정할 수 있습니다.
•	화면:
o	‘약속 제안하기’ 버튼 – 약속을 생성하는 팝업 호출
o	‘장소 정하기’ 버튼 – (향후 추가개발 진행예정)
•	설명: 사용자는 약속 이름, 날짜 범위, 투표 종료 시간을 설정할 수 있습니다

C) 일정 확정
•	기능: 투표를 통해 확정된 약속 시간을 캘린더에 표시합니다.
•	화면:
    o	popup_schedule_results.xml – 스케줄 투표 결과 팝업
    o	popup_second.xml – 확정 알림 팝업
•	설명: ‘완료하기’ 버튼을 누르면 확정된 일정이 캘린더에 표시됩니다.
2.4 타임테이블 기능

사용자별 가능한 스케줄 체크
•	기능: 사용자가 가능한 시간을 선택하여 팀원들과의 약속 시간을 조율합니다.
•	화면:
    o	activity_timetable.xml – 시간표 화면
•	설명: 체크된 시간대를 기반으로 최적의 약속 시간을 계산하고 결과를 표시합니다.



3. 추가 가능 기능

3.1 AI 활용 기능

• 일정 최적화: 사용자의 일정 패턴을 분석하여 겹치는 일정을 자동으로 재조정
• 생산성 분석: 일정 데이터를 기반으로 업무 효율성과 여유 시간 비율을 평가
• 음성을 통한 일정 추가: 사용자가 음성 명령으로 일정을 쉽게 추가할 수 있도록 자연어 처리 기술 적용

3.2 API 활용 기능

• Google Maps API: 사용자별 위치정보 기반 중간 지점 찾기 및 최적의 경로와 이동 시간을 추천
• OpenWeatherMap API: 일정 시간대의 날씨를 표시하여 야외 활동에 적합한 시간을 제안
• Zoom API: 화상회의 일정에 자동으로 Zoom 링크를 생성 및 추가
