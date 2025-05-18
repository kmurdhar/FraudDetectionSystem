package com.example.fraud;

import org.apache.spark.sql.SparkSession;

public class SparkSessionProvider {
    public static SparkSession getSession() {
        return SparkSession.builder()
            .appName("Fraud Detection Report")
            .master("local[*]") // Use 'yarn' for production
            .getOrCreate();
    }
}