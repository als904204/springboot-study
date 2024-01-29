<template>
  <footer class="footer">
    <div class="footer_leftCover">
        <p>웹덕로고</p>
        <div class="footer_contentInfo">
          <div class="footer_title">Contact</div>
          <div class="footer_mail">min.uuu1117@gmail.com</div>
        </div>
        <div class="footer_copyright">
          Copyright NAVER WEBTOON, KAKAO WEBTOON
        </div>
    </div>

    <div class="footer_rightCover">
      <a href="mailto:min.uuu1117@gmail.com" class="pi pi-envelope" target="_blank"></a>
      <a href="https://github.com/als904204" class="pi pi-github" target="_blank"></a>
    </div>


  </footer>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<style>
.footer {
  display: flex;
  color: black;
  background-color: #f9f9f9; /* 푸터 배경색 */
  margin-top:48px;
  height: 180px;
  width: 100%;
  bottom: 0;
  justify-content:center;
}

.footer_leftCover{
  width:30%;
  flex-direction: column;
  display: flex;
  justify-content: center; /*  상하 중앙 정렬 */
  align-items: flex-start; /*  항목들을 왼쪽 정렬*/
}

.footer_contentInfo{
  flex-direction: column;
  width:30%;
  display:flex;
  align-items: flex-start; /* 항목들을 왼쪽 정렬 */
}

.footer_mail, .footer_title {
  font-weight: 400;
  font-size: 13px;
  color: #777;
}

.footer_copyright {
  font-weight: 400;
  font-size: 13px;
  color: #777;
  margin-top: 10px; /* 상단 여백을 추가 */
}

.footer_rightCover{
  display: flex;
  grid-gap: 50px;
  gap: 50px;
  color: #2f2f2f;
  font-weight: 500;
  font-size: 14px;
  line-height: 17px;
  align-items: center;
  margin-top: 20px;
}

.footer_rightCover a {
  color: inherit; /* 링크 색상을 부모 요소와 동일하게 설정 */
  text-decoration: none; /* 밑줄 제거 */
}

.footer_rightCover a:hover {
  text-decoration: none; /* 호버 시 밑줄 제거 */
}

</style>