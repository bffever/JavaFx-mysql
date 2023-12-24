package application.State;

import application.SceneManager;
import application.State.AtmScreenPane.Screen_State;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectionPane extends BasePane{
	HBox m_mainpane;
	VBox m_leftpane;
	VBox m_rightpane;
	AtmScreenPane m_centerpane;
	Button m_transfer;
	Button m_logout;
	Button m_savecash;
	Button m_viewbalance;
	
	public SelectionPane(SceneManager scenemanager)
	{
		m_mainpane=new HBox();
		m_leftpane=new VBox();
		m_rightpane=new VBox();
		m_centerpane=new AtmScreenPane(scenemanager.getM_mysql_connector());
		
		m_transfer=new Button("Transfer");
		m_logout=new Button("Log Out");
		m_savecash=new Button("Save Cash");
		m_viewbalance=new Button("View Balance ");
		
		m_leftpane.getChildren().addAll(m_transfer,m_logout);
		m_rightpane.getChildren().addAll(m_savecash,m_viewbalance);
	
		m_leftpane.setAlignment(Pos.CENTER);
		m_rightpane.setAlignment(Pos.CENTER);
		m_centerpane.setAlignment(Pos.CENTER);
		
		
		m_mainpane.getChildren().addAll(m_leftpane,m_centerpane,m_rightpane);
		HBox.setMargin(m_centerpane, new Insets(100,0,100,0));
		HBox.setMargin(m_leftpane, new Insets(100,0,100,0));
		HBox.setMargin(m_rightpane, new Insets(100,0,100,0));
		
		
		m_mainpane.getStyleClass().add("Selection-MainPane");
		m_leftpane.getStyleClass().add("Selection-LeftPane");
		m_rightpane.getStyleClass().add("Selection-RightPane");
		m_centerpane.getStyleClass().add("Selection-CenterPane");
		
		m_transfer.getStyleClass().add("Selection-Button");
		m_logout.getStyleClass().add("Selection-Button");
		m_savecash.getStyleClass().add("Selection-Button");
		m_viewbalance.getStyleClass().add("Selection-Button");
		
		m_savecash.setOnAction(event->{m_centerpane.Change_State(Screen_State.e_SaveCash, scenemanager);});
		m_viewbalance.setOnAction(event->{m_centerpane.Change_State(Screen_State.e_Balance, scenemanager);});
		m_transfer.setOnAction(event->{m_centerpane.Change_State(Screen_State.e_Transfer, scenemanager);});
		m_logout.setOnAction(event->{scenemanager.Change_State(Scene_State.e_Login); m_centerpane.Clear_State(scenemanager.getM_mysql_connector()); scenemanager.getM_mysql_connector().Clear();});
	}
	@Override
	public void EnterState(SceneManager scenemanager) {
		scenemanager.Change_ScenePane(m_mainpane);
	}

	@Override
	public void ExitState(SceneManager scenemanager) {
	
		
	}

}
