package com.hzy.weiqiu.fragment.finding;

import java.util.LinkedList;

import com.ITXY.weixin.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.hzy.weiqiu.MyApplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LocationFilter extends Activity {
	private MapView mMapView = null;
	private BaiduMap mBaiduMap;
	private Button reset;
	private LocationService locService;
	private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>(); // 存放历史定位结果的链表，�?大存放当前结果的�?5次定位结�?
	protected boolean isFirstLoc=true;
	protected LatLng p1;
	protected LatLng p2;
	protected TextView tvDistance;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locationfilter);
		mMapView = (MapView) findViewById(R.id.bmapView);
		reset = (Button) findViewById(R.id.clear);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
		locService = ((MyApplication) getApplication()).locationService;
		LocationClientOption mOption = locService.getDefaultLocationClientOption();
		mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy); 
		mOption.setCoorType("bd09ll");
		locService.setLocationOption(mOption);
		locService.registerListener(listener);
		locService.start();
	}

	/***
	 * 定位结果回调，在此方法中处理定位结果
	 */
	BDLocationListener listener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub

			if (location != null && (location.getLocType() == 161 || location.getLocType() == 66)) {
				Message locMsg = locHander.obtainMessage();
				Bundle locData;
				locData = Algorithm(location);
				if (locData != null) {
					locData.putParcelable("loc", location);
					locMsg.setData(locData);
					locHander.sendMessage(locMsg);
				}
			}
			

        

		}
	};

	/***
	 * 平滑策略代码实现方法，主要�?�过对新定位和历史定位结果进行�?�度评分�?
	 * 来判断新定位结果的抖动幅度，如果超过经验值，则判定为过大抖动，进行平滑处�?,若�?�度过快�?
	 * 则推测有可能是由于运动�?�度本身造成的，则不进行低�?�平滑处�? �?(●｀�?´�?)�?
	 * 
	 * @param BDLocation
	 * @return Bundle
	 */
	private Bundle Algorithm(BDLocation location) {
		Bundle locData = new Bundle();
		double curSpeed = 0;
		if (locationList.isEmpty() || locationList.size() < 2) {
			LocationEntity temp = new LocationEntity();
			temp.location = location;
			temp.time = System.currentTimeMillis();
			locData.putInt("iscalculate", 0);
			locationList.add(temp);
		} else {
			if (locationList.size() > 5)
				locationList.removeFirst();
			double score = 0;
			for (int i = 0; i < locationList.size(); ++i) {
				LatLng lastPoint = new LatLng(locationList.get(i).location.getLatitude(),
						locationList.get(i).location.getLongitude());
				LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
				double distance = DistanceUtil.getDistance(lastPoint, curPoint);
				curSpeed = distance / (System.currentTimeMillis() - locationList.get(i).time) / 1000;
				score += curSpeed * Utils.EARTH_WEIGHT[i];
			}
			if (score > 0.00000999 && score < 0.00005) { // 经验�?,�?发�?�可根据业务自行调整，也可以不使用这种算�?
				location.setLongitude(
						(locationList.get(locationList.size() - 1).location.getLongitude() + location.getLongitude())
								/ 2);
				location.setLatitude(
						(locationList.get(locationList.size() - 1).location.getLatitude() + location.getLatitude())
								/ 2);
				locData.putInt("iscalculate", 1);
			} else {
				locData.putInt("iscalculate", 0);
			}
			LocationEntity newLocation = new LocationEntity();
			newLocation.location = location;
			newLocation.time = System.currentTimeMillis();
			locationList.add(newLocation);

		}
		return locData;
	}

	/***
	 * 接收定位结果消息，并显示在地图上
	 */
	private Handler locHander = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			try {
				BDLocation location = msg.getData().getParcelable("loc");
				int iscal = msg.getData().getInt("iscalculate");
				if (location != null) {
					LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
					// 构建Marker图标
					BitmapDescriptor bitmap = null;
					if (iscal == 0) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo); // 非推算结�?
					} else {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark); // 推算结果
					}
					if (isFirstLoc) {
		                isFirstLoc = false;
		                // p1=new LatLng(39.93923, 116.357428);
		                p1 = p2 = point;
		                OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
						// 在地图上添加Marker，并显示
						mBaiduMap.addOverlay(option);
						mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
		            } else {
		                // count++;
		                LatLng p0 = p2;
		                p2 = new LatLng(location.getLatitude(), location.getLongitude());
		                sum_distance = (int) (sum_distance + gps2m(p0.latitude,p0.longitude,p2.latitude,p2.longitude));
		               
		                // points.add(p2);
		                Log.d("points", "wwww");
		             // 构建MarkerOption，用于在地图上添加Marker
						OverlayOptions option = new MarkerOptions().position(p2).icon(bitmap);
						// 在地图上添加Marker，并显示
						mBaiduMap.addOverlay(option);
						mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));

		            }
				/*	// 构建MarkerOption，用于在地图上添加Marker
					OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
					// 在地图上添加Marker，并显示
					mBaiduMap.addOverlay(option);
					mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                */
		            tvDistance = (TextView)findViewById(R.id.distance);
		            //tvDistance.setText("现在走过的距离是�?"+sum_distance+"�?");
		            tvDistance.setText(""+sum_distance+"米");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管�?
//		WriteLog.getInstance().close();
		locService.unregisterListener(listener);
		locService.stop();
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管�?
		mMapView.onResume();
		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mBaiduMap != null)
					mBaiduMap.clear();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管�?
		mMapView.onPause();

	}

	/**
	 * 封装定位结果和时间的实体�?
	 * 
	 * @author baidu
	 *
	 */
	class LocationEntity {
		BDLocation location;
		long time;
	}
	 //-------------------------计算距离--------------------------

    private int sum_distance = 0;
    private final double EARTH_RADIUS = 6378137.0;

    private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
    double radLat1 = (lat_a * Math.PI / 180.0);
    double radLat2 = (lat_b * Math.PI / 180.0);
    double a = radLat1 - radLat2;
    double b = (lng_a - lng_b) * Math.PI / 180.0;
    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
    + Math.cos(radLat1) * Math.cos(radLat2)
    * Math.pow(Math.sin(b / 2), 2)));
    s = s * EARTH_RADIUS;
    //s = Math.round(s * 10000) / 10000;
    return 1.38*s;

    }

}
