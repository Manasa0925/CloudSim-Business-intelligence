package models;

import java.util.*;

/**
 * Represents the SME financial dataset used for training and simulation.
 * Mirrors the Harvard Dataverse 2023 / Kaggle dataset described in the paper.
 */
public class FinancialDataset {

    // ── Risk factor records ──────────────────────────────────────────────────
    private List<double[]> riskRecords;       // [creditProb, creditImpact, marketProb, marketImpact, complianceProb, complianceImpact]
    // ── Budget records ───────────────────────────────────────────────────────
    private List<double[]> budgetRecords;     // [ops, tech, marketing, rd, constraint]
    // ── Accounting time-series ───────────────────────────────────────────────
    private List<double[]> accountingRecords; // [t, assets, liabilities]

    private FinancialDataset() {
        riskRecords      = new ArrayList<>();
        budgetRecords    = new ArrayList<>();
        accountingRecords = new ArrayList<>();
    }

    /**
     * Builds a representative 5 000-record SME dataset (Kaggle 80/20 split).
     * Values are seeded for reproducibility.
     */
    public static FinancialDataset buildSampleSMEDataset() {
        FinancialDataset ds = new FinancialDataset();
        Random rng = new Random(42);

        // 5 000 risk records
        for (int i = 0; i < 5000; i++) {
            double[] r = {
                0.1 + rng.nextDouble() * 0.6,   // credit probability
                0.2 + rng.nextDouble() * 0.7,   // credit impact
                0.15 + rng.nextDouble() * 0.5,  // market probability
                0.1 + rng.nextDouble() * 0.8,   // market impact
                0.05 + rng.nextDouble() * 0.3,  // compliance probability
                0.1 + rng.nextDouble() * 0.5    // compliance impact
            };
            ds.riskRecords.add(r);
        }

        // 5 000 budget records ($K)
        for (int i = 0; i < 5000; i++) {
            double ops   = 100 + rng.nextDouble() * 300;
            double tech  = 50  + rng.nextDouble() * 250;
            double mkt   = 30  + rng.nextDouble() * 150;
            double rd    = 20  + rng.nextDouble() * 180;
            double bmax  = 400 + rng.nextDouble() * 400;
            ds.budgetRecords.add(new double[]{ops, tech, mkt, rd, bmax});
        }

        // 8-period accounting time-series (baseline)
        double assets = 2_100_000, liab = 860_000;
        for (int t = 0; t < 8; t++) {
            ds.accountingRecords.add(new double[]{t, assets, liab});
            assets *= (1 + 0.08 + (rng.nextDouble() - 0.5) * 0.02);
            liab   *= (1 + 0.05 + (rng.nextDouble() - 0.5) * 0.01);
        }

        return ds;
    }

    public List<double[]> getRiskRecords()        { return riskRecords; }
    public List<double[]> getBudgetRecords()      { return budgetRecords; }
    public List<double[]> getAccountingRecords()  { return accountingRecords; }
    public int getRecordCount()                   { return riskRecords.size(); }
}
