package routeplanner;

public class Population {
    Route[] routes;

    public Population(int populationSize, boolean initialise) {
        routes = new Route[populationSize];
        if (initialise) {
            for (int i = 0; i < populationSize(); i++) {
            	Route newRoute = new Route();
            	newRoute.generateIndividual();
                saveRoute(i, newRoute);
            }
        }
    }

    public void saveRoute(int index, Route route) {
        routes[index] = route;
    }

    public Route getRoute(int index) {
        return routes[index];
    }

    public Route getFittest() {
    	Route fittest = routes[0];
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getRoute(i).getFitness()) {
                fittest = getRoute(i);
            }
        }
        
        return fittest;
    }

    public int populationSize() {
        return routes.length;
    }
}
