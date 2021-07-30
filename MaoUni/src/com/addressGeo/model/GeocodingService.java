package com.addressGeo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.JedisPool;

import Redis.util.jedis.JedisHandleGeocode;
import Redis.util.jedis.JedisPoolUtil;

import java.util.List;
import java.util.Map;

public class GeocodingService {
	private static JedisHandleGeocode jedisHandleGeocode;

	public GeocodingService() {
	}

	public void addGeo(Integer memId, String address) {
		String key = "memId";

		// 檢查redis裡有沒有該筆會員的經緯度資料
		try {
			List geopos = jedisHandleGeocode.getGeo(key, memId.toString());
			if (geopos.get(0) == null) {
				jedisHandleGeocode.geoAdd(key, memId.toString(), address);
			}
		} catch (Exception e) {
			// 如果沒有查找的key會發生例外，因此進行第一次的建立
			jedisHandleGeocode.geoAdd(key, memId.toString(), address);

		}
	}

	public List getGeocode(Integer memId) {
		String key = "memId";
		return jedisHandleGeocode.getGeo(key, memId.toString());
	}

	public static void main(String[] args) {
		GeocodingService g = new GeocodingService();
		g.addGeo(1, "新北市板橋區三民路一段一巷23號");
		g.addGeo(6, "台北市大安區敦化南路二段105號");
		List d = g.getGeocode(3);
		System.out.println(d.get(0));
		
	}

}
