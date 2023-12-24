package application.State;

import application.MySQL_Connector;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BalancePane extends ATMScreenBasePane{

	private VBox m_vbox;
	private Label m_currentBalance;
	
	public BalancePane()
	{
		m_vbox=new VBox();
		m_currentBalance=new Label();
		m_vbox.getChildren().add(m_currentBalance);
		m_vbox.setAlignment(Pos.CENTER);
	}
	
	@Override
	public void EnterState(MySQL_Connector sqlconnector) 
	{
		m_currentBalance.setText("Current Balance:"+Integer.toString(sqlconnector.GetBalance()));
		
	}

	@Override
	public void ExitState(MySQL_Connector sqlconnector) 
	{
		m_currentBalance.setText("");
	}
	

	@Override
	public Parent get_page() {
		// TODO Auto-generated method stub
		return m_vbox;
	}


}
