package com.example.demo.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@EnableScheduling
@Controller
public class HomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	Calendar cl = Calendar.getInstance();
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmm");
	String record = sdf2.format(cl.getTime());
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMdd");
	String data = sdf4.format(cl.getTime());

	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
	String time = sdf3.format(cl.getTime());
	//    HomeController date = new HomeController();
	private int sbid;
	private int sbid2;
	private int sbid3;
	private int fre;
	private int fre2;
	private int fre3;
	private int hikakufre;
	private int hikakufre2;
	private int hikakufre3;;
	private int stack = 0;
	private int count = 0;
	private int count2 = 0;
	private int count3 = 0;
	private String sb;
	private String sb2;
	private String sb3;
	private String status = "×";
	private String status2 = "×";
	private String status3 = "×";
	



	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String viewPage(HttpServletRequest request,Model model, HttpSession session)throws ParseException {
		String id = (String) session.getAttribute("userId");

		// ユーザーがSearchControllerから来たかどうかを確認
		boolean fromSearch = session.getAttribute("fromSearch") != null && (boolean) session.getAttribute("fromSearch");

		// ユーザーがLoginControllerから来たかどうかを確認
		boolean fromLogin = session.getAttribute("fromLogin") != null && (boolean) session.getAttribute("fromLogin");

		if (fromSearch) {
			model.addAttribute("showBackButton", true);
		} else if(fromLogin) {
			model.addAttribute("showBackButton", false);
		}
		// 一度表示したら、セッションの情報をクリアする
		session.removeAttribute("fromSearch");
		session.removeAttribute("fromLogin");

		List<Map<String, Object>> resultList;

		resultList = jdbcTemplate.queryForList("SELECT * FROM seito "
				+ "LEFT JOIN kaisu ON seito.id = kaisu.id "
				+ "LEFT JOIN kamoku ON kaisu.subjectid = kamoku.subjectid "
				+ "WHERE seito.id = ? "
				+ "ORDER BY kaisu.subjectid ASC, kaisu.frequency ASC;", id);



		model.addAttribute("selectResult", resultList);

		List<Map<String, Object>> kamokuList = jdbcTemplate.queryForList("SELECT  DISTINCT subjectid,subject FROM kamoku ");
		model.addAttribute("selectResult2", kamokuList);

		List<Map<String, Object>> kaisuList = jdbcTemplate.queryForList("SELECT DISTINCT id, frequency FROM kaisu ORDER BY frequency ASC;");
		model.addAttribute("selectResult3", kaisuList);

		int lastFrencency = (int) kaisuList.get(kaisuList.size()-1).get("frequency");
		Map<String, String[]> resultMap = new HashMap<>();
		resultList.forEach((row) -> {
			if (!resultMap.containsKey(row.get("subject"))) {
				resultMap.put((String)row.get("subject"), new String[lastFrencency]);
			}
			String[] statusArr = resultMap.get(row.get("subject"));
			statusArr[(int)row.get("frequency")-1] = (String)row.get("status");
		});
		model.addAttribute("data", resultMap);



		///ここからが曜日取得プログラム
		Date date = new Date();

		// Calendarインスタンスを取得
		Calendar calendar = Calendar.getInstance();

		// DateをCalendarにセット
		calendar.setTime(date);

		// 曜日を取得
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		// 曜日を日本語で出力;


		String[] weekDays = {"", "日", "月", "火", "水", "木", "金", "土"};
		System.out.println("今日は" + dayOfWeek + "曜日です。");
		System.out.println("今日は" + weekDays[dayOfWeek] + "曜日です。");





		switch(dayOfWeek){
		case 1:

			break;

		case 2:

			this.sbid = 1;
			this.sbid2 = 2;
			this.sbid3 = 3;
			this.sb = "データベース応用";
			this.sb2 = "React演習";
			this.sb3 = "デジタル法制度";
			break;
		case 3:

			this.sbid2 = 4;
			this.sbid3 = 5;
			this.sb2 = "javaフレームワーク";
			this.sb3 = "卒業制作";
			break;
		case 4:

			this.sbid = 5;
			this.sbid2 = 6;
			this.sb = "卒業制作";
			this.sb2 = "テスト技法";
			break;
		case 5:

			this.sbid = 7;
			this.sbid2 = 8;
			this.sbid3 = 5;
			this.sb = "AI演習";
			this.sb2 = "C#演習";
			this.sb3 = "卒業制作";
			break;
		case 6:

			this.sbid = 5;
			this.sbid2 = 9;
			this.sbid3 = 4;
			this.sb = "卒業制作";
			this.sb2 = "C言語検定";
			this.sb3 = "javaフレームワーク";
			break;
		case 7:


			break;  
		}



		///ここまでが曜日取得プログラム

		///ここから定期実行一限目
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (dayOfWeek <= 6 && dayOfWeek >1){
					List<Map<String, Object>> tuikaList = jdbcTemplate.queryForList("SELECT id, subjectid, MAX(frequency) AS fre FROM kaisu WHERE id = ? AND subjectid = ?;",id,sbid);
					model.addAttribute("tuikaList",tuikaList);
					List<Map<String, Object>> tuikaList2 = jdbcTemplate.queryForList("SELECT id, subjectid, MAX(frequency) AS fre2 FROM kaisu WHERE id = ? and subjectid = ?;",id,sbid2);
					model.addAttribute("tuikaList2",tuikaList2);
					List<Map<String, Object>> tuikaList3 = jdbcTemplate.queryForList("SELECT id, subjectid, MAX(frequency) AS fre3 FROM kaisu WHERE id = ? and subjectid = ?;",id,sbid3);
					model.addAttribute("tuikaList3",tuikaList3);
					List<Map<String, Object>> hikakuList = jdbcTemplate.queryForList("SELECT subjectid, MAX(frequency) AS hikakufre FROM kaisu WHERE subjectid = ?;",sbid);
					model.addAttribute("hikakuList",hikakuList);
					List<Map<String, Object>> hikakuList2 = jdbcTemplate.queryForList("SELECT subjectid, MAX(frequency) AS hikakufre2 FROM kaisu WHERE subjectid = ?;",sbid2);
					model.addAttribute("hikakuList2",hikakuList2);
					List<Map<String, Object>> hikakuList3 = jdbcTemplate.queryForList("SELECT subjectid, MAX(frequency) AS hikakufre3 FROM kaisu WHERE subjectid = ?;",sbid3);
					model.addAttribute("hikakuList3",hikakuList3);
					System.out.println(tuikaList.get(0).get("fre") );
					System.out.println(tuikaList2.get(0).get("fre2")  );
					System.out.println(tuikaList3.get(0).get("fre3")  );
					System.out.println(hikakuList.get(0).get("hikakufre")  );
					System.out.println(hikakuList2.get(0).get("hikakufre2")  );
					System.out.println(hikakuList3.get(0).get("hikakufre3")  );
					fre = (int) tuikaList.get(0).get("fre");
					fre2 = (int) tuikaList2.get(0).get("fre2");
					fre3 = (int) tuikaList3.get(0).get("fre3");
					hikakufre =  (int) hikakuList.get(0).get("hikakuList");
					hikakufre2 = (int) hikakuList2.get(0).get("hikakuList2");
					hikakufre3 = (int) hikakuList3.get(0).get("hikakuList3");
					

					
				}
				//10時より前の時間だったら実行
				if(record.compareTo(data + "100000") >= 0) {
					System.out.println("1限実行しない");
				}else {
					if (dayOfWeek <= 6 && dayOfWeek >1){

						for(int i = fre;i < hikakufre ;i++)	{
							status = "×";

							fre++;
							jdbcTemplate.update("insert into kaisu (id,subjectid,subject,frequency,status) value (?,?,?,?,?)", id, sbid,sb,fre, status);
						}


					}   

					if(stack == 0 && count == 0) {
						System.out.println("1限出席");
						fre++;
						
						stack = 1;
					}

					System.out.println("実行済み");
					System.out.println(fre );
					System.out.println(id );
					System.out.println(status );
					System.out.println("1限は" + sbid + sb );






					timer.cancel();
				}
			}
		};
		timer.schedule(task, sdf.parse(time +" 09:25:00"));


		///ここまで定期実行一限目   

		///ここから定期実行二限目


		Timer timer2 = new Timer(false);
		TimerTask task2 = new TimerTask() {

			@Override
			public void run() {
				if(record.compareTo(data + "114500") >= 0) {
					System.out.println("2限実行しない");
				}else {
					if (dayOfWeek <= 6 && dayOfWeek >1){
						for(int i = fre2;i < hikakufre2 ;i++)	{
							status2 = "×";

							fre2++;
							jdbcTemplate.update("insert into kaisu (id,subjectid,subject,frequency,status) value (?,?,?,?,?)", id, sbid2,sb2,fre2, status2);
						}


						if(stack == 0 && count2 <= 0) {
							System.out.println("2限出席");
							fre2++;
							
							stack = 2;
						}
						System.out.println(fre2 );
						System.out.println(id );
						System.out.println(status );
						System.out.println("2限は" + sbid2 + sb2 );



					}
					timer2.cancel();
				}
			}
		}; 
		timer2.schedule(task2, sdf.parse(time +" 11:15:00"));


		///ここまで定期実行二限目

		///ここから定期実行三限目

		Timer timer3 = new Timer(false);
		TimerTask task3 = new TimerTask() {
			@Override
			public void run() {
				if(record.compareTo(data + "151500") >= 0) {
					System.out.println("3限実行しない");
				}else {
					if (dayOfWeek <= 6 && dayOfWeek >1){
						for(int i = fre3;i < hikakufre3 ;i++)	{
							status3 = "×";

							fre3++;
							jdbcTemplate.update("insert into kaisu (id,subjectid,subject,frequency,status) value (?,?,?,?,?)", id, sbid3,sb3,fre3, status3);
						}


						if(stack == 0 && count3 <= 0) {
							System.out.println("3限出席");
							fre3++;
							
							stack = 3;
						}
						System.out.println(fre3 );
						System.out.println(id );
						System.out.println(status );
						System.out.println("3限は" + sbid3 + sb3 );


						status3 = "〇";



					}
					timer3.cancel();
				}
			}
		}; 
		timer3.schedule(task3, sdf.parse(time +" 13:45:00"));

		///ここまで定期実行三限目




		return "home";
	}


	//出席するボタンを押したときに動く
	@RequestMapping(path = "/home", method = RequestMethod.POST)
	public String postUpdStu(String latitude,String longitude,String accuracy,HttpServletRequest request, Integer frequency,String subject,String status, HttpSession session, Model model) {

		String id = (String) session.getAttribute("userId");
		///東経140.111244の誤差が0.002、北緯35.6302573の誤差が0.002以内だった場合は実行
		if(Double.parseDouble(longitude) >= 140.109244 && Double.parseDouble(longitude) <= 140.113244 && Double.parseDouble(latitude) >= 35.6282573 && Double.parseDouble(latitude) <= 35.6322573) {
			status = "〇";

			switch(stack){
			case 1:
				count++;
				System.out.println("1限実行");
				System.out.println(fre);
				jdbcTemplate.update("insert into kaisu(id,subjectid,subject,frequency,status) value (?,?,?,?,?)",id, sbid,sb,fre,status);
				stack = 0;
				
				break;
			case 2:
				count2++;
				System.out.println("2限実行");
				System.out.println(fre2);
				jdbcTemplate.update("insert into kaisu(id,subjectid,subject,frequency,status) value (?,?,?,?,?)", id, sbid2,sb2,fre2, status2);
				stack = 0;
				
				break;
			case 3:
				count3++;
				System.out.println("3限実行");
				System.out.println(fre3);
				jdbcTemplate.update("insert into kaisu(id,subjectid,subject,frequency,status) value (?,?,?,?,?)", id, sbid3,sb3,fre3, status3);
				
				stack = 0;
				break;
			}
		}

		System.out.println(record);
		System.out.println(stack);
		System.out.println("北緯"+latitude);
		System.out.println("東経 "+longitude);


		
		System.out.println(sbid);
		System.out.println(fre);
		System.out.println(status);

		
		System.out.println(sbid2);
		System.out.println(fre2);
		System.out.println(status2);


		
		System.out.println(sbid3);
		System.out.println(fre3);
		System.out.println(status3);




		return "redirect:/home";
	}



	//更新ボタンを押したときに動く
	@RequestMapping(path = "/home/teacher", method = RequestMethod.POST)
	public String postUpd(String accuracy,HttpServletRequest request, Integer frequency,String subject,String status, HttpSession session, Model model) {

		String id = (String) session.getAttribute("userId");

		System.out.println(id);
		System.out.println(status);
		System.out.println(subject);
		System.out.println(frequency);

		//kaisuテーブルの科目名と授業回数をもとにステータスを変える
		jdbcTemplate.update("UPDATE kaisu SET status = ? WHERE id = ? AND subject = ? AND frequency = ?",id,status,subject,frequency);



		return "redirect:/home";
	}
}