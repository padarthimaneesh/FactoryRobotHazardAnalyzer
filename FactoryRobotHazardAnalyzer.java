import java.util.Scanner;

/**
 * Factory Robot Hazard Analyzer
 *
 * UC7 - Machinery State Risk Mapping using Enum
 * UC8 - Fully Modular & OOPS-Compliant Hazard Analyzer
 *
 * Demonstrates:
 * - Encapsulation
 * - Abstraction
 * - Single Responsibility Principle
 * - Exception-based validation
 * - Extensibility via Enum
 *
 * @Developer Maneesh
 * @version 8.0
 */

// -------------------- Custom Exception --------------------
class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}

// -------------------- UI Layer --------------------
public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Input collection
            System.out.print("Enter Arm Precision (0.0 - 1.0): ");
            double armPrecision = sc.nextDouble();

            System.out.print("Enter Worker Density (1 - 20): ");
            int workerDensity = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.print("Enter Machinery State (Worn/Faulty/Critical): ");
            String machineStateInput = sc.nextLine();

            // Business layer interaction
            RobotHazardAuditor auditor = new RobotHazardAuditor();

            double hazardRisk = auditor.calculateHazardRisk(
                    armPrecision,
                    workerDensity,
                    machineStateInput
            );

            // Display result
            System.out.println("\nHazard Risk Score: " + hazardRisk);

        } catch (RobotSafetyException e) {
            System.out.println("\nSafety Error: " + e.getMessage());
        }

        sc.close();
    }
}

// -------------------- Business Logic Layer --------------------
class RobotHazardAuditor {

    public double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineStateInput)
            throws RobotSafetyException {

        // Validation
        validateInputs(armPrecision, workerDensity);

        // Enum-based mapping
        MachineryState state =
                MachineryState.fromString(machineStateInput);

        double machineRiskFactor = state.getRiskFactor();

        // Hazard formula
        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }

    private void validateInputs(
            double armPrecision,
            int workerDensity)
            throws RobotSafetyException {

        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException(
                    "Arm precision must be between 0.0 and 1.0."
            );
        }

        if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException(
                    "Worker density must be between 1 and 20."
            );
        }
    }
}

// -------------------- Enum Layer --------------------
enum MachineryState {

    WORN(1.3),
    FAULTY(2.0),
    CRITICAL(3.0);

    private final double riskFactor;

    MachineryState(double riskFactor) {
        this.riskFactor = riskFactor;
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    // Convert user input string to enum safely
    public static MachineryState fromString(String state)
            throws RobotSafetyException {

        try {
            return MachineryState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RobotSafetyException(
                    "Unsupported machinery state: " + state +
                            ". Allowed values are Worn, Faulty, or Critical."
            );
        }
    }
}
