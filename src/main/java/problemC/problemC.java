package problemC;

import java.util.*;

public class problemC {
    private static List<RocketStage> rocketStages = new ArrayList<>();

    public int getTotalMass() {
        int mass = 0;
        for (RocketStage rocketStage : rocketStages) {
            if(!rocketStage.removed)
                mass += rocketStage.mass + rocketStage.fuel;
        }
        return mass;
    }

    public double getForceForStage(RocketStage rocketStage) {
        double force = rocketStage.thrust - getTotalMass()*9.8;
        return force > 0 ? force : 0;
    }

    public double getAcceleration(RocketStage rocketStage) {
        return getForceForStage(rocketStage)/getTotalMass();
    }

    public int calculateMaximumSpeed() {
        List<Double> accelerations = new ArrayList<>();

        for (RocketStage rocketStage : rocketStages) {
            while(rocketStage.fuel > 0) {
                accelerations.add(getAcceleration(rocketStage));
                rocketStage.fuel -= rocketStage.consumption;
            }
            rocketStage.removed = true;
        }
        double sum = 0;
        for (Double i : accelerations) {
            sum += i;
        }

        return (int)Math.round(sum);
    }

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStages = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numberOfStages; i++)
        {
            if(scanner.hasNextLine()) {
                String[] stage = scanner.nextLine().split(" ");
                rocketStages.add(new RocketStage(Integer.parseInt(stage[0]), Integer.parseInt(stage[1]), Integer.parseInt(stage[2]), Integer.parseInt(stage[3])));
                if(getTotalMass() > 10000) {
                    rocketStages.remove(rocketStages.size() - 1);
                    break;
                }
            }
        }
        Collections.reverse(rocketStages);
        scanner.close();
    }

    private static class RocketStage {
        int mass, fuel, thrust, consumption;
        boolean removed;

        public RocketStage(int mass, int fuel, int thrust, int consumption) {
            this.mass = mass;
            this.fuel = fuel;
            this.thrust = thrust;
            this.consumption = consumption;
            this.removed = false;
        }
    }

    public static void main(String[] args)
    {
        problemC c = new problemC();
        c.readInput();
        System.out.println(c.calculateMaximumSpeed());
    }

}
