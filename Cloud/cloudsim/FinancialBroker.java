package cloudsim;

import cloudsim.core.CloudSim;
import cloudsim.core.SimEvent;
import java.util.*;

/**
 * Financial Workload Broker — extends CloudSim's DatacenterBroker concept.
 *
 * Maps financial simulation tasks (risk computation, budget LP, accounting
 * time-series) onto CloudSim cloudlets and virtual machines.
 *
 * Each financial module submits "cloudlets" (computational tasks) to the
 * broker, which schedules them on VMs in the simulated data centre.
 */
public class FinancialBroker {

    private final String name;
    private final List<FinancialCloudlet> cloudlets = new ArrayList<>();
    private int cloudletIdCounter = 0;

    // Simulated VM pool
    private static final int NUM_VMS   = 4;
    private static final int MIPS_PER_VM = 1000;

    public FinancialBroker(String name) {
        this.name = name;
        System.out.println("[FinancialBroker] " + name + " initialized with " + NUM_VMS + " VMs @ " + MIPS_PER_VM + " MIPS each.");
    }

    // ── Cloudlet submission ───────────────────────────────────────────────────

    public FinancialCloudlet submitRiskCloudlet(int numRecords) {
        FinancialCloudlet cl = new FinancialCloudlet(
                cloudletIdCounter++,
                "RISK_ANALYSIS",
                (long) numRecords * 500,   // MI: 500 instructions per record
                1
        );
        cloudlets.add(cl);
        System.out.printf("[Broker] Submitted cloudlet #%d (RISK_ANALYSIS, %d MI)%n",
                cl.getCloudletId(), cl.getLength());
        return cl;
    }

    public FinancialCloudlet submitBudgetCloudlet(int iterations) {
        FinancialCloudlet cl = new FinancialCloudlet(
                cloudletIdCounter++,
                "BUDGET_OPTIMIZATION",
                (long) iterations * 2000,
                2
        );
        cloudlets.add(cl);
        System.out.printf("[Broker] Submitted cloudlet #%d (BUDGET_OPTIMIZATION, %d MI)%n",
                cl.getCloudletId(), cl.getLength());
        return cl;
    }

    public FinancialCloudlet submitAccountingCloudlet(int periods, int scenarios) {
        FinancialCloudlet cl = new FinancialCloudlet(
                cloudletIdCounter++,
                "ACCOUNTING_SIMULATION",
                (long) periods * scenarios * 1500,
                1
        );
        cloudlets.add(cl);
        System.out.printf("[Broker] Submitted cloudlet #%d (ACCOUNTING_SIMULATION, %d MI)%n",
                cl.getCloudletId(), cl.getLength());
        return cl;
    }

    // ── Scheduling ────────────────────────────────────────────────────────────

    public void scheduleAndExecute() {
        System.out.println("\n[FinancialBroker] Scheduling " + cloudlets.size() + " cloudlets on " + NUM_VMS + " VMs...");
        System.out.println("  Cloudlet  Task                  Assigned VM  Est. Time (s)  Status");
        System.out.println("  ────────  ────────────────────  ───────────  ─────────────  ──────");

        int vmIdx = 0;
        for (FinancialCloudlet cl : cloudlets) {
            int vmId   = vmIdx % NUM_VMS;
            double eta = (double) cl.getLength() / (MIPS_PER_VM * cl.getNumPes());
            cl.setStatus("FINISHED");
            cl.setVmId(vmId);
            cl.setActualTime(eta);
            System.out.printf("  #%-8d %-20s  VM-%d         %.4f           %s%n",
                    cl.getCloudletId(), cl.getTaskType(), vmId, eta, cl.getStatus());
            vmIdx++;
        }

        double totalTime = cloudlets.stream().mapToDouble(FinancialCloudlet::getActualTime).max().orElse(0);
        System.out.printf("%n[Broker] All cloudlets finished. Max wall-clock time: %.4f s%n", totalTime);
    }

    public List<FinancialCloudlet> getCloudlets() { return cloudlets; }

    // ── Inner cloudlet class ──────────────────────────────────────────────────

    public static class FinancialCloudlet {
        private final int    cloudletId;
        private final String taskType;
        private final long   length;    // MI (million instructions)
        private final int    numPes;
        private String status     = "CREATED";
        private int    vmId       = -1;
        private double actualTime = 0;

        public FinancialCloudlet(int id, String taskType, long length, int numPes) {
            this.cloudletId = id; this.taskType = taskType;
            this.length = length; this.numPes = numPes;
        }

        public int    getCloudletId()  { return cloudletId; }
        public String getTaskType()    { return taskType; }
        public long   getLength()      { return length; }
        public int    getNumPes()      { return numPes; }
        public String getStatus()      { return status; }
        public double getActualTime()  { return actualTime; }
        public void setStatus(String s) { status = s; }
        public void setVmId(int v)      { vmId = v; }
        public void setActualTime(double t) { actualTime = t; }
    }
}
