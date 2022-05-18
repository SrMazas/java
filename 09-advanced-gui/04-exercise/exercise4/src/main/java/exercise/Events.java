package exercise;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class Events extends JDialog implements ActionListener, ItemListener {
	JComboBox<String> cbA;
	JComboBox<String> cbB;

	JButton btAdd;
	JButton btRemove;
	JButton btTransferA;
	JButton btTransferB;

	JTextField txfA;
	JTextField txfB;

	JLabel lblElements;
	JLabel lblIndex;

	int heightSize = 25;
	int btLocY = 25;
	int txLocY = 60;
	int cbLocY = 95;

	String[] textASplit;
	boolean errTxtA = true;

	public Events() {
		super();

		setLayout(null);

		MouseHandler handler = new MouseHandler();

		btAdd = new JButton("Add");
		btAdd.setSize(100, heightSize);
		btAdd.setLocation(25, btLocY);
		btAdd.addActionListener(this);
		btAdd.addMouseListener(handler);
		add(btAdd);

		btRemove = new JButton("Remove");
		btRemove.setSize(100, heightSize);
		btRemove.setLocation(btAdd.getX() + btAdd.getSize().width + 5, btLocY);
		btRemove.addActionListener(this);
		add(btRemove);

		btTransferA = new JButton("Transfer A");
		btTransferA.setSize(100, heightSize);
		btTransferA.setLocation(btRemove.getX() + btRemove.getSize().width + 5, btLocY);
		btTransferA.addActionListener(this);
		add(btTransferA);

		btTransferB = new JButton("Transfer B");
		btTransferB.setSize(100, heightSize);
		btTransferB.setLocation(btTransferA.getX() + btTransferA.getSize().width + 5, btLocY);
		btTransferB.addActionListener(this);
		add(btTransferB);

		txfA = new JTextField(20);
		txfA.setSize(200, heightSize);
		txfA.addActionListener(this);
		txfA.setLocation(25, txLocY);
		add(txfA);

		txfB = new JTextField(20);
		txfB.setSize(200, heightSize);
		txfB.addActionListener(this);
		txfB.setLocation(txfA.getX() + txfA.getSize().width + 17, txLocY);
		add(txfB);

		cbA = new JComboBox<String>();
		cbA.setSize(100, heightSize);
		cbA.setLocation(25, cbLocY);
		cbA.addItemListener(this);
		cbA.setVisible(false);
		add(cbA);

		cbB = new JComboBox<String>();
		cbB.setSize(100, heightSize);
		cbB.setLocation(cbA.getX() + cbA.getSize().width + 50, cbLocY);
		cbB.addItemListener(this);
		cbB.setVisible(false);
		add(cbB);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		add(e);

		remove(e);

		if (e.getSource() == btTransferB) {
			if (cbA.getItemCount() > 0) {
				cbB.addItem(cbA.getSelectedItem().toString());
				cbA.removeItem(cbA.getSelectedItem());
			}
			visibleA();
			visibleB();
		}

		if (e.getSource() == btTransferA) {
			if (cbB.getItemCount() > 0) {
				cbA.addItem(cbB.getSelectedItem().toString());
				cbB.removeItem(cbB.getSelectedItem());
			}
			visibleA();
			visibleB();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	private class MouseHandler extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);

			System.err.println("Mouse Entered");

			if (e.getSource() == btAdd) {
				btAdd.setForeground(Color.RED);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseExited(e);

			System.err.println("Mouse Exit");

			if (e.getSource() == btAdd) {
				btAdd.setForeground(Color.BLACK);
			}
		}
	}

	private void add(ActionEvent e) {
		if (e.getSource() == btAdd || e.getSource() == txfA) {
			String txtA = txfA.getText();
			if (!txtA.equals("") && !txtA.trim().equals("")) {

				if (txtA.contains(";")) {
					textASplit = txtA.split(";");
					for (int i = 0; i < textASplit.length; i++) {
						if (!textASplit[i].trim().equals("") && !textASplit[i].equals(" ")) {
							cbA.addItem(textASplit[i]);
							errTxtA = false;
						}
					}

				} else {
					cbA.addItem(txtA);
					errTxtA = false;
				}

			}

			if (!errTxtA) {
				cbA.setVisible(true);
			}
		}
	}

	private void remove(ActionEvent e) {
		if (e.getSource() == btRemove) {
			String txtB;
			Boolean anyDelete = false;
			txtB = txfB.getText();
			if (!txtB.trim().equals("")) {
				for (int i = cbA.getItemCount() - 1; i >= 0; i--) {
					if (cbA.getItemAt(i).startsWith(txtB)) {
						cbA.removeItemAt(i);
						anyDelete = true;
					}
				}
				if (anyDelete) {
					txfB.setText("");
				}
			} else {
				cbA.removeItem(cbA.getSelectedItem());
			}
			visibleA();
		}
	}

	private void visibleA() {
		if (cbA.getItemCount() <= 0) {
			cbA.setVisible(false);
		}else{
			cbB.setVisible(true);
		}
	}

	private void visibleB() {
		if (cbB.getItemCount() <= 0) {
			cbB.setVisible(false);
		}else{
			cbB.setVisible(true);
		}
	}

}
