package com.pms.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/*주차정보 자동 생성*/
public class PMSRandom {

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
			//System.out.println(NUM);
			
			if(!CNUM.contains(NUM)) {
				CNUM.add(NUM.toString());
			}
			NUM.setLength(0);
		}
		return CNUM;
	}
	
	public void TIME_SETTING(ArrayList<String> CNUM , int count) {
		Random rand = new Random();

		String startDate = "2020-5-1 00:00:00";
		String endDate = "2020-8-21 00:00:00";
		
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

		for (String key : keys) {
		  System.out.println(key);
		  for(int i = 0; i < map.get(key).size(); i++) {
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  System.out.println(format.format(new Date(map.get(key).get(i))));
			  if(i % 2 == 0) {
				//insert
				//차량번호 key / 입차 map.get(key).get(i) / 출차 map.get(key).get(i+1) 
				  
			  }
		  }
		}

	}
	
	public static void main(String[] args) {
		
		PMSRandom random = new PMSRandom();
		
		ArrayList<String> ran = random.CNUM_RAND(500); //차량번호 생성
		random.TIME_SETTING(ran, 99);
		

		
	}

}
