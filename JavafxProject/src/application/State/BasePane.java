package application.State;

import application.SceneManager;

public abstract class BasePane
{
	public abstract void EnterState(SceneManager scenemanager);
	public abstract void ExitState(SceneManager scenemanager);
}
