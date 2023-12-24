package application;

import java.util.Dictionary;
import java.util.Hashtable;
import application.State.BasePane;
import application.State.LoginPane;
import application.State.Scene_State;
import application.State.SelectionPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class SceneManager 
{
	Stage m_stage;
	Scene m_scene;
	Dictionary<Scene_State, BasePane> m_dictionary;
	BasePane m_currentPane;
	Pane temporary_whitePane;
	private MySQL_Connector m_mysql_connector;

	
	SceneManager()
	{
		temporary_whitePane=new Pane();
		setM_mysql_connector(new MySQL_Connector());
		m_stage=new Stage();
		m_scene=new Scene(temporary_whitePane,800,600,Color.ALICEBLUE);
		m_dictionary=new Hashtable<Scene_State, BasePane>();
		m_dictionary.put(Scene_State.e_Login,new LoginPane(this));
		m_dictionary.put(Scene_State.e_Selection,new SelectionPane(this));

		m_stage.setScene(m_scene);
		m_stage.show();
		
		m_scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		m_stage.setTitle("Assignment");
		m_stage.setResizable(false);
		
		Change_State(Scene_State.e_Login);
	}
	
	public void Change_State(Scene_State state)
	{
		if(m_currentPane!=null)
		{
			m_currentPane.ExitState(this);
		}
		m_currentPane=m_dictionary.get(state);
		m_currentPane.EnterState(this);
		
	}
	
	public void Change_ScenePane(Parent pane)
	{
		m_scene.setRoot(pane);
	}

	public MySQL_Connector getM_mysql_connector() {
		return m_mysql_connector;
	}

	public void setM_mysql_connector(MySQL_Connector m_mysql_connector) {
		this.m_mysql_connector = m_mysql_connector;
	}
}
