package black_scholes_model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.math3.distribution.NormalDistribution;

public class B_SH_methods {
    
    String stockresults;

    // Calculate d1 with dividend yield q
    public double der1calc(double s0, double k, double t, double r, double v, double q) {
        return (Math.log(s0 / k) + (r - q + (v * v) / 2) * t) / (v * Math.sqrt(t));
    }

    // Calculate d2 (unchanged)
    public double der2calc(double d1, double t, double v) {
        return d1 - v * Math.sqrt(t);
    }

    // Standard normal cumulative distribution
    public double normaldist(double d) {
        NormalDistribution nd = new NormalDistribution();
        return nd.cumulativeProbability(d);
    }

    // Call option with dividend yield
    public double call(double s0, double d1, double d2, double K, double r, double t, double q) {
        return s0 * Math.exp(-q * t) * normaldist(d1) - K * Math.exp(-r * t) * normaldist(d2);
    }

    // Put option with dividend yield
    public double put(double s0, double d1, double d2, double K, double r, double t, double q) {
        return K * Math.exp(-r * t) * normaldist(-d2) - s0 * Math.exp(-q * t) * normaldist(-d1);
    }

    // Method to calculate volatility using Python script (unchanged)
    public void volatility_calc(String stockname) throws IOException, InterruptedException {
        String stockresults1 = null;
        ProcessBuilder pb = new ProcessBuilder("py", "yfin.py", stockname);
        pb.redirectErrorStream(true); 
        Process process = pb.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Python output: " + line);
            stockresults1 += line + "\n";
        }
        if(stockresults1.contains("null")) {
            stockresults = stockresults1.replace("null", "");
        }
        
        int exitCode = process.waitFor();
        System.out.println("Python exited with code " + exitCode);
    }
}