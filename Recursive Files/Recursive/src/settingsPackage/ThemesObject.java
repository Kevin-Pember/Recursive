package settingsPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ThemesObject {
	
	public String input;
	
	private String displayColor;
	
	private String numsButtonsColor;
	
	private String funcButtonsColor;
	
	private boolean fontColor;
	
	private FXMLLoader themeObjectLoader;
	
	private Parent themeObject;
	
	private ThemesController themeController;
	
	private SettingsControllClass parentClass;
	public ThemesObject(String input,SettingsControllClass parentClass) {
		this.input = input;
		System.out.println("display "+ input.substring(0,input.indexOf('»')));
		displayColor = input.substring(0,input.indexOf('»'));
		input = input.substring(input.indexOf('»')+1);
		System.out.println("numsButtonsColor "+ input.substring(0,input.indexOf('»')));
		numsButtonsColor = input.substring(0,input.indexOf('»'));
		input = input.substring(input.indexOf('»')+1);
		System.out.println("funcButtonsColor "+ input.substring(0,input.indexOf('»')));
		funcButtonsColor = input.substring(0,input.indexOf('»'));
		input = input.substring(input.indexOf('»')+1);
		System.out.println("fontColor "+ input.substring(0,input.indexOf('»')));
		fontColor = Boolean.parseBoolean(input.substring(0,input.indexOf('»')));
		this.parentClass = parentClass;
		loadElement();
	}
	public void loadElement() {
		try {
			themeObjectLoader = new FXMLLoader(getClass().getResource("/ThemeGroup.fxml"));
			System.out.println("Element Loaded");
			themeObject = themeObjectLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		themeController = (ThemesController) themeObjectLoader.getController();
		themeController.setColors(displayColor, numsButtonsColor, funcButtonsColor, fontColor);
		themeController.setSettingsController(parentClass,this);
	}
	public void setRadio(boolean setting) {
		themeController.setRadioButton(setting);
	}
	public Parent getParent() {
		return themeObject;
	}
	public void enableRemove(boolean enable) {
		themeController.removeButton(enable, input);
	}
}
