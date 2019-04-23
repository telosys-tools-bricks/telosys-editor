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
package org.telosys.tools.editor;

import java.io.File;

import org.telosys.tools.editor.components.TextEditor;

public class TextEditorManager {

	private static String     homeDirectory = null ;
	
	private static TextEditor textEditor = null;
	
	/**
	 * Private constructor
	 */
	private TextEditorManager() {
		
	}
	
	public static void setHomeDir(String directory) {
		homeDirectory = directory ;
		if ( textEditor != null && homeDirectory != null ) {
			// Update the current directory in the Editor
			textEditor.setCurrentDir(new File(homeDirectory));
		}
	}
	
	public static void editFile(File file) {
		// If no current TextEditor create it
		if ( textEditor == null ) {
			File homeDir = getHomeDir(file);
			textEditor = new TextEditor(homeDir);
		}
		// Use current TextEditor to edit the given file
		textEditor.editFile(file); 
		textEditor.putOnFront();
	}

	private static File getHomeDir(File file) {
		if ( homeDirectory != null ) {
			// Home directory has been set before
			return new File(homeDirectory) ;
		}
		else {
			// Not supposed to happen
			return file.getParentFile();
		}
	}
}