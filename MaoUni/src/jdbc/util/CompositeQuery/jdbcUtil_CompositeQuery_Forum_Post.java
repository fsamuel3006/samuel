package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Forum_Post {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("FP_ID".equals(columnName) || "FP_MEM_ID".equals(columnName) || "FP_STATUS".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("FP_TITLE".equals(columnName) || "FP_CONTENT".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("FP_UPDATE".equals(columnName))  // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";  //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("FP_ID", new String[] { "1" });
		map.put("FP_MEM_ID", new String[] { "1" });
		map.put("FP_TITLE", new String[] { "貓咪" });
		map.put("FP_CONTENT", new String[] { "這次分享包含以下主題:新手注意事項,日常照護.......等" });
		map.put("FP_STATUS", new String[] { "0" });
		map.put("FP_UPDATE", new String[] { "2021-07-10" });
		map.put("deptno", new String[] { "10" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "SELECT * FROM FORUM_POST "
				          + jdbcUtil_CompositeQuery_Forum_Post.get_WhereCondition(map)
				          + "ORDER BY FP_ID";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
