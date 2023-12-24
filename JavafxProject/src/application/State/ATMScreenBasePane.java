package application.State;

import application.MySQL_Connector;
import javafx.scene.Parent;

public abstract class ATMScreenBasePane{
	public abstract void EnterState(MySQL_Connector sqlconnector);
	public abstract void ExitState(MySQL_Connector sqlconnector);
	public abstract Parent get_page();
}
