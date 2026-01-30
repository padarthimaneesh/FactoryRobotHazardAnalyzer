import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer
 *
 * UC4 - Validation using conditional logic.
 * UC5 - Refactored validation and calculation into a separate method.
 *
 * This class validates user inputs and calculates hazard risk
 * only when inputs are valid.
 * Validation and hazard risk calculation are handled in a separate method
 * to keep main() minimal and readable.
 *
 * @Developer Maneesh
 * @version 5.0
 */
public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input collection
        System.out.println("Enter Arm precision (0.0 - 1.0): ");
        double armPrecision = sc.nextDouble();

        System.out.println("Enter Worker Density (1 - 20): ");
        int workerDensity = sc.nextInt();

        sc.nextLine(); // clear buffer

        System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
        String machineState = sc.nextLine();

        // Call refactored method
        double hazardRisk = calculateHazardRisk(
                armPrecision,
                workerDensity,
                machineState
        );

        // Display result
        if (hazardRisk != -1) {
            System.out.println("Hazard Risk Score: " + hazardRisk);
        } else {
            System.out.println("\nHazard risk calculation failed due to invalid inputs.");
        }

        sc.close();
    }

    /**
     * Validates inputs and calculates hazard risk.
     * Returns -1 if validation fails.
     */
    public static double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineState) {

        // Validation
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println("Error: Arm precision must be between 0.0 and 1.0.");
            return -1;
        }

        if (workerDensity < 1 || workerDensity > 20) {
            System.out.println("Error: Worker density must be between 1 and 20.");
            return -1;
        }

        if (!machineState.equalsIgnoreCase("Worn")
                && !machineState.equalsIgnoreCase("Faulty")
                && !machineState.equalsIgnoreCase("Critical")) {

            System.out.println("Error: Machinery state must be Worn, Faulty, or Critical.");
            return -1;
        }

        // Business logic
        double machineRiskFactor = getMachineRiskFactor(machineState);

        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }

    // Returns machine risk factor
    public static double getMachineRiskFactor(String machineState) {

        if (machineState.equalsIgnoreCase("Worn")) {
            return 1.3;
        } else if (machineState.equalsIgnoreCase("Faulty")) {
            return 2.0;
        } else { // Critical
            return 3.0;
        }
    }
}
