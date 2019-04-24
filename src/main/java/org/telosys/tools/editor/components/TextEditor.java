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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.telosys.tools.editor.components.tabs.ButtonTabComponent;
import org.telosys.tools.editor.components.textarea.TxDocumentListener;
import org.telosys.tools.editor.components.textarea.TxScrollPane;
import org.telosys.tools.editor.components.textarea.TxTextArea;

/**
 * Text editor main frame ( JFrame specialization )
 * 
 * @author Laurent GUERIN
 *
 */
public class TextEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final FileManager fileManager = new FileManager();

	private final JFrame frame;
	private final JTabbedPane tabbedPane;
	private final JLabel bottomLabel;

	private File currentDir;

	/**
	 * Constructor 
	 * @param initialDirectory the initial directory 
	 */
	public TextEditor(File initialDirectory) {
		super();

		// Set Operating System Look & Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// Nothing to do 
		}

		frame = this;

		currentDir = initialDirectory;

		setSize(600, 600);
		setLocationRelativeTo(null);

		// --- Window closing management
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				actionExit();
			}
		});

		initFonts();

		// --- Menu bar and Tool bar
		TextEditorMenu menu = new TextEditorMenu(this);
		setJMenuBar(menu.getMenuBar());

		// --- Tool bar for icons, etc
		// JToolBar toolBar = new JToolBar();
		// pane.add(toolBar, BorderLayout.SOUTH);

		// --- Central panel with tabs
		JPanel centralPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
				if (scrollPane != null) {
					bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
				} else {
					bottomLabel.setText("");
				}
			}
		});
		centralPanel.add(tabbedPane, BorderLayout.CENTER);
		this.add(centralPanel, BorderLayout.CENTER);

		// --- Bottom label
		bottomLabel = new JLabel("Bottom label ");
		JPanel panel = new JPanel();
		panel.add(bottomLabel);
		this.getContentPane().add(panel, BorderLayout.SOUTH);

		// --- Frame icon
		this.setIconImage("icons/telosys_32.png");
		this.setTitle("Telosys Editor");

		this.setVisible(true);

		// End of constructor : expose debug variables
		DebugVariables.tabbedPane = this.tabbedPane;
	}
	
	/**
	 * Set the current directory (used by the 'FileChooser' for 'Open/Save as' 
	 * @param currentDir
	 */
	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir ;
	}
	
	@Override
	public void setVisible(final boolean visible) {
		// // make sure that frame is marked as not disposed if it is asked to be
		// visible
		// if (visible) {
		// super.setDisposed(false);
		// }

		// let's handle visibility...
		if (!visible || !isVisible()) { // have to check this condition simply because super.setVisible(true) invokes
										// toFront if frame was already visible
			super.setVisible(visible);
		}
		// ...and bring frame to the front.. in a strange and weird way
		if (visible) {
			putOnFront();
		}
	}

	/**
	 * Returns the index of the tab containing the given file (or -1 if none)
	 * 
	 * @param file
	 * @return
	 */
	private int getFileTabIndex(File file) {
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			Component c = tabbedPane.getComponentAt(i);
			if (c instanceof TxScrollPane) {
				TxScrollPane scrollPane = (TxScrollPane) c;
				if (file.getAbsolutePath().equals(scrollPane.getFile().getAbsolutePath())) {
					// File found (this file is already loaded in tab 'i')
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Edit the given file <br>
	 * Create a new tab if the file is not already loaded or select the tab
	 * containing the file
	 * 
	 * @param file
	 */
	public void editFile(File file) {

		int tabIndex = -1 ;
		if ( file != null ) {
			tabIndex = getFileTabIndex(file);
		}
		
		if (tabIndex >= 0) {
			// Already open in a Tab => select this Tab
			tabbedPane.setSelectedIndex(tabIndex);
		} else {
			// Not yet open => load it in a new Tab
			String text = null ;
			if ( file != null ) {
				 text = fileManager.readTextFromFile(file);
			}
			if (text != null) {

				TxTextArea textArea = new TxTextArea(text);

				// Creates a JScrollPane that displays the contents of the specified component,
				// where both horizontal and vertical scrollbars appear whenever the component's
				// contents are larger than the view.
				TxScrollPane scrollPane = new TxScrollPane(textArea, file, tabbedPane);

				// Add the new tab in the "TabbedPane" component
				tabbedPane.addTab(scrollPane.getTitle(), scrollPane);

				int newTabIndex = tabbedPane.getTabCount() - 1;
				
				// #LGU
				/*
				 * Sets the component that is responsible for rendering the title for the specified tab.
				 * A null value means JTabbedPane will render the title and/or icon for the specified tab.
				 * A non-null value means the component will render the title and JTabbedPane will not render the title and/or icon.
				 * Note: The component must not be one that the developer has already added to the tabbed pane.
				 */
				Icon icon = IconProvider.getIcon(file);
				tabbedPane.setTabComponentAt(newTabIndex, new ButtonTabComponent(this, scrollPane, icon)); // Specific TAB
				
				TxDocumentListener documentListener = new TxDocumentListener(scrollPane);
				textArea.setDocumentListener(documentListener);
				tabbedPane.setSelectedIndex(newTabIndex);
			}
		}
	}
	
	public void putOnFront() {
		super.setVisible(true);
		int state = super.getExtendedState();
		state &= ~JFrame.ICONIFIED;
		super.setExtendedState(state);
		super.setAlwaysOnTop(true);
		super.toFront();
		super.requestFocus();
		super.setAlwaysOnTop(false);
	}

	private void initFonts() {
//		// Font for Menu and Menu items
//		Font menuFont = new Font("sans-serif", Font.BOLD, 14);
//		UIManager.put("Menu.font", menuFont);
//		UIManager.put("MenuItem.font", menuFont);

		// Font for TextArea
		Font textAreaFont = new Font(Font.MONOSPACED, Font.PLAIN, 16); // OK
		UIManager.put("TextArea.font", textAreaFont);
	}

	private void setIconImage(String imagePath) {
		URL url = getClass().getClassLoader().getResource(imagePath);
		if (url != null) {
			ImageIcon imageIcon = new ImageIcon(url);
			this.setIconImage(imageIcon.getImage());
		}
	}

	protected void actionOpen() {
		JFileChooser fileChooser = createFileChooser("Open file", "Open", currentDir);
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			editFile(selectedFile);
		}
	}

	protected void actionSave() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		save(scrollPane);
	}

	protected void actionSaveAs() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		File file = scrollPane.getFile();
		JFileChooser fileChooser = createFileChooser("Save as", "Save", file.getParentFile());
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null) {
				int foundIndex = getFileTabIndex(selectedFile);
				if ( foundIndex >= 0 ) {
					String msg = "Cannot save as '" + selectedFile.getName() + "'"
							+ "\nThis file is already opened" ;
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.WARNING_MESSAGE);
					tabbedPane.setSelectedIndex(foundIndex);
				}
				else {
					fileManager.saveTextToFile(scrollPane.getText(), selectedFile);
					scrollPane.reset(selectedFile);
					bottomLabel.setText(scrollPane.getFile().getAbsolutePath());
				}
			}
		}
	}

	private JFileChooser createFileChooser(String title, String buttonText, File directory) {
		JFileChooser.setDefaultLocale(Locale.US);
		JFileChooser fileChooser = new TxFileChooser(directory);
		fileChooser.setDialogTitle(title);
		fileChooser.setApproveButtonText(buttonText);
		return fileChooser;
	}

	/**
	 * Saves the file associated with the given ScrollPane
	 * 
	 * @param scrollPane
	 */
	protected void save(TxScrollPane scrollPane) {
		if (scrollPane != null) {
			fileManager.saveTextToFile(scrollPane.getText(), scrollPane.getFile());
			scrollPane.reset();
		}
	}

	/**
	 * Close all tabs (with confirmation if some texts have been modified)
	 */
	protected boolean closeAllTabs() {
		int n = tabbedPane.getTabCount();
		for (int i = n - 1; i >= 0; i--) {
			Component component = tabbedPane.getComponentAt(i);
			TxScrollPane scrollPane = (TxScrollPane) component;
			boolean r = closeTab(scrollPane);
			
			if (!r) {
				// Cancel
				return false;
			}
		}
		return true; // All closed
	}

	/**
	 * Close the given ScrollPane (with confirmation if the text has been modified)
	 * 
	 * @param scrollPane
	 */
	public boolean closeTab(TxScrollPane scrollPane) {
		if (scrollPane != null && scrollPane.isModified()) {
			// The text has been modified (and not yet saved) => Confirmation
			String msg = "'" + scrollPane.getFile().getName() + "' has been modified.  Save changes ?";
			Object[] options = { "Save", "Don't Save", "Cancel" };
			int choice = JOptionPane.showOptionDialog(null, msg, "Close file ", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
					options, // the titles of buttons
					options[0]); // default button title
			if (choice == 0) {
				// Save and close
				save(scrollPane);
				tabbedPane.remove(scrollPane);
				return true;
			} else if (choice == 1) {
				// Don't Save and close
				tabbedPane.remove(scrollPane);
				return true;
			} else {
				// Cancel
				return false;
			}
		} else {
			// Text not modified => Close directly
			tabbedPane.remove(scrollPane);
			return true;
		}
	}

	protected void actionClose() {
		closeTab((TxScrollPane) tabbedPane.getSelectedComponent());
	}

	protected void actionCloseAll() {
		closeAllTabs();
	}

	/**
	 * Exit editor
	 */
	protected void actionExit() {
		if (closeAllTabs()) {
			// Ok, everything closed
			frame.dispose();
		}
	}

	protected void actionPaste() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().paste();
		}
	}

	protected void actionCut() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().cut();
		}
	}

	protected void actionCopy() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().copy();
		}
	}

	protected void actionSelectAll() {
		TxScrollPane scrollPane = (TxScrollPane) tabbedPane.getSelectedComponent();
		if (scrollPane != null) {
			scrollPane.getTextArea().selectAll();
		}
	}

}
