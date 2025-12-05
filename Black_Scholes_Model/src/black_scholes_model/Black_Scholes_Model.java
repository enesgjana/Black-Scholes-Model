package black_scholes_model;

import javafx.application.Application;

public class Black_Scholes_Model extends B_SH_methods{
	public void runApp() {
        // Launch the JavaFX application
        Application.launch(JavaFX.class);
    }

	public static void main(String[] args) {
        Black_Scholes_Model main = new Black_Scholes_Model();
        main.runApp();
    }

}

