package com.naver.erp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PreChartController {

	@Autowired
	private PreChartService preChartService;

	// -------------------------------------------------------------------------------
	// 가상주소 /posbis/preChartForm.do 로 접근하면 호출되는 메소드 선언.
	// -------------------------------------------------------------------------------
	@RequestMapping(value = "/preChartForm.do")
	public ModelAndView goPreChartForm(
			HttpSession session
			, HttpServletResponse response
			) {
		// ModelAndView 객체 생성하고 ModelAndView 객체에 호출 JSP 페이지명을 저장하기.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("preChartForm.jsp");

		try {
			
			String user_id = (String)session.getAttribute("user_id");

// 사업자번호 (가게명) 얻기.			
//=========================================================================================================
			//String user_id = "master44";
			System.out.println("user_no 얻기 시작");
			System.out.println(user_id);

			// user_id 를 가지고 u_no 값 얻기
			int user_no = this.preChartService.getUserNo(user_id);
			System.out.println("user_no : " + user_no);

			// u_no 값 가지고 business_no, business_name 값 얻기 (N행 N열이라 List<Map<String,String>>
			// . N행 1열이면 List<String> )
			List<Map<String, String>> businessNoList = this.preChartService.getBusinessNoList(user_no);
//			List<String> businessNoList = this.preChartService.getBusinessNoList(user_no);

			System.out.println("businessNoList 끝");

			// ModelAndView 객체에 검색 개수, 게시판 검색 목록 저장하기
			// ModelAndView 객체에 저장된 DB 연동 결과물은 JSP 페이지에서 EL 문법으로 꺼낼 수 있다. ${저장키값명}
			// JSP 페이지에서 사용하기 위해 addObject를 사용하여 ModelAndView 객체에 저장.
			mav.addObject("businessNoList", businessNoList);

			// mav.addObject("boardListAllCnt" , boardListAllCnt);
			System.out.println(businessNoList);
			System.out.println("businessNoList.size()=>" + businessNoList.size());
			//System.out.println("businessNoList.get(\"business_no\")=>" + businessNoList.get(0).get("business_no"));

			String all = "all";
			mav.addObject("all",all);
			
			
//==================================================================================================================================

		} catch (Exception e) {
			// try 구문에서 예외가 발생하면 실행할 구문 설정
			System.out.println(e.getMessage());
			System.out.println("preChartForm <에러발생>");
		}

		return mav;

	}
	
	
	
		// -------------------------------------------------------------------------------
		// /preChartAllProc.do 로 접근하면 호출되는 메소드 선언.	사업자번호 선택 란이 모두 검색 일 때.
		// -------------------------------------------------------------------------------

		@RequestMapping(value = "/preChartAllProc.do" // 접속하는 클라이언트의 URL주소 설정
		// , method=RequestMethod.POST // 접속하는 클라이언트의 파라미터값 전송.
				, produces = "application/json;charset=UTF-8" // 응답할 데이터 종류는 json으로 설정.
		)
		@ResponseBody
		public AllBusinessNoSalesChartListDTO preChartAllProc(
				AllBusinessNoDTO allBusinessNoDTO
				, AllBusinessNoSalesChartListDTO allBusinessNoSalesChartListDTO

		) {

			
			System.out.println("============================preChartAllProc========================");
			System.out.println(allBusinessNoDTO);
			for(int i=0; i<allBusinessNoDTO.getAllBusinessNo().length; i++) {
			System.out.println("business_no ===> " + allBusinessNoDTO.getAllBusinessNo()[i]);
			}
			
			try {
				System.out.println(allBusinessNoDTO.getAllBusinessNo().length);
				// 사업자번호 갯수만큼 반복문을 돌려서 각 가게 마다의 매출 현황 뽑기.
//				for(int i=0; i<allBusinessNoDTO.getAllBusinessNo().length; i++) {
//					
//					List<Map<String, String>> salesMonthList = this.preChartService.getSalesMonthList(allBusinessNoDTO.getAllBusinessNo()[i]);
//					// 2019-12-31 리스트는 뽑아오는데 이 값들을 어디에 저장할 지 생각해야함.
//					System.out.println("salesMonthList ===> " + salesMonthList);
//				
//					allBusinessNoSalesChartListDTO.setAllBusinessNoSalesMonthList(salesMonthList);		
//					
//				}
				
				List<Map<String, String>> allBusinessNoSalesMonthList = this.preChartService.getAllBusinessNoSalesMonthList(allBusinessNoDTO);

				allBusinessNoSalesChartListDTO.setAllBusinessNoSalesMonthList(allBusinessNoSalesMonthList);
				
				for (int i = 0; i < allBusinessNoSalesMonthList.size(); i++) {
					System.out.println("allBusinessNoSalesMonthList.get(\"allBusinessNoSalesMonthList\")=>" + allBusinessNoSalesMonthList.get(i));
				}
			} catch (Exception e) {
				// try 구문에서 예외가 발생하면 실행할 구문 설정
				System.out.println("e.getMessage()" + e.getMessage());
				System.out.println("preChartProc <에러발생>");
			}


			return allBusinessNoSalesChartListDTO;

		}	
	
	
	
	
	
	

//********************************************************************************************************	
// DTO에 [나의 가게 월별 매출] 과 [같은 동네, 같은 업종의 가게들 평균 월별 매출] 을 담아 리턴하기.
//********************************************************************************************************
	// -------------------------------------------------------------------------------
	// /preChartProc.do 로 접근하면 호출되는 메소드 선언.
	// -------------------------------------------------------------------------------
	@RequestMapping(value = "/preChartProc.do" // 접속하는 클라이언트의 URL주소 설정
	// , method=RequestMethod.POST // 접속하는 클라이언트의 파라미터값 전송.
			, produces = "application/json;charset=UTF-8" // 응답할 데이터 종류는 json으로 설정.
	)
	@ResponseBody
	public PreChartListDTO preChartProc(

			// 파라미터 값이 저장되는 [BoardSearchDTO 객체]를 매개변수로 선언
			// 1) [파라미터명]과 [BoardSearchDTO 객체]의 [속성변수명]이 같으면 satter 메소드가 작동되어 [파라미터값]이
			// [속성변수]에 저장된다. (Spring 프레임워크가 알아서 해주는 것.)
			// 2) [속성변수명]에 대응하는 [파라미터명]이 없으면 satter 메소드가 작동되지 않는다.
			// 3) [속성변수명]에 대응하는 [파라미터명]이 있는데 [파라미터값]이 없으면 [속성변수]의 자료형에 관계없이 무조건 null 값이
			// 저장된다.
			// - 자료형이 int 와 같은 기본형일 경우 속성변수 값으로 null 이 들어가게 되므로 에러가 발생한다.
			// - 이러한 에러를 피하려면 파라미터값이 기본형이거나 속성면수의 자료형을 String으로 해야한다.
			// - 에러가 발생하면 메소드 안의 실행구문은 하나도 실행되지 않는다. (예외처리도 안됨.)
			// preChartListDTO preChartListDTO

			// ,@RequestParam(value="user_id") String user_id

			@RequestParam(value = "changeBusinessNo") String changeBusinessNo
			, PreChartListDTO preChartListDTO

	) {
		System.out.println("preChartProc 시작1");

		System.out.println("business_no ===> " + changeBusinessNo);
		try {

// 나의 가게의 월매출 현황
//==================================================================================================================			
			System.out.println("salesMonthList 시작1");
			List<Map<String, String>> salesMonthList = this.preChartService.getSalesMonthList(changeBusinessNo);
			// List<String> salesMonthList =
			// this.preChartService.getSalesMonthList(changeBusinessNo);
//			sml.add((Map<String, String>) salesMonthList);

			System.out.println("salesMonthList.size()=>" + salesMonthList.size());

			for (int i = 0; i < 12; i++) {
				System.out.println("salesMonthList.get(\"salesMonthList\")=>" + salesMonthList.get(i));
			}

//==================================================================================================================	

// 나와 같은 동네, 같은 업종의 가게들의 평균 월매출 현황
//==================================================================================================================			

			List<Map<String, String>> allSalesMonthList = this.preChartService.getAllSalesMonthList(changeBusinessNo);

			System.out.println("allSalesMonthList.size()=>" + allSalesMonthList.size());

			for (int i = 0; i < 12; i++) {
				System.out.println("allSalesMonthList.get(\"allSalesMonthList\")=>" + allSalesMonthList.get(i));
			}

//			
//			System.out.println( "sml.size()=>" + sml.size());
//			System.out.println( "sml.get(\"business_no\")=>" + sml.get(0) );
//			
//==================================================================================================================	
			preChartListDTO.setSalesMonthList(salesMonthList);
			preChartListDTO.setAllSalesMonthList(allSalesMonthList);

		} catch (Exception e) {
			// try 구문에서 예외가 발생하면 실행할 구문 설정
			System.out.println("e.getMessage()" + e.getMessage());
			System.out.println("preChartProc <에러발생>");
		}

		System.out.println("DTO 리턴한다~~~~");
		return preChartListDTO;

	}

// 리스트로 얻어올 데이터 목록 하나씩 받아오기
//	//-------------------------------------------------------------------------------
//	// /preChartProc.do 로 접근하면 호출되는 메소드 선언.
//	//-------------------------------------------------------------------------------
//	@RequestMapping( 
//			value="/preChartProc.do"						// 접속하는 클라이언트의 URL주소 설정
//		//	, method=RequestMethod.POST						// 접속하는 클라이언트의 파라미터값 전송.
//			, produces="application/json;charset=UTF-8"		// 응답할 데이터 종류는 json으로 설정.
//	)
//	@ResponseBody				
//	public List<Map<String,String>> preChartProc(
//
//			// 파라미터 값이 저장되는 [BoardSearchDTO 객체]를 매개변수로 선언
//				// 1) [파라미터명]과 [BoardSearchDTO 객체]의 [속성변수명]이 같으면 satter 메소드가 작동되어 [파라미터값]이 [속성변수]에 저장된다. (Spring 프레임워크가 알아서 해주는 것.)
//				// 2) [속성변수명]에 대응하는 [파라미터명]이 없으면 satter 메소드가 작동되지 않는다.
//				// 3) [속성변수명]에 대응하는 [파라미터명]이 있는데 [파라미터값]이 없으면 [속성변수]의 자료형에 관계없이 무조건 null 값이 저장된다.
//				//		- 자료형이 int 와 같은 기본형일 경우 속성변수 값으로 null 이 들어가게 되므로 에러가 발생한다.
//				//		- 이러한 에러를 피하려면 파라미터값이 기본형이거나 속성면수의 자료형을 String으로 해야한다.
//				//		- 에러가 발생하면 메소드 안의 실행구문은 하나도 실행되지 않는다. (예외처리도 안됨.)
//			//preChartListDTO preChartListDTO
//			
//			//,@RequestParam(value="user_id") String user_id
//			@RequestParam(value="changeBusinessNo") String changeBusinessNo
//
//			
//	) {		
//		System.out.println("preChartProc 시작1");
//		List<Map<String,String>> salesMonthList = null;
//
//		System.out.println("business_no ===> "+changeBusinessNo);
//		try {
//			
//// 나의 가게의 월매출 현황
////==================================================================================================================			
//			System.out.println("salesMonthList 시작1");
//			salesMonthList = this.preChartService.getSalesMonthList(changeBusinessNo);
//			//List<String> salesMonthList = this.preChartService.getSalesMonthList(changeBusinessNo);
////			sml.add((Map<String, String>) salesMonthList);
//		
//			System.out.println( "salesMonthList.size()=>" + salesMonthList.size());
//			
//			for(int i=0; i<12; i++) {
//				System.out.println( "salesMonthList.get(\"salesMonthList\")=>" + salesMonthList.get(i) );
//			}
//			
//
////==================================================================================================================	
//			
//			
//// 나와 같은 동네, 같은 업종의 가게들의 평균 월매출 현황
////==================================================================================================================			
//			
//			List<Map<String,String>> allSalesMonthList = this.preChartService.getAllSalesMonthList(changeBusinessNo);
//			
//			
//			System.out.println( "allSalesMonthList.size()=>" + allSalesMonthList.size());
//			
//			for(int i=0; i<12; i++) {
//				System.out.println( "allSalesMonthList.get(\"allSalesMonthList\")=>" + allSalesMonthList.get(i) );
//			}
//
////			
////			System.out.println( "sml.size()=>" + sml.size());
////			System.out.println( "sml.get(\"business_no\")=>" + sml.get(0) );
////			
////==================================================================================================================			
//						
//		}catch(Exception e) {
//			//try 구문에서 예외가 발생하면 실행할 구문 설정
//			System.out.println("e.getMessage()"+e.getMessage());
//			System.out.println("preChartProc <에러발생>");
//		}
//		
//		
//		
//		System.out.println("리턴한다~~~~");
//		return salesMonthList;
//		
//		
//	}
//		
//	
//	
//	
//
	// -------------------------------------------------------------------------------
	// /preChartProc2.do 로 접근하면 호출되는 메소드 선언.
	// -------------------------------------------------------------------------------
	@RequestMapping(value = "/preChartProc2.do" // 접속하는 클라이언트의 URL주소 설정
	// , method=RequestMethod.POST // 접속하는 클라이언트의 파라미터값 전송.
			, produces = "application/json;charset=UTF-8" // 응답할 데이터 종류는 json으로 설정.
	)
	@ResponseBody
	public MyPopularityListDTO preChartProc2(

			// 파라미터 값이 저장되는 [BoardSearchDTO 객체]를 매개변수로 선언
			// 1) [파라미터명]과 [BoardSearchDTO 객체]의 [속성변수명]이 같으면 satter 메소드가 작동되어 [파라미터값]이
			// [속성변수]에 저장된다. (Spring 프레임워크가 알아서 해주는 것.)
			// 2) [속성변수명]에 대응하는 [파라미터명]이 없으면 satter 메소드가 작동되지 않는다.
			// 3) [속성변수명]에 대응하는 [파라미터명]이 있는데 [파라미터값]이 없으면 [속성변수]의 자료형에 관계없이 무조건 null 값이
			// 저장된다.
			// - 자료형이 int 와 같은 기본형일 경우 속성변수 값으로 null 이 들어가게 되므로 에러가 발생한다.
			// - 이러한 에러를 피하려면 파라미터값이 기본형이거나 속성면수의 자료형을 String으로 해야한다.
			// - 에러가 발생하면 메소드 안의 실행구문은 하나도 실행되지 않는다. (예외처리도 안됨.)
			// preChartListDTO preChartListDTO

			// ,@RequestParam(value="user_id") String user_id
			@RequestParam(value = "changeBusinessNo") String changeBusinessNo
			, MyPopularityListDTO myPopularityListDTO

	) {
		System.out.println("preChartProc2 시작1");
		List<Map<String,String>> myPopularityMenu = new ArrayList<Map<String,String>>();
		
		List<Map<String,String>> othersPopularityMenu = new ArrayList<Map<String,String>>();
		

//		List<String> myPopularityMenu = new ArrayList<String>();
//		List<String> othersPopularityMenu = new ArrayList<String>();
		
		System.out.println("business_no ===> " + changeBusinessNo);

		try {
			
// 나의 가게 인기메뉴 구하기.
//==================================================================================================================			
			myPopularityMenu = this.preChartService.getMyPopularityMenu(changeBusinessNo);
			
			System.out.println("myPopularityMenu ===> " + myPopularityMenu.size());
			for (int i = 0; i < myPopularityMenu.size(); i++) {
				System.out.println("myPopularityMenu.get(\"myPopularityMenu\")=>" + myPopularityMenu.get(i));
			}

			myPopularityListDTO.setMyPopularityMenu(myPopularityMenu);
			
// 다른 가게 인기메뉴 구하기.
//==================================================================================================================			
			othersPopularityMenu = this.preChartService.getOthersPopularityMenu(changeBusinessNo);

			System.out.println("othersPopularityMenu ===> " + othersPopularityMenu.size());
			for (int i = 0; i < othersPopularityMenu.size(); i++) {
				System.out.println("othersPopularityMenu.get(\"othersPopularityMenu\")=>" + othersPopularityMenu.get(i));
			}

			myPopularityListDTO.setOthersPopularityMenu(othersPopularityMenu);
			
			
// 같은 동네, 같은 업종 점포수 구하기.
//==================================================================================================================			
			String storeCount = this.preChartService.getStoreCount(changeBusinessNo);
			
			System.out.println("storeCount ===> " + storeCount);
			
			myPopularityListDTO.setStoreCount(storeCount);
			
				
			
			

		} catch (Exception e) {
			// try 구문에서 예외가 발생하면 실행할 구문 설정
			System.out.println("e.getMessage()" + e.getMessage());
			System.out.println("preChartProc2 <에러발생>");
		}

		System.out.println("myPopularityListDTO 리턴한다~~~~");
		return myPopularityListDTO;

	}
	
	
	
	
	// -------------------------------------------------------------------------------
	// /preChartProc3.do 로 접근하면 호출되는 메소드 선언.
	// -------------------------------------------------------------------------------
	@RequestMapping(value = "/preChartProc3.do" // 접속하는 클라이언트의 URL주소 설정
	// , method=RequestMethod.POST // 접속하는 클라이언트의 파라미터값 전송.
			, produces = "application/json;charset=UTF-8" // 응답할 데이터 종류는 json으로 설정.
	)
	@ResponseBody
	public MySalesRatioDTO preChartProc3(

			@RequestParam(value = "changeBusinessNo") String changeBusinessNo
			, MySalesRatioDTO mySalesRatioDTO

	) {
		System.out.println("preChartProc3 시작1");
		List<Map<String,String>> menuSalesCount = new ArrayList<Map<String,String>>();
		List<Map<String,String>> salesBenefit = new ArrayList<Map<String,String>>();
		
		System.out.println("business_no ===> " + changeBusinessNo);

		try {
			
// 상품별 판매 개수 구하기
//==================================================================================================================			
			menuSalesCount = this.preChartService.getMenuSalesCount(changeBusinessNo);
			
			System.out.println("menuSalesCount ===> " + menuSalesCount.size());
			for (int i = 0; i < menuSalesCount.size(); i++) {
				System.out.println("menuSalesCount.get(\"menuSalesCount\")=>" + menuSalesCount.get(i));
			}

			mySalesRatioDTO.setMenuSalesCount(menuSalesCount);
			
			
			
// 상품별 순이익 구하기
//==================================================================================================================			
			salesBenefit = this.preChartService.getSalesBenefit(changeBusinessNo);
			
			System.out.println("salesBenefit ===> " + salesBenefit.size());
			for (int i = 0; i < salesBenefit.size(); i++) {
				System.out.println("salesBenefit.get(\"salesBenefit\")=>" + salesBenefit.get(i));
			}

			mySalesRatioDTO.setSalesBenefit(salesBenefit);			
				
			
			

		} catch (Exception e) {
			// try 구문에서 예외가 발생하면 실행할 구문 설정
			System.out.println("e.getMessage()" + e.getMessage());
			System.out.println("preChartProc2 <에러발생>");
		}

		System.out.println("myPopularityListDTO 리턴한다~~~~");
		return mySalesRatioDTO;

	}

	
	
	
	

}
