# CloudSim Business Intelligence Project

## Project Overview

This project is a CloudSim-based Business Intelligence and Financial Simulation System developed using Java. It combines cloud computing concepts with financial analysis to simulate business operations, predict outcomes, and evaluate decision-making strategies.

The main purpose of this project is to help understand how cloud environments can support financial planning, business intelligence, and risk management through simulation.

---

# Project Objective

The goal of this project is to create a system that can:

* Simulate financial activities in a cloud environment
* Analyze financial risks and business performance
* Predict future financial outcomes using AutoML logic
* Optimize budgeting strategies for better efficiency
* Compare multiple business scenarios
* Improve decision-making using simulation results

---

# Technologies Used

This project was built using:

* Java
* CloudSim Framework
* Object-Oriented Programming (OOP)
* Business Intelligence Concepts
* Financial Simulation Models
* AutoML-based Prediction Logic

---

# Project Modules

## 1. AutoML Predictor

The AutoML Predictor is responsible for analyzing data and generating predictions.

It performs tasks such as:

* Feature selection
* Model comparison
* Hyperparameter tuning
* Risk prediction
* Financial forecasting

### File:

`automl/AutoMLPredictor.java`

---

## 2. Budget Optimizer

The Budget Optimizer helps in planning and distributing financial resources effectively.

It helps to:

* Improve return on investment (ROI)
* Allocate budget efficiently
* Support business planning
* Optimize financial decisions

### File:

`budget/BudgetOptimizer.java`

---

## 3. Accounting Simulator

The Accounting Simulator tracks financial performance by comparing assets and liabilities.

It calculates financial position using the formula:

```text
F(t) = A(t) − L(t)
```

Where:

* `F(t)` = Financial Position
* `A(t)` = Assets
* `L(t)` = Liabilities

This module also simulates different business conditions such as optimistic, baseline, and adverse scenarios.

### File:

`accounting/AccountingSimulator.java`

---

## 4. Financial Risk Manager

The Financial Risk Manager evaluates business risk and uncertainty.

This module:

* Calculates financial risk levels
* Detects possible business threats
* Estimates risk exposure
* Supports mitigation planning

### File:

`risk/FinancialRiskManager.java`

---

## 5. CloudSim Core

CloudSim is used to simulate cloud infrastructure and manage events within the system.

It supports:

* Cloud environment simulation
* Resource allocation
* Event processing
* Workflow execution

### Files:

* `cloudsim/core/CloudSim.java`
* `cloudsim/core/SimEvent.java`

---

# Project Structure

```text
CloudSim-Business-intelligence/
│
├── accounting/
│   └── AccountingSimulator.java
│
├── automl/
│   └── AutoMLPredictor.java
│
├── budget/
│   └── BudgetOptimizer.java
│
├── cloudsim/
│   ├── FinancialBroker.java
│   └── core/
│       ├── CloudSim.java
│       └── SimEvent.java
│
├── main/
│   └── FinancialSimulation.java
│
├── models/
│   ├── FinancialDataset.java
│   └── SimulationResult.java
│
├── risk/
│   └── FinancialRiskManager.java
│
└── pom.xml
```

---

# How to Run the Project

## Step 1: Clone the Repository

```bash
git clone https://github.com/Manasa0925/CloudSim-Business-intelligence.git
```

---

## Step 2: Open the Project

You can open the project in:

* Visual Studio Code
* IntelliJ IDEA
* Eclipse

---

## Step 3: Compile the Project

```bash
javac -d out accounting/*.java automl/*.java budget/*.java cloudsim/core/*.java cloudsim/*.java main/*.java models/*.java risk/*.java
```

---

## Step 4: Run the Simulation

```bash
java -cp out main.FinancialSimulation
```

---

# Expected Output

After running the project, the system displays:

* Financial risk analysis
* Budget efficiency results
* AutoML prediction performance
* Accounting scenario comparison
* CloudSim simulation output

Example:
text
Validation MSE : 0.0198
Prediction Accuracy : 85%
Risk Reduction : 9%
Budget Efficiency Improvement : 16%

# Key Features

* Cloud-based simulation
* Financial forecasting
* Business intelligence analysis
* Risk management
* Budget optimization
* Scenario-based accounting simulation
* AutoML prediction support


# Future Improvements

Some possible future enhancements include:

* Adding real-world datasets
* Creating a dashboard interface
* Connecting to a database
* Deploying to cloud platforms
* Adding real-time analytics

# Author

**Manasa**

GitHub Repository:

[https://github.com/Manasa0925/CloudSim-Business-intelligence](https://github.com/Manasa0925/CloudSim-Business-intelligence)


# License

This project was created for educational and academic purposes.
