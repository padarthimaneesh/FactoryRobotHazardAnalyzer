import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer
 *
 * UC1 - Printing message using print statement
 * UC3 - Calculating the hazard risk.
 *
 * This class takes input from the user, calculates the hazard risk
 * using the given formula, and displays the risk score.
 *
 * @Developer Maneesh
 * @version 3.0
 */

public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {

        // Display system name
        System.out.println("Factory Robot Hazard Analyzer");

        Scanner sc = new Scanner(System.in);

        // Taking Arm Precision value from user
        System.out.println("Enter Arm precision (0.0 - 1.0): ");
        double armPrecision = sc.nextDouble();

        // Taking Worker Density value from user
        System.out.println("Enter Worker Density (1 - 20): ");
        int workerDensity = sc.nextInt();

        sc.nextLine(); // clear buffer

        // Taking Machinery State value from user
        System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
        String machineState = sc.nextLine();

        // Echo inputs
        System.out.println("Arm Precision   : " + armPrecision);
        System.out.println("Worker Density  : " + workerDensity);
        System.out.println("Machinery State : " + machineState);

        // Get machine risk factor
        double machineRiskFactor = getMachineRiskFactor(machineState);

        // Calculate hazard risk
        double hazardRisk = calculateHazardRisk(
                armPrecision,
                workerDensity,
                machineRiskFactor
        );

        // Display hazard risk score
        System.out.println("Hazard Risk Score: " + hazardRisk);

        sc.close();
    }

    // Method to return machine risk factor (assume valid input)
    public static double getMachineRiskFactor(String machineState) {

        if (machineState.equalsIgnoreCase("Worn")) {
            return 1.3;
        } else if (machineState.equalsIgnoreCase("Faulty")) {
            return 2.0;
        } else { // Critical
            return 3.0;
        }
    }

    // Method to calculate hazard risk
    public static double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            double machineRiskFactor) {

        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}
