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

import java.io.File;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconProvider {

//	private static final ImageIcon TEXT_FILE_ICON     = createImageIcon("/icons/file_16pix.png");
	private static final ImageIcon TEXT_FILE_ICON     = createImageIcon("/icons/text_file_16pix.png");
	private static final ImageIcon DBCFG_FILE_ICON    = createImageIcon("/icons/dbcfg_file_16pix.png");
	private static final ImageIcon VELOCITY_FILE_ICON = createImageIcon("/icons/velocity_file_16pix.png");
	private static final ImageIcon MODEL_FILE_ICON    = createImageIcon("/icons/model_file_16pix.png");
	private static final ImageIcon ENTITY_FILE_ICON   = createImageIcon("/icons/entity_file_16pix.png");
	private static final ImageIcon TEMPLATES_CFG_FILE_ICON = createImageIcon("/icons/templates_cfg_file_16pix.png");
	private static final ImageIcon TELOSYS_CFG_FILE_ICON   = createImageIcon("/icons/telosys_cfg_file_16pix.png");
	
	private static ImageIcon createImageIcon(String path) {
		URL url = TextEditor.class.getResource(path);
		if (url != null) {
			return new ImageIcon(url);
		} else {
			throw new RuntimeException("Couldn't find icon file " + path );
		}
	}
	
	public static Icon getIcon(File file) {
		String fileName =  file.getName();
		if ( fileName.endsWith(".entity") ) return ENTITY_FILE_ICON ;
		if ( fileName.endsWith(".model") ) return MODEL_FILE_ICON ;
		if ( fileName.endsWith(".dbcfg") ) return DBCFG_FILE_ICON ;
		if ( fileName.endsWith(".vm") ) return VELOCITY_FILE_ICON ;
		if ( fileName.equals("templates.cfg") ) return TEMPLATES_CFG_FILE_ICON ;
		if ( fileName.startsWith("telosys") && fileName.endsWith(".cfg")) return TELOSYS_CFG_FILE_ICON ;
		// Default icon 
		return TEXT_FILE_ICON ;
	}
	


}
