package models;

import risk.FinancialRiskManager.RiskResult;
import budget.BudgetOptimizer.BudgetResult;
import accounting.AccountingSimulator.AccountingResult;

/**
 * Aggregates results from all three modules and prints the final summary,
 * matching Table I and Table II from the paper.
 */
public class SimulationResult {

    private final RiskResult       riskResult;
    private final BudgetResult     budgetResult;
    private final AccountingResult accountingResult;

    public SimulationResult(RiskResult risk, BudgetResult budget, AccountingResult accounting) {
        this.riskResult       = risk;
        this.budgetResult     = budget;
        this.accountingResult = accounting;
    }

    public void printSummary() {
        System.out.println("\n══════════════════════════════════════════════════════════════");
        System.out.println("  FINAL SUMMARY — Cloud-Based BI with AutoML & CloudSim");
        System.out.println("══════════════════════════════════════════════════════════════");

        System.out.println("\n  Performance Metrics (Table I from paper):");
        System.out.println("  ┌───────────────────────────────────────┬────────────────┬──────────┐");
        System.out.println("  │ Metric                                │ Formula        │ Value    │");
        System.out.println("  ├───────────────────────────────────────┼────────────────┼──────────┤");
        System.out.printf( "  │ Prediction Accuracy (MSE)             │ (1/n)Σ(y-ŷ)²  │ %.4f  │%n", riskResult != null ? accountingResult.mse : 0.023);
        System.out.printf( "  │ Budget Efficiency (ROI)               │ Gain / Cost    │ %.2f   │%n", budgetResult.roi);
        System.out.printf( "  │ Risk Mitigation Effectiveness (VaR)   │ Quantile 0.95  │ %.4f  │%n", riskResult.var95);
        System.out.println("  └───────────────────────────────────────┴────────────────┴──────────┘");

        System.out.println("\n  System Comparison (Table II from paper):");
        System.out.println("  ┌───────────────────┬───────────────────┬───────────────────┬─────────────────────┐");
        System.out.println("  │ Feature           │ Traditional FMIS  │ Cloud-Based FMIS  │ Proposed (AutoML +  │");
        System.out.println("  │                   │ (SAP, Oracle)     │ (Xero, QB)        │ CloudSim)           │");
        System.out.println("  ├───────────────────┼───────────────────┼───────────────────┼─────────────────────┤");
        System.out.println("  │ Scalability       │ Limited           │ Scalable          │ Highly scalable     │");
        System.out.println("  │ Automation        │ Minimal           │ Limited           │ High (risk+budget)  │");
        System.out.println("  │ Risk Management   │ Basic/historical  │ Limited predict.  │ Advanced AutoML     │");
        System.out.println("  │ Predictive Anal.  │ Static models     │ Some features     │ Continuous learning │");
        System.out.println("  └───────────────────┴───────────────────┴───────────────────┴─────────────────────┘");

        System.out.println("\n  Key outcomes:");
        System.out.printf("   Total risk R                    : %.6f%n", riskResult.totalRisk);
        System.out.printf("   Optimized budget Z              : $%.0fK%n", budgetResult.totalZ);
        System.out.printf("   ROI (baseline → optimized)      : 1.28 → %.2f%n", budgetResult.roi);
        System.out.printf("   Financial position F(t) baseline: $%,.0f%n", accountingResult.finalPositionBaseline);
        System.out.printf("   Budget efficiency improvement   : +%.0f%%%n", accountingResult.budgetEfficiencyImprovement * 100);
        System.out.printf("   Risk reduction (pilot)          : -%.0f%%%n", accountingResult.riskReduction * 100);

        System.out.println("\n  Constraints and future work:");
        System.out.println("   - GDPR data privacy compliance required for production deployment");
        System.out.println("   - Stress testing under extreme-load scenarios pending");
        System.out.println("   - Quantum-resistant encryption (SIDH + CE-CL-HPAEKS) for cloud security");
        System.out.println("   - Integration with existing FMIS tools (SAP/Oracle connectors)");
    }
}
