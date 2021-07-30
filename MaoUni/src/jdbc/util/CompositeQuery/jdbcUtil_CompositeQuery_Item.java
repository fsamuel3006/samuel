/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Item {

	public static String get_aCondition_For_myDB(String ITEM_ID, String ITEM_PET_TYPE, String ITEM_NAME, String ITEMT_ID, String value) {

		String aCondition = null;

		if ("ITEM_ID".equals(ITEM_ID)) { // 用於其他
			aCondition = ITEM_ID + "=" + value;
		}
		if ("ITEMT_ID".equals(ITEMT_ID)) { // 用於其他
			aCondition = ITEMT_ID + "=" + value;
		}
		if ("ITEM_NAME".equals(ITEM_NAME)) { // 用於varchar 
			aCondition = ITEM_NAME + " like '%" + value + "%'";
		}
		if ("ITEM_PET_TYPE".equals(ITEM_PET_TYPE)) { // 用於varchar 
			aCondition = ITEM_PET_TYPE + " like '%" + value + "%'";
		}
		
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
				String aCondition = get_aCondition_For_myDB(key, key, key, key, value.trim());

				if (count == 1) {
					whereCondition.append(" where " + aCondition);
				}else {
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("ITEM_ID", new String[] { "1" });
		map.put("ITEM_TYPE_ID", new String[] { "1" });
		map.put("ITEM_PET_TYPE", new String[] { "貓" });		
		map.put("ITEM_NAME", new String[] { "萬用查詢測試名稱" });
		map.put("ITEM_CONTENT", new String[] { "萬用查詢測試內容" });
		map.put("ITEM_PRICE", new String[] { "500" });
		map.put("ITEM_AMOUNT", new String[] { "19" });
		map.put("ITEM_STATUS", new String[] { "1" });
		map.put("ITEM_UPDATE", new String[] { "2021-7-25" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "SELECT * FROM ITEM "
				          + jdbcUtil_CompositeQuery_Item.get_WhereCondition(map)
				          + "ORDER by ITEM_ID";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
