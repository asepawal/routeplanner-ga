package routeplanner;

public class Main {

	public static void main(String[] args) {

		Station.readFromFile("dataset/hotspot.csv");

		Population pop = new Population(50, true);
		
		System.out.println("Start");
		double initialDistance = pop.getFittest().getDistance();

		// Evolve population till converge
		pop = GA.evolvePopulation(pop);
		Route fittest = pop.getFittest();
		int i = 0, j = 0;
		while(j <  10000) {
			pop = GA.evolvePopulation(pop);
			if(pop.getFittest().getDistance() < fittest.getDistance()) {
				fittest = pop.getFittest();
				System.out.println(i++ + ": " + pop.getFittest().getDistance());
				j = 0;
			}else {
				j++;
			}
			
		}
		System.out.println("Base Distance: 	" + initialDistance);
		System.out.println("Final Distance: " + pop.getFittest().getDistance());
		System.out.println("Route Order: 	" + fittest);
		
	}
}
