package application.State;



import application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class LoginPane extends BasePane{

	BorderPane m_borderpane;
	VBox m_vbox;
	Label m_title;
	PasswordField m_password;
	Button m_loginButton;
	Alert m_emptyAlert,m_wrongPassword;
	
	public LoginPane(SceneManager scenemanager)
	{
		
		m_borderpane=new BorderPane();
		m_vbox=new VBox();
		m_title=new Label();
		m_password=new PasswordField();
		m_loginButton=new Button("Login");
		m_emptyAlert=new Alert(AlertType.ERROR,"Password can't be empty");
		m_wrongPassword=new Alert(AlertType.INFORMATION,"Password is wrong please enter again!");
		
		m_title.setText("Login");
		m_password.setPromptText("Enter Password:");
		
		m_vbox.getChildren().addAll(m_title,m_password,m_loginButton);
		m_borderpane.setCenter(m_vbox);
		
		m_borderpane.getStyleClass().add("Login-BorderPane");
		m_vbox.getStyleClass().add("Login-VBox");
		m_title.getStyleClass().add("Login-Label");
		m_password.getStyleClass().add("Login-PasswordField");
		m_loginButton.getStyleClass().add("Login-Button");
		
		VBox.setMargin(m_password, new Insets(20, 0, 20, 0));
		m_vbox.setAlignment(Pos.CENTER);
		
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, c -> 
		{
            if (c.getControlNewText().matches("\\d{0,6}")) {
                return c;
            } else {
                return null;
            }
        });
		
		m_password.setTextFormatter(textFormatter);
		
		m_loginButton.setOnAction(Login_Function(scenemanager));
	}
	
	
	public void EnterState(SceneManager scenemanager) 
	{
		scenemanager.Change_ScenePane(m_borderpane);
	}


	public void ExitState(SceneManager scenemanager) 
	{
		m_password.clear();
	}
	
	EventHandler<ActionEvent> Login_Function(SceneManager scenemanager)
	{
		EventHandler<ActionEvent> login_function=new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0) 
			{
				if(!m_password.getText().isEmpty())
				{
					if(scenemanager.getM_mysql_connector().LoginCheck(m_password.getText()))
					{
						scenemanager.Change_State(Scene_State.e_Selection);
					}
					else
					{
						m_password.clear();
						m_wrongPassword.showAndWait();
					}
				}else
				{
					m_emptyAlert.showAndWait();
				}
			}
		};
		
		return login_function;
	}

}
