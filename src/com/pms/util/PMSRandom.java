package com.pms.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.pms.dao.RandomInsert;

/*데이터 자동생성 클래스*/
public class PMSRandom {
	// 차번호 생성
	public ArrayList<String> CNUM_RAND(int count) {
		
		Random rand = new Random();
		StringBuffer NUM = new StringBuffer();
		String[] lang = {"가","나","다","라","마","바","사","아","자","차"};
		ArrayList<String> CNUM = new ArrayList<String>();
		
		for(int i = 0 ; i < count; i++) {
			for(int j = 0; j < 2; j++) {
				NUM.append(rand.nextInt(10));
			}
			NUM.append(lang[rand.nextInt(10)]);
			for(int j = 0; j < 4; j++) {
				NUM.append(rand.nextInt(10));
			}
			if(!CNUM.contains(NUM)) {
				CNUM.add(NUM.toString());
			}
			NUM.setLength(0);
		}
		return CNUM;
	}
	
	public static String randomHangulName() {
	    List<String> first = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
	        "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
	        "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
	        "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
	        "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
	    List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
	        "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
	        "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
	        "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
	        "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
	        "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
	        "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
	        "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
	        "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
	        "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
	    Collections.shuffle(first);
	    Collections.shuffle(name);
	    return first.get(0) + name.get(0) + name.get(1);
	  }


	// 로그 생성
	public void TIME_SETTING(ArrayList<String> CNUM , int count) {
		Random rand = new Random();
		
		String startDate = "2020-5-1 00:00:00";
		String endDate = "2020-9-10 00:00:00";
		Timestamp stime = Timestamp.valueOf(startDate); 
		Timestamp etime = Timestamp.valueOf(endDate);
		
		long rand_diff;
		long diff = etime.getTime() - stime.getTime();
		
		HashMap<String, ArrayList<Long>> map = new HashMap<>();
		
		ArrayList<Long> rand_time_arr = null;
		
		for(int i = 0; i < CNUM.size() ; i++) {
			rand_time_arr = new ArrayList<Long>();
			for(int j = 0; j < count ; j++) {
				rand_diff = (long)(Math.random() * diff);
				long rand_st = stime.getTime() + rand_diff;
				rand_time_arr.add(rand_st);
			}
			Collections.sort(rand_time_arr);
			map.put(CNUM.get(i), rand_time_arr);
		}
		
		Set<String> keys = map.keySet();
		String in = null;
		String out = null;
		long st = 0;
		long gt = 0;
		RandomInsert randomInsert = new RandomInsert();
		for (String key : keys) {
		  for(int i = 0; i < map.get(key).size(); i++) {
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			  gt = map.get(key).get(i);
			  
			  if(gt > st && i > 0) {
				  continue;
			  }
			  
			  rand_diff = (long)(Math.random() * 86400000) + 43200000;
			  st = gt + rand_diff;			  
			  
			  in = format.format(new Date(map.get(key).get(i)));

			  if(i < map.get(key).size()-1) {
				  out = format.format(new Date(st));
			  }else {
				  out = null;
			  }
			  
			  randomInsert.randomLogAdd(key,in,out);
		  }
		}
	}
	
	//월정액 회원 생성
	private void MONTH_SETTING(ArrayList<String> CNUM) {
		Random rand = new Random();
		String startDate = "2020-5-1 00:00:00";
		String endDate = "2020-9-10 00:00:00";
		Timestamp stime = Timestamp.valueOf(startDate); 
		Timestamp etime = Timestamp.valueOf(endDate);
		
		long rand_diff;
		long diff = etime.getTime() - stime.getTime();
		
		HashMap<String, ArrayList<Long>> map = new HashMap<>();
		
		ArrayList<Long> rand_time_arr = null;
		
		for(int i=0; i < (CNUM.size()/5); i++) {
			rand_time_arr = new ArrayList<Long>();
			for(int j = 0; j < 4 ; j++) {
				rand_diff = (long)(Math.random() * diff);
				long rand_st = stime.getTime() + rand_diff;
				rand_time_arr.add(rand_st);
			}
			Collections.sort(rand_time_arr);
			map.put(CNUM.get(i), rand_time_arr);
		}
		
		Calendar cal = Calendar.getInstance(); // stopDate 사용

		Set<String> keys = map.keySet();
		String in = null;
		String out = null;
		String toDate = null;
		String stopDate = null;
		long st = 0;
		long gt = 0;
		RandomInsert randomInsert = new RandomInsert();
		for (String key : keys) {
			String name = randomHangulName();
		  for(int i = 0; i < map.get(key).size(); i++) {

			  
			  
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  cal.setTime(new Date(map.get(key).get(i))); 
			  cal.add(Calendar.MONTH, 1);	 
			  //long a = cal.getTime();
			  //long b = map.get(key).get(i+1);
			  if(i < map.get(key).size()-1) {
				  if(map.get(key).get(i)+(864000000*30) > map.get(key).get(i+1)) {
					  	continue;
				  }
				  toDate = format.format(new Date(map.get(key).get(i)));
				  stopDate = format.format(cal.getTime()); // String 으로 반환
				  randomInsert.randomMemberAdd(key,toDate,stopDate,name);
			  }

		  }
		}
	}
	
	public static void main(String[] args) {
		PMSRandom random = new PMSRandom();
		ArrayList<String> ran = random.CNUM_RAND(300); 
		//random.TIME_SETTING(ran, 100);
		random.MONTH_SETTING(ran);
		
		System.out.println("성공");
	}

}
