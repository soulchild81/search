## search
 - 블로그 검색  , 인기검색어 조회 API 입니다.
 - jar 다운로드 경로 : https://drive.google.com/uc?export=download&id=1LoRNSL9YbJZpTAt82ZRd0yl267-jQcmM
 - 추가 구현 사항 
   1. API 테스트를 위한 swagger 추가 : http://localhost:8080/swagger-ui.html --> 스웨거 페이지를 통한 API 테스트
   2. 카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공 --> Exception 발생시 네이버 API 로 대체
   3. 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 구현 (예시. 키워드 별로 검색된 횟수의 정확도) --> select ~for update 로 레코드에 Lock을 걸고 데이터 조회후 업데이트
   4. 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현 --> /search/v1/popular/keyword API 상에 ehcache를 추가하여 데이터를 캐시에 저장 및 조회 하여 
                                                        DB의 부담을 최대한 줄이는 방향으로 처리 , 60초간의 expireTime 설정


## API 명세

####  - URI : /search/v1/blog
####  - HTTP METHOD : GET
####  - DESCRIPTION : 블로그 검색 API --> 쿼리를 파라미터로 하여 블로그를 검색 한다.
####  - Authentication : false

Request Param 
| Parameter | Required | Type | Description |
|---|:---:|---:|---:|
| `query` | O | String  | 요청 질의어 |
| `sort`  | X | String  | 정렬 파라미터 (accuracy : 정확도순, recency : 최신순) |
| `page`  | X | Integer | 페이지 번호 default 1 |
| `size`  | X | Integer | 한페이지에 보여질 문서 수 default 10 |

Response
| Name | Type | Description |
|---|:---:|---:|
| `return_code` |               String | 리턴 코드 (API 의 결과 코드 Constant.RESULT_CODE 내에 기술) |
| `message`     |               String | API 상태 메세지                                         |
| `message_type`|               String | API 상태 코드                                          |
| `data`        |                       | API 데이터 오브젝트|
| `data.pager`  |                       | 페이징 데이터 정보| 
| `data.list[]` |                       | 블로그 검색 리스트|
| `data.list[].blog_contents`  | String | 블로그 내용 축약 텍스트|
| `data.list[].blog_title`     | String | 블로그 제목|
| `data.list[].blog_url`       | String | 블로그 상세 URL |
| `data.list[].post_date`      | String | 블로그 작성일자 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]|


####  - URI : /search/v1/popular/keyword
####  - HTTP METHOD : GET
####  - DESCRIPTION : 인기검색어 TOP 10 조회
####  - Authentication : false

Request Param
<br>NONE

Response
| Name | Type | Description |
|---|:---:|---:|
| `return_code` |                  String | 리턴 코드 (API 의 결과 코드 Constant.RESULT_CODE 내에 기술)|
| `message`      |                 String | API 상태 메세지|
| `message_type` |                 String | API 상태 코드|
| `data`         |                        | API 데이터 오브젝트|
| `data.pager`   |                        | 페이징 데이터 정보 |
| `data.list[]`  |                        | 데이터 리스트 
| `data.list[].id` |                Integer|   해당 키워드 시퀀스|
| `data.list[].popular_keyword` |   String |   검색어 |
| `data.list[].count`            | Integer |  검색어가 조회된 건수 |
| `data.list[].update_time`      | TimeStamp| 검색어가 업데이트 된 시간 |






