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
package org.telosys.tools.editor.components.textarea;

import java.io.File;
import java.io.Serializable;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * JScrollPane specialization to keep the file, the tab title and return
 * text.<br>
 * 
 * The 'ScrollPane' is the component containing the 'TextArea' <br>
 * ( it's a TextArea with scroll bars )
 * 
 * @author Laurent GUERIN
 *
 */
public class TxScrollPane extends JScrollPane implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String FILLER = "  " ;
	private static final String STAR   = "*" ;

	private final JTabbedPane tabbedPane; // set of tabs
	private final TxTextArea textArea;
	private File file; // Can change in case of 'save as'
	private String title;
	private boolean modified = false;

	/**
	 * Constructor
	 * 
	 * @param textArea
	 * @param file
	 */
	public TxScrollPane(TxTextArea textArea, File file, JTabbedPane tabbedPane) {
		super(textArea);
		this.tabbedPane = tabbedPane;
		this.textArea = textArea;
		this.file = file;
		this.title = FILLER + file.getName();
		this.modified = false;
	}

	public File getFile() {
		return file;
	}

	public String getTitle() {
		return title;
	}

	public TxTextArea getTextArea() {
		return textArea;
	}

	public String getText() {
		return textArea.getText();
	}

	// ---------------------------------------------------------------------------------------

	protected void textUpdate() {
		if (!modified) {
			markAsUpdated();
		}
	}

	private int getTabIndex() {
		return tabbedPane.indexOfComponent(this);
	}

	/**
	 * Reset status and change the current file (eg for "Save As")
	 * 
	 * @param file
	 */
	public void reset(File file) {
		this.file = file;
		this.title = file.getName();
		reset();
	}

	/**
	 * Reset status as 'not modified'
	 */
	public void reset() {
		modified = false;
		title = FILLER + file.getName(); // Reset title without '*'
		tabbedPane.setTitleAt(getTabIndex(), title);   
//		tabbedPane.repaint();
	}

	/**
	 * Marks the current Tab as 'modified'
	 */
	private void markAsUpdated() {
		modified = true; // Flag as modified
		title = STAR + file.getName(); // add "*" at the beginning of the title
		tabbedPane.setTitleAt(getTabIndex(), title);
//		tabbedPane.repaint();
	}

	public boolean isModified() {
		return modified;
	}
}
