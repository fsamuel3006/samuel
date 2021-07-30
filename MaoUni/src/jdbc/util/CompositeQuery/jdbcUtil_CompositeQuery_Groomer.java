package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;

public class jdbcUtil_CompositeQuery_Groomer {
	public static String get_aCondition_For_myDB(String columnName, String value) {
		String aCondition = null;
		// 若要忽略大小寫需用 equalsIgnoreCase(String str)
		
		if ("groomerId".equals(columnName) || "gstatus".equals(columnName)) {
			aCondition = columnName + "=" + value;
		}
		if ("gname".equals(columnName)) {
			aCondition = columnName + " like '%" + value + "%'";
		}

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0]; // 每個key(param)都只會取得一個值，因為input非多選
			// 拿到action以外的param值，且確保不為null或""
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if ("gstatus".equals(key) && "all".equals(value) ) {
					count--;
					continue;
				}

				if (count == 1) {
					whereCondition.append(" where " + aCondition);
				} else {
					whereCondition.append(" and " + aCondition);
				}
			} // 如果未進入上方if區塊，則會回傳""

		}
		System.out.println("whereCondition: " + whereCondition);
		return whereCondition.toString();

	}
}
