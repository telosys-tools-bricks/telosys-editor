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

/**
 * This class is designed to be used as the interface to manage the TextEditor from the CLI
 * 
 * @author Laurent GUERIN
 *
 */
public class TextEditorManager {

	private static TextEditor textEditor = null;
	
	/**
	 * Private constructor
	 */
	private TextEditorManager() {
	}
	
	/**
	 * Set the current HOME directory 
	 * @param directory
	 */
	public static void setHomeDirectory(String directory) {
		TextEditorContext.setHomeDirectory(directory);
	}

	/**
	 * Just init (if necessary) and open the editor (without file to edit)
	 */
	public static void openEditor() {
		initTextEditor() ;
		textEditor.putOnFront();
	}
	
	/**
	 * Init (if necessary) and open the editor to edit the given file
	 * @param file
	 */
	public static void editFile(File file) {
		initTextEditor() ;
		// Use current TextEditor to edit the given file
		textEditor.editFile(file); 
		textEditor.putOnFront();
	}

	/**
	 * Init the TextEditor (if no current TextEditor create it)
	 */
	private static void initTextEditor() {
		if ( textEditor == null ) { // not yet created
			// Create the TextEditor instance
			textEditor = new TextEditor();
		}
	}
}
