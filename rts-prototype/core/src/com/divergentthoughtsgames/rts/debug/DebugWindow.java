package com.divergentthoughtsgames.rts.debug;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.badlogic.gdx.Gdx;
import com.divergentthoughtsgames.rts.App;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DebugWindow
{
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public DebugWindow()
	{
		initialize();
	}

	public void setVisible(boolean value)
	{
		frame.setVisible(value);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 565, 538);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(200dlu;min)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(50dlu;min)"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default"),}));

		JLabel lblShowhideNavigationGraph = new JLabel("Show/hide navigation graph");
		frame.getContentPane().add(lblShowhideNavigationGraph, "2, 2");

		JCheckBox navGraphVisibleCheckBox = new JCheckBox("");
		navGraphVisibleCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gdx.app.postRunnable(() -> {
					App.debug.setEnabled();
				});
			}
		});
		frame.getContentPane().add(navGraphVisibleCheckBox, "4, 2, center, default");

		JLabel lblMakeNodePassableImpassable = new JLabel("Make node passable/impassable");
		frame.getContentPane().add(lblMakeNodePassableImpassable, "2, 4");

		JCheckBox nodePassableCheckBox = new JCheckBox("");
		frame.getContentPane().add(nodePassableCheckBox, "4, 4, center, default");

		JLabel lblLogOutputLevel = new JLabel("Log output level");
		frame.getContentPane().add(lblLogOutputLevel, "2, 6, left, default");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Error", "Info", "Debug"}));
		frame.getContentPane().add(comboBox, "4, 6, fill, default");
	}

}
