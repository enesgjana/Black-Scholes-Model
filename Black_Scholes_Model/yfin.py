import yfinance as yf
import pandas as pd
import sys
import re

ticker = sys.argv[1]         

has_number = bool(re.search(r'\d', ticker))
if has_number == True:
	print("Incorrect stock name entered. Try again!")
	sys.exit()

ticker1_symbol = sys.argv[1]
current_price = yf.Ticker(ticker1_symbol).history(period="1d")["Close"].iloc[-1]

print(f"Current price of {ticker1_symbol}: {current_price}")

df = yf.download(ticker, period="1y", interval="1d", auto_adjust=False, progress=False)
if df.empty:
    raise RuntimeError("No data returned. Check the ticker or your internet connection.")

df = df.reset_index()
df.to_csv(f"{ticker}_1y.csv", index=False)
#print(f"Saved {ticker}_1y.csv with {len(df)} rows")

# Read CSV
df = pd.read_csv(f"{ticker}_1y.csv", parse_dates=["Date"])
df["Adj Close"] = pd.to_numeric(df["Adj Close"], errors="coerce")
df = df.dropna(subset=["Adj Close"])

# Set Date as index
df.set_index("Date", inplace=True)

# Daily volatility
df["Return"] = df["Adj Close"].pct_change()
df = df.dropna(subset=["Return"])
daily_vol = df["Return"].std()
annual_vol = daily_vol * (252 ** 0.5)

# Weekly volatility
weekly_prices = df["Adj Close"].resample("W-FRI").last()
weekly_returns = weekly_prices.pct_change().dropna()
weekly_vol = weekly_returns.std()
annual_weekly_vol = weekly_vol * (52 ** 0.5)

# Monthly volatility
monthly_prices = df["Adj Close"].resample("ME").last()
monthly_returns = monthly_prices.pct_change().dropna()
monthly_vol = monthly_returns.std()
annual_monthly_vol = monthly_vol * (12 ** 0.5)

#Divident Yield

stock = yf.Ticker(ticker)

dividend_yield = stock.info.get('dividendYield')
if dividend_yield is not None:
    dividend_yield_percent = dividend_yield
else:
    print("Dividend yield not available")

# Print results
#print(f"Current price: {current_price}")
print(f"Daily volatility: {daily_vol:.2%}")
print(f"Annualized volatility: {annual_vol:.2%}")
print(f"Weekly annualized volatility: {annual_weekly_vol:.2%}")
print(f"Monthly annualized volatility: {annual_monthly_vol:.2%}")
print(f"Dividend yield : {dividend_yield_percent}"+"%")