<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
 
  <title>메인</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="resources/intro/img/favicon.png" rel="icon">
  <link href="resources/intro/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,500,600,700,700i|Montserrat:300,400,500,600,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="resources/intro/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="resources/intro/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="resources/intro/lib/animate/animate.min.css" rel="stylesheet">
  <link href="resources/intro/lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="resources/intro/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="resources/intro/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="resources/intro/css/style.css" rel="stylesheet">

   <!-- Bootstrap CSS -->
  <link href="resources/sidetopbar/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="resources/sidetopbar/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="resources/sidetopbar/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="resources/sidetopbar/css/font-awesome.min.css" rel="stylesheet" />
  <!-- full calendar css-->
  <link href="resources/sidetopbar/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
  <link href="resources/sidetopbar/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
  <!-- easy pie chart-->
  <link href="resources/sidetopbar/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen" />
  <!-- owl carousel -->
  <link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
  <link href="css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
  <!-- Custom styles -->
  <link rel="stylesheet" href="css/fullcalendar.css">
  <link href="resources/sidetopbar/css/widgets.css" rel="stylesheet">
  <link href="resources/sidetopbar/css/style.css" rel="stylesheet"> 
  <link href="resources/sidetopbar/css/style-responsive.css" rel="stylesheet" />
  <link href="resources/sidetopbar/css/xcharts.min.css" rel=" stylesheet">
  <link href="resources/sidetopbar/css/jquery-ui-1.10.4.min.css" rel="stylesheet"> 
  
  <link rel="stylesheet" type="text/css" href="resources/intro/css/util.css">
 
   
  <!-- =======================================================
    Theme Name: Rapid
    Theme URL: https://bootstrapmade.com/rapid-multipurpose-bootstrap-business-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
  
  
  
 
   <script> 
 
   function goMyPageForm(){
       alert($("[name=payForm]").serialize());

       $.ajax({ 
           url : "/posbis/payProc.do"
           ,type : "post"   
           ,data : $("[name=payForm]").serialize()
          , success : function(insertCard) {
             

				if(insertCard == 1){
					alert("카드가 정상적으로 등록되었습니다.\n 프리미엄회원으로 정상처리 되었습니다 \n 로그인을 다시 해주세요.");
					location.replace("/posbis/mainForm.do");
				}
				
				else {
					alert("서버 오류 발생. 관리자에게 문의 바람");
				} 
              
          }, error : function(){
             alert("서버 접속 실패");
            }
     });
   }      
   
      function goMainForm(){
         alert("결제가 취소 되었습니다.");
       	 location.replace("/posbis/mainForm.do");
      }
      
      
    //--------------------------------------------------------
	   //로고 클릭시
	     function goMainForm(){
	        //alert("메인으로 이동");
	        location.replace("/posbis/mainForm.do");
	     }
		
		//회사소개-pobis 클릭시
		
		function goIntroForm(){
	        //alert("회사소개로 이동");
	        location.replace("/posbis/introForm.do");
	     }
		
		//회사소개-pobis 클릭시
   		
   		function goIntroForm(){
   	        //alert("회사소개로 이동");
   	        location.replace("/posbis/introForm.do");
   	     }
	 
		//분석현황-검색관리 (프리미엄으로 이동 시일반 회원은 프리미엄 부분에 들어가지 못함)
   		function goPreSearchForm(){
   	        //alert("검색관리로 이동");
   			var rank_code = ${rank_code};
	         if(rank_code == 2){
	        	 location.replace("/posbis/preSearchForm.do");
	         }
	         else{
	        	 if(confirm("프리미엄 회원 등록을 위해 카드결제 화면으로 이동하시겠습니까?")==false) {
						return;
					}
	        	 else{
	        		 location.replace("/posbis/payForm.do");
	             }
	         }
   	        
   	     }
   		//분석현황-차트관리 (프리미엄으로 이동 시일반 회원은 프리미엄 부분에 들어가지 못함)
   		function goPreChartForm(){
   	        //alert("차트관리로 이동");
   			var rank_code = ${rank_code};
	         if(rank_code == 2){
	         	location.replace("/posbis/preChartForm.do");
	         }
	         else{
	        	 if(confirm("프리미엄 회원 등록을 위해 카드결제 화면으로 이동하시겠습니까?")==false) {
						return;
					}
	        	 else{
	        		 location.replace("/posbis/payForm.do");
	             }
	         }
   	     }
		 
		function goMessageForm(){
		    alert("건의사항이 접수 되었습니다. 감사합니다");
	 
		 }
   </script>

   </head>

 
