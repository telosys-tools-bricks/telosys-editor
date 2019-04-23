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
package org.telosys.tools.editor.components.tabs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import org.telosys.tools.editor.components.TextEditor;
import org.telosys.tools.editor.components.textarea.TxScrollPane;

/**
 * Tab "X" button used to close the current tab
 * 
 * @author Laurent GUERIN
 *
 */
public class TabButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static final MouseListener buttonMouseListener = new TabButtonMouseListener() ;

	private final TextEditor   textEditor ;
	private final TxScrollPane scrollPane ;

	public TabButton(TextEditor textEditor, TxScrollPane scrollPane) {

		this.textEditor = textEditor;
		this.scrollPane = scrollPane;

		int size = 17;
		setPreferredSize(new Dimension(size, size));
		setToolTipText("close this tab");
		setUI(new BasicButtonUI()); // Button UI (same for all Look & Feel)
		setContentAreaFilled(false); // transparent
		setFocusable(false); // Not focusable
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(false);
		addMouseListener(buttonMouseListener); // Rollover effect
		setRolloverEnabled(true);
		addActionListener(this); // Listener to close the tab by clicking the button
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.textEditor.closeTab(scrollPane);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JButton#updateUI()
	 */
	@Override
	public void updateUI() {
		// Nothing to do, don't want to update UI for this button
	}

	// paint the cross
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		// shift the image for pressed buttons
		if (getModel().isPressed()) {
			g2.translate(1, 1);
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		if (getModel().isRollover()) {
			g2.setColor(Color.MAGENTA);
		}
		int delta = 6;
		g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
		g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
		g2.dispose();
	}

}
