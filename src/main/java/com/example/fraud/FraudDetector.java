package com.example.fraud;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import static org.apache.spark.sql.functions.*;

public class FraudDetector {

    public static Dataset<Row> detectFraud(Dataset<Row> transactions) {
        return transactions.withColumn("fraudReason",
                when(not(col("kycVerified")), "Invalid KYC")
                .when(col("amount").gt(1000000), "High Amount")
                .when(not(col("country").equalTo("India")), "Foreign Transaction")
                .when(col("amount").mod(1000000).equalTo(0), "Round Amount Transaction")
                .when(hour(col("transactionDate").cast("timestamp")).lt(6), "Odd Hour Transaction")
                .when(col("customerId").isNull(), "Missing Customer ID")
                .otherwise(null)
        ).filter(col("fraudReason").isNotNull());
    }
}