<body>
     <!--==========================
  Header
  ============================-->
  <header id="header">

         <div id="topbar">
           <div class="container">
          
           </div>
         </div>

         <div class="container">

           <div class="logo float-left">
             <!-- Uncomment below if you prefer to use an image logo -->
             <h1 style="cursor:pointer"  class="text-light"><a  onClick="goMainForm();" class="scrollto"><span>POSBIS</span></a></h1>
             <!-- <a href="#header" class="scrollto"><img src="img/logo.png" alt="" class="img-fluid"></a> -->
           </div>

           <nav class="main-nav float-right d-none d-lg-block">
        <ul>
          <li style="cursor:pointer"  class="drop-down"><a href="">회사소개</a>
            <ul>
              <li style="cursor:pointer" onClick="goIntroForm();"><a href="#">POSBIS</a></li>
            </ul>
          </li>

        </ul>
      </nav><!-- .main-nav -->
           
         </div>
       </header><!-- #header -->

  <!--==========================
    Intro Section
  ============================-->
  <section id="intro" class="clearfix">
    <div class="container d-flex h-100">
      <div class="row justify-content-center align-self-center">
        <div class="col-md-6 intro-info order-md-first order-last">
          <h2>SIGN&nbsp;&nbsp;UP</h2>
 
 
        </div>
   
      </div>

    </div>
  </section> 

   

    <!--==========================
      	결제창
    ============================-->   
  <main id="main">
 
 
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
          <div class="col-lg-10"><br><br>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="index.html">회원가입</a></li>
              <li><i class="fa fa-user-md"></i>프리미엄 회원 결제</li>
             </ol>
          </div>
        </div>
 
      <div class="row">
      
          <div class="col-lg-8">
            <section class="panel">
              <header class="panel-heading">
                 <a href="">[결제창]</a>
              </header>
              <div class="panel-body"> 
                  <form name = "payForm" class="form-validate form-horizontal payForm" id="feedback_form"  >
 
                  <div class="form-group"> 
                    <label for="cname" class="control-label col-lg-2">카드종류 <span class="required">*</span></label>
                     <div class="col-lg-2">
                     <!--    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email"> -->
                        
                        <select name="credit"  size=1 class="form-control credit"  > 
                          <option value="" selected> --선택요망--</option>
		                  <option value="BC">BC카드</option>
		                  <option value="SHINHAN">신한카드</option>
		                  <option value="KB">국민카드</option>
		                  <option value="SAMSUNG">삼성카드</option>
		                  <option value="HYUNDAI">현대카드</option>
		                  <option value="LOTTE">롯데카드</option>
		                  <option value="HANA_SK">하나카드</option>
		                  <option value="NH">농협카드</option>
		                  <option value="CITY">씨티카드</option>
		                  <option value="KakaoBank">카카오뱅크카드</option>
		                  <option value="K bank">케이뱅크카드</option>
		                  <option value="JEONBUK">전북은행카드</option>
		                  <option value="COMMUNITY_CREDIT_COOPERATIVES">새마을금고카드</option>
		                  <option value="ETC">기타(은행/증권)카드</option>
 
 
                      </select>
                      
                      
                      </div>
              
                  </div>
                   
                  <div class="form-group">
                    <label for="cname" class="control-label col-lg-2">카드번호 <span class="required">*</span></label>
                    <div class="col-lg-2">
                        <input type="text"  name="creditNum1" size=4 maxlength=4 class="form-control" placeholder="4자리"   >
 
                      </div>
                       <div class="col-lg-2">
                        <input type="text"  name="creditNum2" size=4 maxlength=4 class="form-control creditNum2" placeholder="4자리"     >
 
                      </div> 
                       <div class="col-lg-2">
                        <input type="text"  name="creditNum3" size=4 maxlength=4 class="form-control creditNum3"  placeholder="4자리"    >
 
                      </div> 
                      <div class="col-lg-2">
                        <input type="password"  name="creditNum4" size=4 maxlength=4 class="form-control creditNum4" placeholder="4자리"    >
 
                      </div> 
                  </div>
                  
                   <div class="form-group">
                    <label for="cname" class="control-label col-lg-2">유효기간 <span class="required">*</span></label>
                    <div class="col-lg-2">
                        <input type="text" name="ex_month" size=2 maxlength=2  placeholder="mm" class="form-control exdate1"  >
                      </div> 
                      <div class="col-lg-2">
                        <input type="text" name="ex_year" size=2 maxlength=2  placeholder="yy" class="form-control exdate2"  >
                      </div> 
 
                  </div>
                  
                  <div class="form-group">
                    <label for="cname" class="control-label col-lg-2">주민번호 <span class="required">*</span></label>
          		
          		 <div class="col-lg-2">
          		<input type="text" name="jumin_no" size=5 maxlength=6  class="form-control creditpwd" >
          		</div>&nbsp;-&nbsp;*******
          			</div>
          			<br>
                      <div class="form-group">
                    <label for="cname" class="control-label col-lg-2">비밀번호 <span class="required">*</span></label>
                    <div class="col-lg-2">
                        <input type="password" name="credit_pwd"   placeholder="앞 2자리" class="form-control creditpwd"  size=3 maxlength=2 >
                      </div>** 
 
 
                  </div>
   
        		<div style="float:right">
                <button class="btn btn-success" type="button" value="등록" onClick="goMyPageForm();">등록 </button>
                <button class="btn btn-danger" type="button" value="취소" onClick="goMainForm()">취소 </button>
                </div>
                </form>


              </div>
            </section>
          </div>
    </div>
 </section>
