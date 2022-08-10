package com.grupo7;

import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.grupo7.FishingDerbyClone;
public class DesktopLauncher {
	public static void main (String[] arg) {
		Object selectedValue = null;
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			config.setTitle("FishingDerbyClone");
			config.setWindowedMode(800, 600);
			config.setResizable(false);
			config.setWindowIcon("assets/gameIcon.png");
			config.useVsync(true);
			config.setForegroundFPS(60);
			Object[] opcoes = { "1 Jogador - Plaver VS IA", "2 Jogadores - Plaver VS Player"};
      		selectedValue = JOptionPane.showInputDialog(null,
        	  "Escolha um modo de jogo", "Modo de Jogo",
        	      JOptionPane.INFORMATION_MESSAGE, null,
        	          opcoes, opcoes [0]); 
			new Lwjgl3Application(new FishingDerbyClone(selectedValue), config);
	}
}
