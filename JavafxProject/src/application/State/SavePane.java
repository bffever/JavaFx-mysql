package application.State;

import application.MySQL_Connector;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class SavePane extends ATMScreenBasePane{

	private VBox m_vbox;
	private TextField m_enter;
	private Button m_button;
	private Alert m_empty,m_balancenotEnough,m_success;
	
	
	SavePane(MySQL_Connector mysql)
	{
		m_vbox=new VBox();
		m_enter=new TextField();
		
		m_button=new Button("Save");
		m_enter.setPromptText("Enter Amount:");
		
		m_empty=new Alert(AlertType.INFORMATION,"Save Can't be done with empty amount");
		/*
		m_balancenotEnough=new Alert(AlertType.WARNING,"Your Current Balance is not enough for transaction");
		*/
		m_success=new Alert(AlertType.INFORMATION,"Transfer Success");
		
		m_vbox.getChildren().addAll(m_enter,m_button);
		
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, c -> 
		{
            if (c.getControlNewText().matches("\\d{0,6}")) {
                return c;
            } else {
                return null;
            }
        });
		
		m_enter.setTextFormatter(textFormatter);
		
		
		m_button.setOnAction(
		event->
			{
					if(Integer.parseInt(m_enter.getText())<=0)
					{
						m_empty.showAndWait();
					}
					else
					{
						int amount=Integer.parseInt(m_enter.getText());
						mysql.Update_Cash(amount);
						m_success.showAndWait();
						amount=0;
					}
			}
		);
		
		m_vbox.setAlignment(Pos.CENTER);
	}
	
	public void EnterState(MySQL_Connector sqlconnector) {

		
	}

	@Override
	public void ExitState(MySQL_Connector sqlconnector) {
		m_enter.clear();
		
	}

	@Override
	public Parent get_page() {
	
		return m_vbox;
	}

}
