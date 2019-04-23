/**
 *  Copyright (C) 2015-2019  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.editor.components;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * About dialog box
 *
 */
public class AboutDialog {

	private static final URL ICON_URL = AboutDialog.class.getClassLoader().getResource("icons/telosys_64.png");
	
	public static void show() {
		
		String title = "About Telosys editor";
		
		String msg = "Telosys editor version " + TextEditorVersion.VERSION 
//				+ "\n"
//				+ "home is '" + TextEditor.getHome() + "' "
				+ "\n" ;
		
		if (ICON_URL != null)   {
			ImageIcon icon = new ImageIcon(ICON_URL);
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE, icon);
		}
		else {
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
		}
		  
	}

}
