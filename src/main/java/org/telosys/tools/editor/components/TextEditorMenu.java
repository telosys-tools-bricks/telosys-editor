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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Text editor menu builder (provides the 'JMenuBar')
 * 
 * @author Laurent GUERIN
 *
 */
public class TextEditorMenu {

	private final TextEditor editor;
	private final JMenuBar menuBar;

	protected TextEditorMenu(TextEditor editor) {

		this.editor = editor;

		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createToolsMenu());
		menuBar.add(createHelpMenu());
	}

	protected JMenuBar getMenuBar() {
		return menuBar;
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem item ;

		item = new JMenuItem("Open");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // Ctrl-O
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionOpen();
			}
		});
		menu.add(item);

		item = new JMenuItem("Save");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // Ctrl-S
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionSave();
			}
		});
		menu.add(item);

		item = new JMenuItem("Save as");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionSaveAs();
			}
		});
		menu.add(item);

		menu.addSeparator(); //============

		item = new JMenuItem("Close");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionClose();
			}
		});
		menu.add(item);

		item = new JMenuItem("Close All");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionCloseAll();
			}
		});
		menu.add(item);

		menu.addSeparator(); //============

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionExit();
			}
		});
		menu.add(exitMenuItem);

		return menu;
	}

	private JMenu createEditMenu() {
		JMenu menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);

		JMenuItem cutI = new JMenuItem("Cut");
		cutI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cutI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionCut();
			}
		});
		menu.add(cutI);

		JMenuItem copyI = new JMenuItem("Copy");
		copyI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copyI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionCopy();
			}
		});
		menu.add(copyI);

		JMenuItem pasteI = new JMenuItem("Paste");
		pasteI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		pasteI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionPaste();
			}
		});
		menu.add(pasteI);

		JMenuItem selectI = new JMenuItem("Select All");
		selectI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				editor.actionSelectAll();
			}
		});
		menu.add(selectI);

		return menu;
	}

	private JMenu createToolsMenu() {
		JMenu menu = new JMenu("Tools");
		menu.setMnemonic(KeyEvent.VK_T);

		JMenuItem menuItem = new JMenuItem("Debug");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				DebugDialog.show();
			}
		});
		menu.add(menuItem);

		return menu;
	}

	private JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);

		JMenuItem menuItem = new JMenuItem("About");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				AboutDialog.show();
			}
		});

		menu.add(menuItem);

		return menu;
	}

}
