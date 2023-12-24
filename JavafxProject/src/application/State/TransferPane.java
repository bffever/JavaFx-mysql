package application.State;

import application.MySQL_Connector;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class TransferPane extends ATMScreenBasePane {
	
	private VBox m_vbox;
	private TextField m_account,m_cashAmount;
	private Button m_transfer;
	private Alert m_empty,m_balancenotEnough,m_success,m_cant_transferaccount;

	TransferPane(MySQL_Connector mysql)
	{
		m_vbox=new VBox();
		m_account=new TextField();
		m_cashAmount=new TextField();
		m_transfer=new Button("Transfer");
		
		m_account.setPromptText("Enter Transfer Account:");
		m_account.setPromptText("Enter Transfer Amount:");
		
		m_empty=new Alert(AlertType.INFORMATION,"Transfer Can't be done with empty amount");
		
		m_balancenotEnough=new Alert(AlertType.WARNING,"Your Current Balance is not enough for transaction");
		
		m_success=new Alert(AlertType.INFORMATION,"Transfer Success");
		
		m_cant_transferaccount=new Alert(AlertType.INFORMATION,"Cant find transfer user account please enter again");
		
		m_vbox.getChildren().addAll(m_account,m_cashAmount,m_transfer);
		m_vbox.setAlignment(Pos.CENTER);
		
		m_transfer.setOnAction(event->
		{
			boolean account_found=mysql.Get_TransferAccount(m_account.getText());
			int amount=Integer.parseInt(m_cashAmount.getText());
			if(account_found)
			{
				if(amount<=mysql.GetBalance())
				{
					mysql.Transfer_Money(Integer.toString(amount));
					
				}else
				{
					m_balancenotEnough.showAndWait();
				}
			}else
			{
				m_cant_transferaccount.showAndWait();
			}
		});
	}
	@Override
	public void EnterState(MySQL_Connector sqlconnector) {

	}

	@Override
	public void ExitState(MySQL_Connector sqlconnector) {
		m_account.clear();
		m_cashAmount.clear();
		
	}

	@Override
	public Parent get_page() {
		return m_vbox;
	}

}
