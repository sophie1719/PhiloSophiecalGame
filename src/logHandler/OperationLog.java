package logHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class OperationLog {
	private String logText;
	
	// normal constructors
	public OperationLog(String logText, String logFileName) {
		this.logText = logText;
		this.printoutLog();
		this.logToFile(logFileName);
	}
	
	// polymorphic constructor for writing the logs without doubling into the console
	public OperationLog(String logText, String logFileName, boolean showInConsole) {
		if (showInConsole) {
			this.logText = logText;
			this.printoutLog();
			this.logToFile(logFileName);
		} else {
			this.logText = logText;
			this.logToFile(logFileName);
		}
	}
	
	// static log handling methods that are used in the main
	
	public static void setupLogs(Player player, int i, String logFileName) {
			String logText  = "  PLAYER #" + Integer.toString(i+1) + "\n"
							+ "  Nickname: " + player.getNickname() + "\n"
							+ "  Hero:     " + player.getHero().toString() + "\n" 
							+ "  Weapon:   " + player.getWeapon().toString() + "\n"
							+ "  Shield:   " + player.getShield().toString() + "\n";
			String fancyText = player.getNickname() + " is playing for " 
							 + player.getHero().toString() + " that attacks with " 
							 + player.getWeapon().toString() + " and defends with "
							 + player.getShield().toString() + ".\n";
			new OperationLog(logText, logFileName, false);
			System.out.println(fancyText);
	}
	
	public static String hpLog(Player player, String logFileName) {
		String logText = String.format(player.getNickname() + "'s HP: %.2f\n", player.getHP());
		new OperationLog(logText, logFileName);
		return logText;
	}
	
	public static void finalLine(String logFileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
			writer.write("\n\n==============================================\n\n\n");
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}
	
	public static String logFileNameGenerator() {
		LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
	    String formattedDate = date.format(formatter);
	    return "logs/game_logs_" + formattedDate + ".txt";
	}
	
	// log printout
	public void printoutLog() {
		System.out.println(this.logText);
	}
	
	public void logToFile(String logFileName) {
		// opening new writer inside the try for it to close automatically
		// when the try block is exited
		// "true" in FileWriter turning on the append option
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
			writer.write(this.logText);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}
	
	public static String dumpLogFile(String logFileName) {
		String logs = "";
		try {
			// opening the file for reading
			BufferedReader reader = new BufferedReader(new FileReader(logFileName));
			// reading each line from the file
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				logs += line + "\n";
			}
			// closing the file
			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}
		return logs;
	}
	
	// getter
	public String getLogText() {
		return this.logText;
	}

	// setter
	public void setLogText(String logText) {
		this.logText = logText;
	}


}
