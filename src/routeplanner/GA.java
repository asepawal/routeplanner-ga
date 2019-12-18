package routeplanner;

public class GA {
    private static final double mutationRate = 0.1;
    private static final int tournamentSize = 10;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveRoute(0, pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            Route parent1 = tournamentSelection(pop);
            Route parent2 = tournamentSelection(pop);
            Route child = crossover(parent1, parent2);
            newPopulation.saveRoute(i, child);
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getRoute(i));
        }

        return newPopulation;
    }

    public static Route crossover(Route parent1, Route parent2) {
        Route child = new Route();

        int startPos = (int) (Math.random() * parent1.routeSize());
        int endPos = (int) (Math.random() * parent1.routeSize());

        for (int i = 0; i < child.routeSize(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setStation(i, parent1.getStation(i));
            }
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setStation(i, parent1.getStation(i));
                }
            }
        }

        for (int i = 0; i < parent2.routeSize(); i++) {
            if (!child.containsStation(parent2.getStation(i))) {
                for (int ii = 0; ii < child.routeSize(); ii++) {
                    if (child.getStation(ii) == null) {
                        child.setStation(ii, parent2.getStation(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    private static void mutate(Route route) {
        for(int routePos1=0; routePos1 < route.routeSize(); routePos1++){
            if(Math.random() < mutationRate){
                int routePos2 = (int) (route.routeSize() * Math.random());

                Station station1 = route.getStation(routePos1);
                Station station2 = route.getStation(routePos2);

                route.setStation(routePos2, station1);
                route.setStation(routePos1, station2);
            }
        }
    }

    private static Route tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveRoute(i, pop.getRoute(randomId));
        }
        Route fittest = tournament.getFittest();
        return fittest;
    }
}

