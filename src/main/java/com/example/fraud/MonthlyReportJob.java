package com.example.fraud;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

import java.time.LocalDate;
import java.time.YearMonth;

public class MonthlyReportJob {
    public static void main(String[] args) {
        SparkSession spark = SparkSessionProvider.getSession();

        String firstDayOfMonth = LocalDate.now().withDayOfMonth(1).toString();
        String lastDay = LocalDate.now().toString();

        Dataset<Row> allDays = spark.read().json("hdfs://path/to/daily/transactions/")
                .filter(col("transactionDate").between(firstDayOfMonth, lastDay));

        Dataset<Row> frauds = FraudDetector.detectFraud(allDays);
        frauds.write().mode("overwrite").json("hdfs://output/monthly_report_" + YearMonth.now());

        spark.stop();
    }
}