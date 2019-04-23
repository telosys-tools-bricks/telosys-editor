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

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.telosys.tools.editor.components.TextEditor;
import org.telosys.tools.editor.components.textarea.TxScrollPane;

/**
 * This component is design to be used as a "tab component" <br>
 * It contains the components to be printed in the TAB : <br>
 * a JLabel for the text and the file icon  <br>
 * a JButton for the 'close' button (to close the tab it belongs to ) <br>
 *
 * @author Laurent GUERIN
 *
 */
public class ButtonTabComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public ButtonTabComponent(TextEditor textEditor, TxScrollPane scrollPane, Icon icon) {

        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        setOpaque(false);
      
        //----- Add tab text ( JLabel ) with icon if any
        JLabel label = new TabLabel(scrollPane);
        if ( icon != null ) {
            label.setIcon(icon);
        }
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        
        //----- Add tab close button ( JButton )
        JButton button = new TabButton(textEditor, scrollPane);
        add(button);
        
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

}


