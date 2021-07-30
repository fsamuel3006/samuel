package Redis.util.jedis;

import java.io.IOException;
import java.util.List;
//import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

//import redis.clients.jedis.GeoCoordinate;
//import redis.clients.jedis.GeoRadiusResponse;
//import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
//import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.JedisPool;

public class JedisHandleGeocode {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool(); // 相當於ds
	
	public static void geoAdd(String key, String memId, String address) {
		Jedis jedis = null;
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyALjOdTPZMiMWQVlR01yYwLZWHAVuhk6_w")
			    .build();
//		System.out.println("sssssssssssssssssss: " + context);
		jedis = pool.getResource();
		
		// 將地址轉為geocode
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context, address).await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Double lng = new Double(gson.toJson(results[0].geometry.location.lng));	// 經度
			Double lat = new Double(gson.toJson(results[0].geometry.location.lat));	// 緯度
			
			// 存入
			jedis.geoadd(key, lng, lat, memId);	
			jedis.close();
			
		} catch (ApiException | InterruptedException | IOException e) {
			throw new RuntimeException("GeocodingApi error occured. " + e.getMessage());
		}
		
	}
	
	// 取得會員經緯度
	public static List getGeo(String key, String memId) {
		Jedis jedis = null;
		jedis = pool.getResource();
		List geopos = jedis.geopos(key, memId);
		jedis.close();
//		System.out.println(geopos.get(0));
		return geopos;
	}
	
	
//	public static void testGeoadd() {
//		Jedis jedis = null;
//		jedis = pool.getResource();	// 相當於 ds getConnection
//
//		jedis.geoadd("citys", 116.41667, 39.91667, "beijin");
//		jedis.geoadd("citys", 121.48, 31.22, "Shanghai");
//		jedis.geoadd("citys", 117.20, 39.13, "Tianjin");
//		List geopos = jedis.geopos("citys", "beijin");
//		List geopos1 = jedis.geopos("memId", "3");
//		System.out.println(geopos1.get(0));
//		jedis.close();
//	}
//	
//	public static void main(String[] args) {
//		getGeo("memId", "3");
////		testGeoradius();
//	}

//	/**
//	 * * @Description:批量添加地理位置
//	 */
//	public static void testGeoadds() {
//		Jedis jedis = new Jedis("10.1.10.74", 6379);
//		jedis.auth("123");
//		GeoCoordinate beijin = new GeoCoordinate(116.41667, 39.91667);
//		GeoCoordinate Shanghai = new GeoCoordinate(121.48, 31.22);
//		GeoCoordinate Tianjin = new GeoCoordinate(117.20, 39.13);
//		Map map = Maps.newHashMap();
//		map.put("cs", beijin);
//		map.put("hh", Shanghai);
//		map.put("sy", Tianjin);
//		jedis.geoadd("citys", map);
//	}
//
//	/**
//	 * * @Description:georadius可以根据给定地理位置坐标获取指定范围内的地理位置集合
//	 */
//	public static void testGeoradius() {
//		Jedis jedis = new Jedis("10.1.10.74", 6379);
//		jedis.auth("123");
//		GeoRadiusParam withCoord = GeoRadiusParam.geoRadiusParam().count(6).sortAscending().withDist().withCoord();
//		List georadius = jedis.georadius("citys", 121.48, 31.22, 5000, GeoUnit.KM, withCoord);
//		georadius.forEach(e ->{System.out.println(e.getMemberByString());});
//	}
}


//public GeocoderGeometry locationToCoordinate(String location) throws IOException {
//    GeocoderGeometry coordinate = null;
//
//    if (location != null && !location.isEmpty()) {
//        GeocoderRequest request = new GeocoderRequest();
//        request.setAddress(location);
//
//        GeocodeResponse response = geocoder.geocode(request);
//        if (response.getStatus() == GeocoderStatus.OK) {
//            List<GeocoderResult> results = response.getResults();
//
//            for (GeocoderResult result : results) {
//                GeocoderGeometry geometry = result.getGeometry();
//                coordinate = geometry;
//                break;
//            }
//        }
//    }
//
//    return coordinate;
//}
 