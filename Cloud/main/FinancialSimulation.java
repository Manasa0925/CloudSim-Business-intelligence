package main;

import cloudsim.*;
import cloudsim.core.CloudSim;
import risk.FinancialRiskManager;
import budget.BudgetOptimizer;
import accounting.AccountingSimulator;
import automl.AutoMLPredictor;
import models.FinancialDataset;
import models.SimulationResult;

import java.util.*;

/**
 * Cloud-Based Business Intelligence with AutoML
 * Financial Risk Management, Budget Optimization, and Accounting Simulations
 * Using CloudSim — based on IEEE ACROSET 2025 paper.
 *
 * Architecture:
 *   Input Data (Financial Data, Risk Factors, Budget Constraints)
 *       -> Cloud-Based BI (AutoML + CloudSim)
 *           -> Output (Optimized Budget, Simulated Financial Scenarios, Risk Analysis)
 */
public class FinancialSimulation {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("  Cloud-Based BI with AutoML & CloudSim");
        System.out.println("  Financial Risk Management, Budget Optimization & Accounting");
        System.out.println("=============================================================\n");

        // ── 1. Initialize CloudSim ──────────────────────────────────────────
        int numUsers = 1;
        Calendar calendar = Calendar.getInstance();
        boolean traceFlag = false;
        CloudSim.init(numUsers, calendar, traceFlag);

        // ── 2. Build financial dataset (Harvard Dataverse / Kaggle SME data) ─
        FinancialDataset dataset = FinancialDataset.buildSampleSMEDataset();
        System.out.println("[Dataset] Loaded " + dataset.getRecordCount() + " SME financial records.\n");

        // ── 3. AutoML Predictor — Feature selection + model training ─────────
        AutoMLPredictor automl = new AutoMLPredictor();
        automl.trainModels(dataset);

        // ── 4. Financial Risk Manager — R = Σ P_i × I_i ──────────────────────
        FinancialRiskManager riskManager = new FinancialRiskManager(automl);
        riskManager.analyzeRisks(dataset);

        // ── 5. Budget Optimizer — maximize Z = Σ x_i · c_i ───────────────────
        BudgetOptimizer budgetOptimizer = new BudgetOptimizer(automl);
        budgetOptimizer.optimize(dataset);

        // ── 6. Accounting Simulator — F(t) = A(t) − L(t) ─────────────────────
        AccountingSimulator accountingSim = new AccountingSimulator(automl);
        accountingSim.runAllScenarios(dataset);

        // ── 7. Aggregate & report results ─────────────────────────────────────
        SimulationResult result = new SimulationResult(
                riskManager.getResult(),
                budgetOptimizer.getResult(),
                accountingSim.getResult()
        );
        result.printSummary();

        System.out.println("\n[CloudSim] Simulation complete.");
        CloudSim.stopSimulation();
    }
}
