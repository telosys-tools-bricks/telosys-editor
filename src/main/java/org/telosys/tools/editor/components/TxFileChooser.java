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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Specific FileChooser with System Look And Feel
 * 
 * @author Laurent GUERIN 
 *
 */
public class TxFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	public TxFileChooser(File dir) {
		super();
		
		this.setCurrentDirectory(dir);

//		this.setDialogTitle(title);
//		this.setApproveButtonText(buttonText);

		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
//		FileNameExtensionFilter filter;
//		filter = new FileNameExtensionFilter("Entity", "entity");
		this.addChoosableFileFilter( new FileNameExtensionFilter("Entity (.entity)", "entity") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("DSL Model (.model)", "model") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("Template (.vm)", "vm") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("Configuration (.cfg)", "cfg") );
		this.addChoosableFileFilter( new FileNameExtensionFilter("Database Configuration (.dbcfg)", "dbcfg") );
//		filter = new FileNameExtensionFilter("Entity", "entity");
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Template (Velocity file)", "vm");
//		fileChooser.setFileFilter(filter);
	}

//	@Override
//	public void updateUI() {
//		super.updateUI();
//	}

//	@Override
//	public void updateUI() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Throwable ex) {
//			//originalLookAndFeel = null;
//		}
//		super.updateUI();
//	}

	
//	@Override
//	public void updateUI() {
//		LookAndFeel originalLookAndFeel = UIManager.getLookAndFeel();
//		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Throwable ex) {
//			originalLookAndFeel = null;
//		}
//
//		super.updateUI();
//
//		if (originalLookAndFeel != null) {
//////			FilePane filePane = findFilePane(this);
//////			filePane.setViewType(FilePane.VIEWTYPE_DETAILS);
//////			filePane.setViewType(FilePane.VIEWTYPE_LIST);
////
////			Color background = UIManager.getColor("Label.background");
////			setBackground(background);
////			setOpaque(true);
//
//			try {
//				UIManager.setLookAndFeel(originalLookAndFeel);
//			} catch (UnsupportedLookAndFeelException e) {
//			} // shouldn't get here
//		}
//	}

}