</section>
<!--==========================
    꼬리말
  ============================-->
  <footer id="footer" class="section-bg">
    <div class="footer-top">
      <div class="container">

        <div class="row">

          <div class="col-lg-6">

            <div class="row">

                <div class="col-sm-6">

                  <div class="footer-info">
                    <h3>POSBIS</h3>
                    <p>Cras fermentum odio eu feugiat lide par naso tierra. Justo eget nada terra videa magna derita valies darta donna mare fermentum iaculis eu non diam phasellus. Scelerisque felis imperdiet proin fermentum leo. Amet volutpat consequat mauris nunc congue.</p>
                  </div>

                  <!-- <div class="footer-newsletter">
                    <h4>Our Newsletter</h4>
                    <p>Tamen quem nulla quae legam multos aute sint culpa legam noster magna veniam enim veniam illum dolore legam minim quorum culpa amet magna export quem.</p>
                    <form action="" method="post">
                      <input type="email" name="email"><input type="submit"  value="Subscribe">
                    </form>
                  </div> -->

                </div>

                <div class="col-sm-6">
                  <div class="footer-links">
                    <h4>빠른 이동</h4>
                    <ul>
                      <li><a onClick="goIntroForm();">회사소개 </a></li>
                      <li><a onClick="goMainForm();">로그인</a></li>
                      <li><a onClick="goqstnRegForm();">질문하기</a></li>
                    </ul>
                  </div>

                  <div class="footer-links">
                    <h4>연락망</h4>
                    <p>
                      월드메르디앙벤쳐 2차 <br>
                      Korea, Seoul 가산디지털단지역<br>
                      용기의 방, 409호 <br>
                      <strong>Phone:</strong> +1 5589 55488 55<br>
                      <strong>Email:</strong> info@example.com<br>
                    </p>
                  </div>

                  <div class="social-links">
                    <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
                    <a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
                    <a href="#" class="instagram"><i class="fa fa-instagram"></i></a>
                    <a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
                  </div>

                </div>

            </div>

          </div>

          <div class="col-lg-6">

            <div class="form">
              
              <h4>건의 사항</h4>
              <p>POSBIS는 고객의 말에 늘 귀기울이고 있습니다. <br>불편한 점 또는 좋은 제안이 있으시다면 언제든지 건의해 주세요. </p>
              <form action="" method="post" role="form" class="contactForm">
                <div class="form-group">
                  <input type="text" class="form-control" name="name" id="name" placeholder="성함" data-rule="minlen:2" data-msg="2자 이상 입력해 주십시오" />
                  <div class="validation"></div>
                </div>
                <div class="form-group">
                  <input type="email" class="form-control" name="email" id="email" placeholder="이메일" data-rule="email" data-msg="이메일을 입력해 주십시오" />
                  <div class="validation"></div>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="subject" id="subject" placeholder="제목" data-rule="minlen:4" data-msg="제목을 4자 이상 입력해 주십시오" />
                  <div class="validation"></div>
                </div>
                <div class="form-group">
                  <textarea class="form-control" name="message" rows="5" data-rule="required"  placeholder="내용" data-msg="내용을 입력해 주십시오"></textarea>
                  <div class="validation"></div>
                </div>

               <!--  <div id="sendmessage">건의사항이 무사히 전달됐습니다. 감사합니다!</div>
                <div id="errormessage"></div> -->

                <div class="text-center"><button type="submit" title="Send Message" onClick="goMessageForm();">전송</button></div>
              </form>
            </div>

          </div>

          

        </div>

      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong>POSBIS</strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Rapid
        -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>

 
  </footer><!-- #footer -->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
  <!-- Uncomment below i you want to use a preloader -->
  <!-- <div id="preloader"></div> -->

  <!-- JavaScript Libraries -->
  <script src="resources/intro/lib/jquery/jquery.min.js"></script>
  <script src="resources/intro/lib/jquery/jquery-migrate.min.js"></script>
  <script src="resources/intro/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="resources/intro/lib/easing/easing.min.js"></script>
  <script src="resources/intro/lib/mobile-nav/mobile-nav.js"></script>
  <script src="resources/intro/lib/wow/wow.min.js"></script>
  <script src="resources/intro/lib/waypoints/waypoints.min.js"></script>
  <script src="resources/intro/lib/counterup/counterup.min.js"></script>
  <script src="resources/intro/lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="resources/intro/lib/isotope/isotope.pkgd.min.js"></script>
  <script src="resources/intro/lib/lightbox/js/lightbox.min.js"></script>
  <!-- Contact Form JavaScript File -->
  <script src="resources/intro/contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="resources/intro/js/main.js"></script>
  
  

</body>
</html> 