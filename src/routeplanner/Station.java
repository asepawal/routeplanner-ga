package routeplanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Station {
	private static ArrayList<Station> stations = new ArrayList<Station>();
	
	private double lon;
	private double lat;
	private int i;
	
    public static void addStation(Station station) {
    	stations.add(station);
    }

    public static Station getStation(int index){
        return stations.get(index);
    }

    public static int numberOfStations(){
        return stations.size();
    }
	
	public static void readFromFile(String path) {
		try {
			BufferedReader csvReader;
			String row;
			csvReader = new BufferedReader(new FileReader(path));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				stations.add(new Station(Double.valueOf(data[0]), Double.valueOf(data[1])));
			}
			csvReader.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Station(int i, double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
		this.i = i;
	}
	
	public Station(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public double getLon() {
		return this.lon;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public int getIndex() {
		return this.i;
	}
	
	public double getDistanceTo(Station to) {
		//Manhatan Distance
		return (Math.abs(this.lon - to.getLon()) + Math.abs(this.lat - to.getLat()))*100000;
	}
}
