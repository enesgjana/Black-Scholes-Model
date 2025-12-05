package black_scholes_model;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFX extends Application{
    @Override
    public void start(Stage stage) {
    	Label myLabel = new Label("Black-Scholes Calculator."); 
    	myLabel.setFont(new Font("Georgia", 24));
    	
    	Label price1 = new Label("Enter the current stock price.");
        TextField price = new TextField();
        
        Label sprice = new Label("Enter the strike price:");
        TextField sprice1 = new TextField();
       
        Label time = new Label("Enter the time(years):");
        TextField time1 = new TextField();
        
        Label irate = new Label("Enter the interest rate(%):");
        TextField irate1 = new TextField();
        
        Label vol = new Label("Enter the volatility(%):");
        TextField vol1 = new TextField();
        
        Label div = new Label("Enter the divident yield(%):");
        TextField div1 = new TextField();
    	
        Label resultLabel = new Label();
        resultLabel.setFont(new Font("Georgia", 13));
        Label resultLabel1 = new Label();
        resultLabel1.setFont(new Font("Georgia", 13));
        
        Button calculateBtn = new Button("Calculate"); 
        
        calculateBtn.setOnAction(e -> {
            try {
            	Black_Scholes_Model Model1 = new Black_Scholes_Model();

        		double d1 = Model1.der1calc(Double.parseDouble(price.getText()), Double.parseDouble(sprice1.getText()), Double.parseDouble(time1.getText()), Double.parseDouble(irate1.getText())/100, Double.parseDouble(vol1.getText())/100, Double.parseDouble(div1.getText())/100);
        		double d2 = Model1.der2calc(d1, Double.parseDouble(time1.getText()), Double.parseDouble(vol1.getText())/100);
        		
        		double call_price = Model1.call(Double.parseDouble(price.getText()), d1, d2, Double.parseDouble(sprice1.getText()), Double.parseDouble(irate1.getText())/100, Double.parseDouble(time1.getText()), Double.parseDouble(div1.getText())/100);
        		
        		
        		double put_price = Model1.put(Double.parseDouble(price.getText()), d1, d2, Double.parseDouble(sprice1.getText()), Double.parseDouble(irate1.getText())/100, Double.parseDouble(time1.getText()), Double.parseDouble(div1.getText())/100);
                resultLabel.setText("Call price: " + call_price + "  Call option probability in-the-money: "+ Model1.normaldist(d2)*100+"%");
                resultLabel1.setText("Put price: "+ put_price + "  Put option probability in-the-money: "+Model1.normaldist(-d2)*100+"%");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers in all fields!");
            }
        });
        
        Button multisim = new Button("Multiple Models");
        
        multisim.setOnAction(e -> {
        	Stage secondStage = new Stage();
            VBox root1 = new VBox(15);
            root1.setAlignment(Pos.BASELINE_LEFT);
            root1.setStyle("-fx-background-color: lightblue;");

            Label[] mLabel= new Label[10];
            for(int i = 0; i < mLabel.length; i++) {
            	mLabel[i] = new Label();
            	mLabel[i].setFont(new Font("Georgia", 13));
            	try {
                	Black_Scholes_Model Model1 = new Black_Scholes_Model();

            		double d1 = Model1.der1calc(Double.parseDouble(price.getText()), Double.parseDouble(sprice1.getText())-5+i, Double.parseDouble(time1.getText()), Double.parseDouble(irate1.getText())/100, Double.parseDouble(vol1.getText())/100, Double.parseDouble(div1.getText())/100);
            		double d2 = Model1.der2calc(d1, Double.parseDouble(time1.getText()), Double.parseDouble(vol1.getText())/100);
            		
            		double call_price = Model1.call(Double.parseDouble(price.getText()), d1, d2, Double.parseDouble(sprice1.getText())-5+i, Double.parseDouble(irate1.getText())/100, Double.parseDouble(time1.getText()), Double.parseDouble(div1.getText())/100);
            		
            		
            		double put_price = Model1.put(Double.parseDouble(price.getText()), d1, d2, Double.parseDouble(sprice1.getText())-5+i, Double.parseDouble(irate1.getText())/100, Double.parseDouble(time1.getText()), Double.parseDouble(div1.getText())/100);
            		mLabel[i].setText("Strike price: "+ (Double.parseDouble(sprice1.getText())-5+i) +" Call price: " + call_price + "  Call option probability in-the-money: "+ Model1.normaldist(d2)*100+"%"+"  Put price: "+ put_price + "  Put option probability in-the-money: "+Model1.normaldist(-d2)*100+"%");
                } catch (NumberFormatException ex) {
                	mLabel[i].setText("Go back and enter valid numbers!");
                }
            	root1.getChildren().add(mLabel[i]);
          
            }
            

            Scene scene = new Scene(root1, 1200, 400);
            secondStage.setTitle("Multiple Models");
            secondStage.setScene(scene);
            secondStage.show();
        });
        
        Label volintro = new Label("Volatility Calculator");
        volintro.setFont(new Font("Georgia", 18));
        Label volLabel = new Label("Enter the stock name:");
        TextField stockname = new TextField();
        Label stockresultlabel = new Label();
        Button calcvol = new Button("Calculate volatility");
        
        calcvol.setOnAction(e -> {
        	Black_Scholes_Model Model2 = new Black_Scholes_Model();
        	try {
				Model2.volatility_calc(stockname.getText());
				stockresultlabel.setText(Model2.stockresults);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            });
        
        VBox root = new VBox(10); 
        root.setStyle("-fx-background-color: lightblue;");
        root.getChildren().add(myLabel);
        root.getChildren().add(price1); 
        root.getChildren().add(price); 
        root.getChildren().add(sprice);
        root.getChildren().add(sprice1);
        root.getChildren().add(time);
        root.getChildren().add(time1);
        root.getChildren().add(irate);
        root.getChildren().add(irate1);
        root.getChildren().add(vol);
        root.getChildren().add(vol1);
        root.getChildren().add(div);
        root.getChildren().add(div1);
        root.getChildren().add(resultLabel);
        root.getChildren().add(resultLabel1);
        root.getChildren().add(calculateBtn);
        root.getChildren().add(multisim);
        root.getChildren().add(volintro);
        root.getChildren().add(volLabel);
        root.getChildren().add(stockname);
        root.getChildren().add(stockresultlabel);
        root.getChildren().add(calcvol);
        
        Scene scene = new Scene(root, 700, 800); 
        stage.setTitle("Black-Scholes Model Calculator");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}