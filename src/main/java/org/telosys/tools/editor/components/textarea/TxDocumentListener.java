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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * DocumentListener implementation <br>
 * Listen to all changes on the text document <br>
 * If the document is modified it notifies the associated component
 * 
 * @author Laurent GUERIN
 *
 */
public class TxDocumentListener implements DocumentListener {

	private final TxScrollPane scrollPane ;

	/**
	 * Constructor
	 * @param scrollPane
	 */
	public TxDocumentListener(TxScrollPane scrollPane) {
		super();
		this.scrollPane = scrollPane;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		scrollPane.textUpdate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		scrollPane.textUpdate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// Gives notification that an attribute or set of attributes changed
		// Nothing to do
	}

}
