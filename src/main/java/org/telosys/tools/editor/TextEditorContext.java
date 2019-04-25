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

public class TextEditorContext {

	private static String     homeDirectory = null ;
	
	/**
	 * Private constructor
	 */
	private TextEditorContext() {
		
	}
	
	/**
	 * Set the current HOME directory 
	 * @param directory
	 */
	protected static void setHomeDirectory(String directory) {
		homeDirectory = directory ;
	}

	/**
	 * Returns the current HOME directory as String (or null if not defined)
	 * @return
	 */
	public static String getHomeDirectory() {
		return homeDirectory ;
	}

	/**
	 * Returns the current HOME directory as File (or null if not defined)
	 * @return
	 */
	public static File getHomeDirectoryFile() {
		if ( homeDirectory != null ) {
			return new File(homeDirectory);
		}
		else {
			return null ;
		}
	}

}
