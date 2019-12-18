package routeplanner;

import java.util.ArrayList;
import java.util.Collections;

public class Route {
    private ArrayList<Station> route = new ArrayList<Station>();
    private double fitness = 0;
    private int distance = 0;

    public Route(){
        for (int i = 0; i < Station.numberOfStations(); i++) {
            route.add(null);
        }
    }

    public Route(ArrayList<Station> route){
        this.route = route;
    }

    public void generateIndividual() {
        for (int stationIndex = 0; stationIndex < Station.numberOfStations(); stationIndex++) {
        	setStation(stationIndex, Station.getStation(stationIndex));
        }
        Collections.shuffle(route);
    }

    public Station getStation(int routePosition) {
        return route.get(routePosition);
    }

    public void setStation(int routePosition, Station station) {
        route.set(routePosition, station);
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = (100/(double)getDistance());
        }
        return fitness;
    }

    public double getDistance(){
        if (distance == 0) {
            int routeDistance = 0;
            for (int stationIndex=0; stationIndex < routeSize(); stationIndex++) {
            	Station fromStation = getStation(stationIndex);
            	Station destinationStation;
            	
                if(stationIndex+1 < routeSize()){
                    destinationStation = getStation(stationIndex+1);
                }
                else{
                    destinationStation = getStation(0);//origin
                }
                routeDistance += fromStation.getDistanceTo(destinationStation);
            }
            distance = routeDistance;
        }
        return distance;
    }

    public int routeSize() {
        return route.size();
    }

    public boolean containsStation(Station station){
        return route.contains(station);
    }

    @Override
    public String toString() {
//        String str = "https://www.google.com/maps/dir/";
//        Station x;
//        for (int i = 0; i < routeSize(); i++) {
//        	x = getStation(i);
//        	str += "'"+x.getLat()+"," + x.getLon() +"'/"; 
//        }
//        x = getStation(0);
//        str += "'"+x.getLat()+"," + x.getLon() +"'/"; //origin
        
        String str = "";
        for (int i = 0; i < routeSize(); i++) {
        	str += i + "," +getStation(i).getLat() + "," + getStation(i).getLon() +"\n";
        }
        
        return str;
    }

	public void print() {
		String str = "";
		for (int i = 0; i < routeSize(); i++) {
        	str += getStation(i).getIndex() + " >> "; 
        }
		System.out.println(str);
		
	}
}
