package application.State;

import java.util.Dictionary;
import java.util.Hashtable;

import application.MySQL_Connector;
import application.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AtmScreenPane extends VBox{
	
	public enum Screen_State
	{
		e_Balance,e_Transfer,e_SaveCash
	};

	private Dictionary<Screen_State,ATMScreenBasePane> m_dictionary;
	private ATMScreenBasePane m_currentState;

	
	AtmScreenPane(MySQL_Connector mysql)
	{
		m_dictionary=new Hashtable<Screen_State,ATMScreenBasePane>();
		m_dictionary.put(Screen_State.e_Balance,new BalancePane());
		m_dictionary.put(Screen_State.e_SaveCash,new SavePane(mysql));
		m_dictionary.put(Screen_State.e_Transfer,new TransferPane(mysql));
		setAlignment(Pos.CENTER);
	}
	
	
	public void Change_State(Screen_State state,SceneManager scenemanager)
	{
		if(m_currentState!=null)
		{
			m_currentState.ExitState(scenemanager.getM_mysql_connector());
			getChildren().clear();
		}
		m_currentState=m_dictionary.get(state);
		getChildren().add(m_currentState.get_page());
		m_currentState.EnterState(scenemanager.getM_mysql_connector());
	}
	
	public void Clear_State(MySQL_Connector mysql)
	{
		if(m_currentState!=null)
		{m_currentState.ExitState(mysql);}
	}
}
