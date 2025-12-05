Black-Scholes Option Pricing Model
A Java implementation of the Black-Scholes-Merton model for option pricing with a JavaFX graphical interface and Python integration for real-time stock data retrieval.
Overview
This project provides a comprehensive tool for pricing European options using the Black-Scholes model. It includes calculations for option Greeks (Delta, Gamma, Vega, Theta, Rho) and features historical stock data analysis from major German DAX companies.
Features

Black-Scholes Option Pricing: Calculate fair values for European call and put options
JavaFX GUI: User-friendly graphical interface for input and visualization
Real-Time Data Integration: Python script using yfinance to fetch current stock prices
Historical Data Analysis: Pre-loaded CSV files with historical data from German stocks

Technologies Used
Java

JavaFX: GUI framework for the application interface
Java I/O: For reading CSV files and communicating with Python scripts

Python

yfinance: Fetching real-time stock market data
pandas: Data manipulation and analysis
sys: System-specific parameters and functions
re: Regular expressions for data parsing

Project Structure
Black_Scholes_Model/
├── src/
│   └── black_scholes_model/
│       ├── Black_Scholes_Model.java    # Main JavaFX application
│       ├── B_SH_methods.java           # Black-Scholes calculations
│       └── JavaFX.java                 # GUI components
├── Referenced Libraries/
│   ├── yfin.py                         # Python script for data fetching
│   └── *.csv                           # Historical stock data files
└── README.md
Included Stock Data
Historical data (1-year) for major German companies:

ADYEN.AS, ALV.DE, BAS.DE (BASF)
BAYN.DE (Bayer), BMW.DE
DB (Deutsche Bank), DBK.DE (Deutsche Bank)
DTE.DE (Deutsche Telekom)
GMAB.CO, IFX.DE (Infineon)
MBG.DE (Mercedes-Benz), MC (LVMH)
ORCL (Oracle), RHM.DE (Rheinmetall)
RYAN (Ryanair), SAP, Sap.De (SAP)
Sie.de (Siemens), Spot (Spotify)
VOW.DE (Volkswagen)

Installation
Prerequisites
Java:

JDK 8 or higher
JavaFX SDK (if not included in your JDK)

Python:

Python 3.6+
Required packages:

bash  pip install yfinance pandas
Setup

Clone the repository:

bash   git clone https://github.com/enesgjana/Black-Scholes-Model.git
   cd Black-Scholes-Model

Import the project into your Java IDE (Eclipse, IntelliJ, etc.)
Ensure JavaFX libraries are properly configured in your IDE
Install Python dependencies if using the real-time data feature

Usage
Running the Application

Run the main JavaFX application:

java   // In your IDE, run Black_Scholes_Model.java or JavaFX.java

The GUI will prompt you to enter:

Stock price (S)
Strike price (K)
Time to maturity (T) in years
Risk-free interest rate (r)
Volatility (σ)


The application will calculate and display:

Call option price
Put option price



Using Python Integration
To fetch real-time stock data:
bashpython yfin.py <TICKER_SYMBOL>
Example:
bashpython yfin.py SAP.DE
Black-Scholes Formula
The Black-Scholes formula for a European call option:
C = S₀N(d₁) - Ke^(-rT)N(d₂)
For a European put option:
P = Ke^(-rT)N(-d₂) - S₀N(-d₁)
Where:

d₁ = [ln(S₀/K) + (r + σ²/2)T] / (σ√T)
d₂ = d₁ - σ√T
N(x) = cumulative standard normal distribution
S₀ = current stock price
K = strike price
r = risk-free interest rate
T = time to maturity
σ = volatility

Contributing
Contributions are welcome! Please feel free to submit a Pull Request.
License
This project is open source and available under the MIT License.
Author
Enes Gjana

GitHub: @enesgjana

Acknowledgments

Black-Scholes-Merton option pricing model
Historical stock data from Yahoo Finance
DAX company data for analysis

Disclaimer
This tool is for educational and research purposes only. It should not be used as the sole basis for investment decisions. Always consult with a qualified financial advisor before making investment decisions.