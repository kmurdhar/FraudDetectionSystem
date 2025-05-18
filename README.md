# 🚨 Fraud Detection System - Apache Spark with Java

A scalable Big Data project to detect fraudulent transactions using Apache Spark and Java, designed to process daily and monthly transaction reports from HDFS and flag suspicious activity.

## 📌 Features

- 🔍 Detects fraud based on:
  - Invalid or missing KYC
  - Transaction amount > ₹1,000,000
  - Foreign transactions (non-India)
  - Round number transactions (e.g., ₹1,000,000)
  - Odd hour transactions (e.g., 12AM–6AM)
- 📅 Generates:
  - **Daily Reports**: Every day
  - **Monthly Reports**: On the last day of the month
- ☁️ Reads input from **HDFS**
- 📝 Writes processed results back to **HDFS** (used later for PDF generation)
- ⚙️ Easily schedulable via `cron`, Apache Airflow, or Oozie

---

## 🧱 Project Structure
spark-fraud-report/
├── pom.xml
└── src/
└── main/
├── java/com/example/fraud/
│ ├── Transaction.java # POJO for transaction data
│ ├── SparkSessionProvider.java # Spark session initializer
│ ├── FraudDetector.java # Contains all fraud logic
│ ├── DailyReportJob.java # Daily job entry point
│ └── MonthlyReportJob.java # Monthly job entry point
└── resources/



---

## 🚀 How to Run

### ✅ Prerequisites

- Java 8+
- Apache Spark 3.x
- HDFS
- Maven

### 🔧 Build the Project

```bash
mvn clean install

 Run Daily Job
spark-submit --class com.example.fraud.DailyReportJob target/spark-fraud-report-1.0-SNAPSHOT.jar


Run Monthly Job
spark-submit --class com.example.fraud.MonthlyReportJob target/spark-fraud-report-1.0-SNAPSHOT.jar

📤 Input Format (JSON)
Each transaction record should look like:
{
  "transactionId": "TXN12345",
  "customerId": "CUST001",
  "amount": 1500000,
  "country": "Singapore",
  "kycVerified": false,
  "transactionDate": "2025-05-01T02:45:00"
}


📥 Output
Fraudulent transactions are written to HDFS with a fraudReason column explaining the flag.

 
{
  "transactionId": "TXN12345",
  "amount": 1500000,
  "fraudReason": "High Amount"
}

📅 Scheduling with Cron (Example)
Daily report at midnight:
0 0 * * * spark-submit --class com.example.fraud.DailyReportJob ...


Monthly report on last day:
59 23 28-31 * * [ "$(date +\\%d -d tomorrow)" == "01" ] && spark-submit --class com.example.fraud.MonthlyReportJob ...


👨‍💻 Author
Kamlesh Murdhariya
💼 Java | Big Data | Apache Spark | AWS
