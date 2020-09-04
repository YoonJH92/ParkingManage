package com.pms.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
	//이름 자동생성
	public static String randomHangulName() {
	    List<String> first = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안");
	    List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
	        "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
	        "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
	        "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
	        "유", "윤", "율", "으", "은", "의", "이", "익");
	    Collections.shuffle(first);
	    Collections.shuffle(name);
	    return first.get(0) + name.get(0) + name.get(1);
	  }

	//이메일 자동생성
	public static String randomEmailName() {
	    List<String> name = Arrays.asList("a","b","c","d","e","f","g","h","1","2","3","4","5","6","7","8","9");
	    List<String> addr = Arrays.asList("@naver.com","@gmail.com","@nate.com");
	    Collections.shuffle(addr);
	    Collections.shuffle(name);
	    
	    StringBuffer buff = new StringBuffer();
	    
	    for(int i = 0 ; i < 6 ; i++) {
	    	buff.append(name.get(i));
	    }
	    return buff.toString() + addr.get(0);
	  }
	
	//전화번호 자동생성
	public static String randomPhoneName() {
	    List<String> name = Arrays.asList("0","1","2","3","4","5","6","7","8","9");
	    Collections.shuffle(name);
	    
	    StringBuffer buff = new StringBuffer();
	    
	    for(int i = 0 ; i < 8 ; i++) {
	    	buff.append(name.get(i));
	    }
	    buff.insert(4, "-");
	    
	    return "010-"+buff.toString();
	  }
	
	// 타입 자동생성
	public static String randomPhoneType() {
	    List<String> name = Arrays.asList("관리자","일반","일반","일반","일반","일반","일반","일반","일반");
	    Collections.shuffle(name);
	    return name.get(0);
	  }
	

	// 로그 생성
	public void TIME_SETTING(ArrayList<String> CNUM , int count) {
		String startDate = "2020-5-1 00:00:00";
		Timestamp stime = Timestamp.valueOf(startDate); 
		Timestamp etime = Timestamp.valueOf(LocalDateTime.now());
		
		long rand_diff;
		long diff = etime.getTime() - stime.getTime(); // 종료시간 - 시작시간
		
		HashMap<String, ArrayList<Long>> map = new HashMap<>();
		
		ArrayList<Long> rand_time_arr = null; // 랜덤 시간 배열
		
		for(int i = 0; i < CNUM.size() ; i++) { // 차량번호 수 만큼 반복문
			rand_time_arr = new ArrayList<Long>();
			for(int j = 0; j < count ; j++) {
				rand_diff = (long)(Math.random() * diff); // 뺀 랜덤시간만큼 추가
				long rand_st = stime.getTime() + rand_diff; // 시작시간에 더함
				rand_time_arr.add(rand_st); // 리스트에 저장
			}
			Collections.sort(rand_time_arr); // 정렬
			map.put(CNUM.get(i), rand_time_arr); // map에 key value로 저장
		}

		Set<String> keys = map.keySet();
		String in = null;
		String out = null;
		long endTime = 0;
		int num = 0;
		RandomInsert randomInsert = new RandomInsert();
		for (String key : keys) { // map foreach문
		  for(int i = 0; i < map.get(key).size(); i++) { // map에 value 수만큼 반복
			  num = 0;
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			  if(i > 0 ) {
				  if(endTime > map.get(key).get(i)) { // 먼저 저장된 endTime값이랑 현재 시간 비교해서 endTime이 더 클시 패스
					  continue;
				  } 
			  }
			  
			  rand_diff = (long)(Math.random() * 43200000) + 86400000; // 기본 하루 + 반나절랜덤 시간추가
			  endTime = map.get(key).get(i) + rand_diff; //나간 시간
			  
			  in = format.format(new Date(map.get(key).get(i))); //입장시간 string 형태로 변환 

			  if(i < map.get(key).size()-1) {
				  out = format.format(new Date(endTime));//마지막 시간은 null값으로 지정해서 실시간 주차현황 보여줌
			  }else {
				  out = null;
			  }
			  num = randomInsert.monthNum(key,in,out); // 월정액 사용자 체크
			  randomInsert.randomLogAdd(key,in,out,num); // 차량로그 추가
		  }
		}
	}
	
	//월정액 회원 생성
	private void MONTH_SETTING(ArrayList<String> CNUM) {
		String startDate = "2020-5-1 00:00:00";
		Timestamp stime = Timestamp.valueOf(startDate); 
		Timestamp etime = Timestamp.valueOf(LocalDateTime.now());
		
		long rand_diff;
		long diff = etime.getTime() - stime.getTime(); // 종료시간 - 시작시간
		
		HashMap<String, ArrayList<Long>> map = new HashMap<>();
		
		ArrayList<Long> rand_time_arr = null;
		
		for(int i=0; i < (CNUM.size()/2); i++) { // 전체 차량수 개수 지정
			rand_time_arr = new ArrayList<Long>();
			for(int j = 0; j < 4 ; j++) { // 월정액 로그 개수
				rand_diff = (long)(Math.random() * diff); 
				long rand_st = stime.getTime() + rand_diff;
				rand_time_arr.add(rand_st);
			}
			Collections.sort(rand_time_arr);
			map.put(CNUM.get(i), rand_time_arr);
		}
		
		Calendar cal = Calendar.getInstance(); // stopDate 사용

		Set<String> keys = map.keySet();
		String toDate = null;
		String stopDate = null;
		RandomInsert randomInsert = new RandomInsert();
		for (String key : keys) {
			  String name = randomHangulName(); // 한글이름 랜덤생성
			  String email = randomEmailName(); // 이메일 랜덤생성
			  String phone = randomPhoneName(); // 전화번호 랜덤생성
			  String type = randomPhoneType(); // 타입 랜덤생성
		  for(int i = 0; i < map.get(key).size(); i++) {
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  cal.setTime(new Date(map.get(key).get(i))); 
			  cal.add(Calendar.MONTH, 1); // 저장된 시간 + 한달 데이터 구함
			  
			  if(i < map.get(key).size()-1) { 
				  if(map.get(key).get(i+1) > cal.getTimeInMillis() ) { // 한달 차이를 주기 위해 사용
					  toDate = format.format(new Date(map.get(key).get(i))); 
					  stopDate = format.format(cal.getTime()); // String 으로 반환
					  randomInsert.randomMemberAdd(key,toDate,stopDate,name,email,phone,type);
				  }
			  }
		  }
		}
	}
	
	public static void main(String[] args) {
		PMSRandom random = new PMSRandom();
		ArrayList<String> ran = random.CNUM_RAND(500); 
		random.MONTH_SETTING(ran);
		random.TIME_SETTING(ran, 50);
		
		System.out.println("성공");
	}

}
