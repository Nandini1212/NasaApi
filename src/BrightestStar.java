import java.util.ArrayList;
import java.util.List;

public class BrightestStar {
		public String getBrightestStar(List<Location> locations, String minDate) {
			FireBallServiceImpl fireBallService = new FireBallServiceImpl();
			List<FireBall> fireballDataPoints = fireBallService.getDataFromAPICall(minDate);
			if (fireballDataPoints == null || fireballDataPoints.isEmpty()) {
				return "noDataAvailable";
			}
			double maxBrightness = Integer.MIN_VALUE;
			List<Object> brightestStarList = new ArrayList<>();
			String brightestStar = "";

			for (Location location : locations) {
				double latitude = location.getLatitude(), longitude = location.getLongitude();
				System.out.println("loc : " + location.getName());
				double locationMaxBrightness = Integer.MIN_VALUE;
				double minLatitude = 0.0;
				double minLongitude, maxLatitude;
				double maxLongitude = 0.0;
				minLatitude = latitude - 15;
				maxLatitude = latitude + 15;
				minLongitude = longitude - 15;
				maxLongitude = longitude + 15;
				for (FireBall fireball : fireballDataPoints) {
					System.out.println("dyn : " + fireball.getTotalImpactEnergy());
					if (fireball.getLatDir().equalsIgnoreCase(location.getLatDirection())
							&& fireball.getLongDir().equalsIgnoreCase(location.getLongDirection())) {
						System.out.println("f lat : " + fireball.getLatDir()
								+ "l lat : " + " " + location.getLatDirection()
								+ "f long : " + " " + fireball.getLongDir()
								+ "l long : " + location.getLongDirection()
								+ "lat dir : " + fireballDataPoints.get(2) + " " +
								"long dir : " + fireballDataPoints.get(4));
						if (Util.isInclusive(fireball.getLatitude(), minLatitude, maxLatitude)
								&& Util.isInclusive(fireball.getLongitude(), minLongitude, maxLongitude)) {
							if (locationMaxBrightness < fireball.getTotalImpactEnergy()) {
								locationMaxBrightness = fireball.getTotalImpactEnergy();
								System.out.println("en : " + fireball.getTotalImpactEnergy());
							}
							//}
						}
					}
				}
					if (locationMaxBrightness > maxBrightness) {
						maxBrightness = locationMaxBrightness;
						brightestStar = location.getName();
					}
				}
				brightestStarList.add(brightestStar);
				brightestStarList.add(maxBrightness);
				return "location " + brightestStarList.get(0) + " brightest star energy" + brightestStarList.get(1);
			}
//	List<FireBall> fireballDataPoints;
//	int LAT_LONG_RANGE = 15;


//	public String getBrightestShootingStar(List<Location> locationInputs, String minDate)
//	{
//		//Data is fetched as per date filter , avoiding redundant calls to api
//		//It avoids slow system and unstable
//		FireBallServiceImpl apiService=new FireBallServiceImpl();
//		fireballDataPoints = apiService.getDataFromAPICall(minDate);
//
//		System.out.println("fire ball 1 : " + fireballDataPoints.get(0).getTotalImpactEnergy());
//
//		if (fireballDataPoints.isEmpty()) {
//			return "API_NO_DATA";
//		}
//		List<Object> getBrightestStar= getBrightestStarDetails(locationInputs, fireballDataPoints);
//		return "BRIGHTEST_LOCATION " + getBrightestStar.get(0)
//				+"BRIGHTEST_STAR_ENERGY : "+ getBrightestStar.get(1);
//	}
//
//	private List<Object> getBrightestStarDetails(List<Location> locationInputs, List<FireBall> fireballDataPoints) {
//		double maxBrightness = Integer.MIN_VALUE;
//		double brightness;
//		List<Object> getBrightestStar =new ArrayList<>();
//		String brightestStar = "";
//
//		for (Location locationInput : locationInputs) {
//			brightness=fireball(locationInput.getLatitude(),locationInput.getLongitude(),locationInput);
//			System.out.println("brightness : " + brightness);
//			if(maxBrightness<brightness)
//			{
//				maxBrightness=brightness;
//				brightestStar=locationInput.getName();
//			}
//		}
//		getBrightestStar.add(brightestStar);
//		getBrightestStar.add(maxBrightness);
//		return getBrightestStar;
//	}
//
//	double fireball(double latitude, double longitude, Location locationInput){
//		double latMin, latMax, longMin, longMax;
//		double maxBrightnessPerLoc = Integer.MIN_VALUE;
//
//		latMin = latitude - LAT_LONG_RANGE;
//		latMax = latitude + LAT_LONG_RANGE;
//		longMin = longitude - LAT_LONG_RANGE;
//		longMax = longitude + LAT_LONG_RANGE;
//
//		for (FireBall fireball : fireballDataPoints) {
//			System.out.println("fire ball : " + fireball.getTotalImpactEnergy());
//			if (fireball.getLatDir().equalsIgnoreCase(locationInput.getLatDirection())
//					&& fireball.getLongDir().equalsIgnoreCase(locationInput.getLongDirection())) {
//				System.out.println("f lat : " + fireball.getLatDir()
//						+ "l lat : " + " " +locationInput.getLatDirection()
//						+ "f long : " + " " + fireball.getLongDir()
//						+ "l long : " + locationInput.getLongDirection()
//						+"lat dir : " + fireballDataPoints.get(2) + " " +
//						"long dir : " + fireballDataPoints.get(4));
//				if (Util.isInclusive(fireball.getLatitude(), latMin, latMax)
//						&& Util.isInclusive(fireball.getLongitude(), longMin, longMax)) {
//					if (maxBrightnessPerLoc < fireball.getTotalImpactEnergy()) {
//						maxBrightnessPerLoc = fireball.getTotalImpactEnergy();
//					}
//				}
//			}
//		}
//		return maxBrightnessPerLoc;
//	}

}