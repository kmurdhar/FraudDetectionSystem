package com.example.fraud;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.time.LocalDate;

public class DailyReportJob {
    public static void main(String[] args) {
        SparkSession spark = SparkSessionProvider.getSession();

        Dataset<Row> transactions = spark.read().json("hdfs://path/to/daily/transactions/");
        Dataset<Row> frauds = FraudDetector.detectFraud(transactions);

        frauds.write().mode("overwrite").json("hdfs://output/daily_report_" + LocalDate.now());

        spark.stop();
    }
}