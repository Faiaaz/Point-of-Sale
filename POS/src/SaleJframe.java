import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.LinkedList;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JPanel;

public class SaleJframe implements PropertyListener {

	private JFrame frmPosSoftware;
	private JPanel panel;
	private JPanel ConfirmationMsg;
	private JPanel confrimDialogue;
	private JPanel psLoadedItem;
	private JPanel psEnterIDMenu;
	private JPanel home;
	private JPanel addNewItemPanel;
	private JPanel newItemConfirm;
	
	private JTextField ItemIDField;
	private JTextField QtyField;
	private JTextField TotalCost;
	private JTextField DateText;
	
	Beeper beep;
	ProcessSaleController psc;
	SaleLineItem sLineItem;										//ProductSpec of the last added item is stored in this
	LinkedList<SaleLineItem> sli;
	ProductSpecification ps;
	
	
	int counter;
	String total;
	String vat;
	String grandtotal;
	int selectIndex;
	int id;
	int quantity;
	boolean error = false; 										// This boolean controls Table update
	boolean saleInit = false;									// This boolean checks whether the Sale is initialed or not 
	boolean invalidInput = false;								// This boolean checks whether the input is valid or not (i.e. Valid input Number) 
	boolean noDiscount = false;
	boolean emptyCart = true;
	private JTextField vatAmount;
	private JTextField gTotal;
	private JTextField discountField;
	private JButton btnSale;
	private JButton btnEditInventory;
	private JTextField EditItemID;
	private JLabel lblProductSpecificationSheet;
	private JLabel lblProductIdEdit;
	private JTextField productIDEditTxt;
	private JLabel lblProductNameEdit;
	private JTextField ProductNameEditTxt;
	private JLabel lblUnitPriceEdit;
	private JTextField UnitPriceEditTxt;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnDelete;
	private JLabel label_1;
	private JLabel conFirmID;
	private JTextField confirmIDTxt;
	private JLabel confirmNamelabel;
	private JTextField confirmNametxt;
	private JLabel confirmPrice;
	private JTextField confirmPriceTxt;
	private JButton EditConfirmButton;
	private JButton saleHomebtn;
	private JButton btnAddNewInventory;
	private JLabel lblPosSoftware;
	private JLabel lblAddNewItem;
	private JLabel lblItemName;
	private JTextField addNewInventoryTxt;
	private JTextField addNewInvPriceTxt;
	private JLabel lblNewLabel;
	private JLabel newItemConfirmName;
	private JLabel newItemConPrice;
	private JTextField newItemConNameTxt;
	private JTextField newItemConPriceTxt;
	private JButton newItemConBackBtn;
	private JButton newItemConSavebtn;
	private JButton CancelAddingInventory;
	private JLabel lblInsertItemId;
	private JButton btnUndoDelete;
	private JButton InsertItemIDGoBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleJframe window = new SaleJframe();
					window.frmPosSoftware.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaleJframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		frmPosSoftware = new JFrame();
		frmPosSoftware.setTitle("POS Software");
		frmPosSoftware.setBounds(100, 100, 790, 648);
		frmPosSoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPosSoftware.getContentPane().setLayout(null);

		DefaultTableModel model = new DefaultTableModel();
		
		home = new JPanel();
		home.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(home);
		home.setLayout(null);
		
		btnSale = new JButton("SALE");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(false);
				panel.setVisible(true);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
			}
		});
		btnSale.setBounds(169, 202, 165, 40);
		home.add(btnSale);
		
		btnEditInventory = new JButton("EDIT INVENTORY ");
		btnEditInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(false);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(true);
				psLoadedItem.setVisible(false);
			}
		});
		btnEditInventory.setBounds(400, 202, 165, 40);
		home.add(btnEditInventory);
		
		btnAddNewInventory = new JButton("ADD NEW INVENTORY");
		btnAddNewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(false);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(true);
				newItemConfirm.setVisible(false);
				
			}
		});
		btnAddNewInventory.setBounds(295, 291, 150, 40);
		home.add(btnAddNewInventory);
		
		lblPosSoftware = new JLabel("POS SOFTWARE");
		lblPosSoftware.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPosSoftware.setBounds(291, 67, 154, 40);
		home.add(lblPosSoftware);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(67, 408, 133, 20);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a Discount", "Senior Discount (10%)", "Eid Discount", "Best for Store", "Best for Customer"}));
		
		
			//--------------Calculate Discount Button---------------
			
			JButton calcDiscount = new JButton("Calculate Discount");
			calcDiscount.setBounds(67, 444, 133, 23);
			panel.add(calcDiscount);
			
			JLabel label = new JLabel("  VAT :");
			label.setBounds(553, 396, 40, 15);
			panel.add(label);
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			gTotal = new JTextField();
			gTotal.setBounds(603, 426, 116, 21);
			panel.add(gTotal);
			gTotal.setForeground(new Color(0, 0, 255));
			gTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
			gTotal.setEditable(false);
			gTotal.setColumns(10);
			
			JLabel lblGrandTotal = new JLabel(" Grand Total :");
			lblGrandTotal.setBounds(510, 429, 83, 15);
			panel.add(lblGrandTotal);
			lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			vatAmount = new JTextField();
			vatAmount.setBounds(603, 395, 116, 19);
			panel.add(vatAmount);
			vatAmount.setForeground(new Color(255, 69, 0));
			vatAmount.setFont(new Font("Tahoma", Font.BOLD, 10));
			vatAmount.setEditable(false);
			vatAmount.setColumns(10);
			
			JLabel lblVat = new JLabel(" Discounted Total :");
			lblVat.setBounds(477, 463, 116, 15);
			panel.add(lblVat);
			lblVat.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			//--------------------New Sale button---------------------------
			
			JButton btnNewSale = new JButton("New Sale");
			btnNewSale.setBounds(67, 22, 101, 23);
			panel.add(btnNewSale);
			
			//-----------------------Date Field------------------------------
			
			JLabel lblDate = new JLabel("Date");
			lblDate.setBounds(492, 56, 40, 14);
			panel.add(lblDate);
			
					DateText = new JTextField();
					DateText.setBounds(542, 53, 177, 20);
					panel.add(DateText);
					DateText.setEditable(false);
					DateText.setColumns(10);
					
							JLabel lblTotal = new JLabel("Total :");
							lblTotal.setBounds(554, 370, 39, 15);
							panel.add(lblTotal);
							lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
							
									
									//------------------------Total Cost Field-----------------------
									
									TotalCost = new JTextField();
									TotalCost.setBounds(603, 362, 116, 20);
									panel.add(TotalCost);
									TotalCost.setFont(new Font("Tahoma", Font.BOLD, 11));
									TotalCost.setEditable(false);
									TotalCost.setColumns(10);
									
											//--------------------------Table Set--------------------------
											
											JScrollPane scrollPane = new JScrollPane();
											scrollPane.setBounds(67, 159, 652, 181);
											panel.add(scrollPane);
											JTable table = new JTable(model);
											scrollPane.setViewportView(table);
											table.setModel(new DefaultTableModel(new Object[][] {},
													new String[] { "SL#", "Item Name", "Unit Price", "Quantity", "Sub Total" }));
											
													
													//--------------------AddButton----------------------------
													
													
													JButton btnAddItem = new JButton("Add Item");
													btnAddItem.setBounds(67, 116, 101, 23);
													panel.add(btnAddItem);
													
															JLabel lblItemId = new JLabel("Item ID:");
															lblItemId.setBounds(15, 56, 60, 14);
															panel.add(lblItemId);
															
																	QtyField = new JTextField();
																	QtyField.setBounds(67, 85, 165, 20);
																	panel.add(QtyField);
																	QtyField.setColumns(10);
																	
																			JLabel lblQuantity = new JLabel("Quantity:");
																			lblQuantity.setBounds(15, 88, 66, 14);
																			panel.add(lblQuantity);
																			
																					
																			
																					
																					// -----------------------TextIDFields----------------------------
																					
																					ItemIDField = new JTextField();
																					ItemIDField.setBounds(67, 53, 165, 20);
																					panel.add(ItemIDField);
																					ItemIDField.setColumns(10);
																					
																					discountField = new JTextField();
																					discountField.setBounds(603, 458, 116, 20);
																					panel.add(discountField);
																					discountField.setForeground(new Color(34, 139, 34));
																					discountField.setFont(new Font("Tahoma", Font.BOLD, 11));
																					discountField.setEditable(false);
																					discountField.setColumns(10);
																					
																					saleHomebtn = new JButton("Home");
																					saleHomebtn.addActionListener(new ActionListener() {
																						public void actionPerformed(ActionEvent e) {
																							home.setVisible(true);
																							panel.setVisible(false);
																							confrimDialogue.setVisible(false);
																							ConfirmationMsg.setVisible(false);
																							psEnterIDMenu.setVisible(false);
																							psLoadedItem.setVisible(false);
																							addNewItemPanel.setVisible(false);
																							newItemConfirm.setVisible(false);
																						}
																					});
																					saleHomebtn.setBounds(636, 22, 83, 23);
																					panel.add(saleHomebtn);
																					
																							// -----------------Add button Action-----------------------------
																					
																							btnAddItem.addActionListener(new ActionListener() {
																								public void actionPerformed(ActionEvent e) {
																									
																									//---------------------Calling the worker Method-----------------
																									
																									addNewItemButtonAction(e);
																					
																									// ------------------Update table-------------------------
																									
																									if (saleInit && !error && !invalidInput) {									//checking whether Update is valid or not
																										sli = psc.getSaleLineItemList();										//conditions include "No error in adding Item" & valid input format 
																																												// & provided that the sale is initiated at first	
																					
																										sLineItem = sli.getLast();												//Just adding the latest item in current Sale List
																					
																										int colNum = table.getModel().getColumnCount();
																					
																										Object[] item = new Object[colNum];
																					
																										item[0] = ++counter;
																										item[1] = sLineItem.getPs().getName();
																										item[2] = sLineItem.getPs().getPrice();
																										item[3] = sLineItem.getQuantity();
																										item[4] = sLineItem.getSubtotal();
																										((DefaultTableModel) table.getModel()).addRow(item);
																										
																										
																										
																										
																					
																									}
																								}
																							});
																							
																							// -----------------------New Sale Button Action----------------------------
	

																							btnNewSale.addActionListener(new ActionListener() {
																								public void actionPerformed(ActionEvent e) {
																									//----------------Calling Worker Method----------------
																									
																									newSaleButtonAction(e);
																									
																									//-----------------Clearing Table-----------------------
																									
																									table.setModel(new DefaultTableModel(new Object[][] {},
																											new String[] { "SL#", "Item Name", "Unit Price", "Quantity", "Sub Total" }));
																											TotalCost.setText("");
																											ItemIDField.setText("");
																											QtyField.setText("");
																											vatAmount.setText("");
																											gTotal.setText("");
																											discountField.setText("");

																								}
																							});
																							calcDiscount.addActionListener(new ActionListener() {
																								
																								public void actionPerformed(ActionEvent arg0) {
																									
																									
																									selectIndex = comboBox.getSelectedIndex();
																									
																									if(!saleInit){
																										JOptionPane.showMessageDialog(frmPosSoftware, "Please Start a Sale First");
																										return;
																									}
																									
																									if(emptyCart){
																										JOptionPane.showMessageDialog(frmPosSoftware, "Please Add an Item First");
																										return;
																									}
																										
																									if(selectIndex==0 || noDiscount){
																										JOptionPane.showMessageDialog(frmPosSoftware, "Please Select a Discount Option");
																										return;
																									}
																										
																									calculateDiscountButtonAction(arg0);				
																									
																									grandtotal = Integer.toString(psc.sale.getGrandTotal());				
																									gTotal.setText(grandtotal);
																									discountField.setText(Integer.toString(psc.sale.getTotal()));
																									ItemIDField.setText("");
																									QtyField.setText("");
																									
																									
																								}
																							});
		
		psEnterIDMenu = new JPanel();
		psEnterIDMenu.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(psEnterIDMenu);
		psEnterIDMenu.setLayout(null);
		
		JLabel lblItemId_1 = new JLabel("Item ID:");
		lblItemId_1.setBounds(198, 225, 74, 17);
		psEnterIDMenu.add(lblItemId_1);
		
		JButton btnShowItem = new JButton("SHOW ITEM");
		btnShowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				home.setVisible(false);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(true);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(false);
				newItemConfirm.setVisible(false);
				
				showProductSpecForEdit(arg0);
			}
		});
		btnShowItem.setBounds(373, 277, 130, 23);
		psEnterIDMenu.add(btnShowItem);
		
		EditItemID = new JTextField();
		EditItemID.setBounds(296, 223, 207, 20);
		psEnterIDMenu.add(EditItemID);
		EditItemID.setColumns(10);
		
		lblInsertItemId = new JLabel("INSERT ITEM ID FOR EDIT OR DELETE");
		lblInsertItemId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInsertItemId.setBounds(203, 85, 355, 46);
		psEnterIDMenu.add(lblInsertItemId);
		
		InsertItemIDGoBack = new JButton("Go Back");
		InsertItemIDGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(false);
				newItemConfirm.setVisible(false);
			}
		});
		InsertItemIDGoBack.setBounds(373, 322, 130, 23);
		psEnterIDMenu.add(InsertItemIDGoBack);
		
		psLoadedItem = new JPanel();
		psLoadedItem.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(psLoadedItem);
		psLoadedItem.setLayout(null);
		
		lblProductSpecificationSheet = new JLabel("Product Specification Sheet");
		lblProductSpecificationSheet.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductSpecificationSheet.setBounds(284, 106, 246, 14);
		psLoadedItem.add(lblProductSpecificationSheet);
		
		lblProductIdEdit = new JLabel("Product ID:");
		lblProductIdEdit.setBounds(174, 156, 91, 14);
		psLoadedItem.add(lblProductIdEdit);
		
		productIDEditTxt = new JTextField();
		productIDEditTxt.setEditable(false);
		productIDEditTxt.setBounds(304, 153, 226, 20);
		psLoadedItem.add(productIDEditTxt);
		productIDEditTxt.setColumns(10);
		
		lblProductNameEdit = new JLabel("Product Name:");
		lblProductNameEdit.setBounds(174, 205, 91, 14);
		psLoadedItem.add(lblProductNameEdit);
		
		ProductNameEditTxt = new JTextField();
		ProductNameEditTxt.setBounds(304, 202, 226, 20);
		psLoadedItem.add(ProductNameEditTxt);
		ProductNameEditTxt.setColumns(10);
		
		lblUnitPriceEdit = new JLabel("Unit Price:");
		lblUnitPriceEdit.setBounds(174, 257, 69, 14);
		psLoadedItem.add(lblUnitPriceEdit);
		
		UnitPriceEditTxt = new JTextField();
		UnitPriceEditTxt.setBounds(304, 254, 226, 20);
		psLoadedItem.add(UnitPriceEditTxt);
		UnitPriceEditTxt.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editItemSaveButtonWorker(e);
			}
		});
		btnSave.setBounds(441, 325, 89, 23);
		psLoadedItem.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(false);
				newItemConfirm.setVisible(false);
			}
		});
		btnCancel.setBounds(441, 393, 89, 23);
		psLoadedItem.add(btnCancel);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonActionWorker(e);
				
			}
		});
		btnDelete.setBounds(441, 359, 89, 23);
		psLoadedItem.add(btnDelete);
		
		newItemConfirm = new JPanel();
		newItemConfirm.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(newItemConfirm);
		newItemConfirm.setLayout(null);
		
		lblNewLabel = new JLabel("NEW ITEM CONFIRM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(291, 97, 224, 14);
		newItemConfirm.add(lblNewLabel);
		
		newItemConfirmName = new JLabel("Item Name");
		newItemConfirmName.setBounds(134, 193, 104, 14);
		newItemConfirm.add(newItemConfirmName);
		
		newItemConPrice = new JLabel("Unit Price");
		newItemConPrice.setBounds(134, 234, 104, 14);
		newItemConfirm.add(newItemConPrice);
		
		newItemConNameTxt = new JTextField();
		newItemConNameTxt.setEditable(false);
		newItemConNameTxt.setBounds(248, 190, 267, 20);
		newItemConfirm.add(newItemConNameTxt);
		newItemConNameTxt.setColumns(10);
		
		newItemConPriceTxt = new JTextField();
		newItemConPriceTxt.setEditable(false);
		newItemConPriceTxt.setBounds(248, 231, 267, 20);
		newItemConfirm.add(newItemConPriceTxt);
		newItemConPriceTxt.setColumns(10);
		
		newItemConBackBtn = new JButton("Go Back");
		newItemConBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(false);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(true);
				newItemConfirm.setVisible(false);
			}
		});
		newItemConBackBtn.setBounds(327, 306, 89, 23);
		newItemConfirm.add(newItemConBackBtn);
		
		newItemConSavebtn = new JButton("Save");
		newItemConSavebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newInvetoryAddSaveWorker(e);
			}
		});
		newItemConSavebtn.setBounds(426, 306, 89, 23);
		newItemConfirm.add(newItemConSavebtn);
		
		confrimDialogue = new JPanel();
		confrimDialogue.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(confrimDialogue);
		confrimDialogue.setLayout(null);
		
		label_1 = new JLabel("Product Specification Sheet");
		label_1.setBounds(285, 134, 224, 20);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		confrimDialogue.add(label_1);
		
		conFirmID = new JLabel("Product ID:");
		conFirmID.setBounds(181, 215, 55, 14);
		confrimDialogue.add(conFirmID);
		
		confirmIDTxt = new JTextField();
		confirmIDTxt.setEditable(false);
		confirmIDTxt.setColumns(10);
		confirmIDTxt.setBounds(315, 212, 226, 20);
		confrimDialogue.add(confirmIDTxt);
		
		confirmNamelabel = new JLabel("Product Name:");
		confirmNamelabel.setBounds(181, 246, 91, 14);
		confrimDialogue.add(confirmNamelabel);
		
		confirmNametxt = new JTextField();
		confirmNametxt.setEditable(false);
		confirmNametxt.setColumns(10);
		confirmNametxt.setBounds(315, 243, 226, 20);
		confrimDialogue.add(confirmNametxt);
		
		confirmPrice = new JLabel("Unit Price:");
		confirmPrice.setBounds(181, 277, 69, 14);
		confrimDialogue.add(confirmPrice);
		
		confirmPriceTxt = new JTextField();
		confirmPriceTxt.setEditable(false);
		confirmPriceTxt.setColumns(10);
		confirmPriceTxt.setBounds(315, 274, 226, 20);
		confrimDialogue.add(confirmPriceTxt);
		
		EditConfirmButton = new JButton("Confirm");
		EditConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editConfirmButtonWorker(e);
			}
		});
		EditConfirmButton.setBounds(436, 327, 105, 23);
		confrimDialogue.add(EditConfirmButton);
		
		btnUndoDelete = new JButton("Rollback");
		btnUndoDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rollbackButtonActionWorker(e);
			}
		});
		btnUndoDelete.setBounds(436, 373, 105, 23);
		confrimDialogue.add(btnUndoDelete);
		
		addNewItemPanel = new JPanel();
		addNewItemPanel.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(addNewItemPanel);
		addNewItemPanel.setLayout(null);
		
		lblAddNewItem = new JLabel("ADD NEW ITEM");
		lblAddNewItem.setBounds(289, 81, 187, 14);
		lblAddNewItem.setFont(new Font("Tahoma", Font.BOLD, 17));
		addNewItemPanel.add(lblAddNewItem);
		
		lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(155, 140, 59, 14);
		addNewItemPanel.add(lblItemName);
		
		addNewInventoryTxt = new JTextField();
		addNewInventoryTxt.setBounds(237, 137, 283, 20);
		addNewItemPanel.add(addNewInventoryTxt);
		addNewInventoryTxt.setColumns(10);
		
		JLabel lblItemPrice = new JLabel("Item Price:");
		lblItemPrice.setBounds(155, 192, 59, 14);
		addNewItemPanel.add(lblItemPrice);
		
		addNewInvPriceTxt = new JTextField();
		addNewInvPriceTxt.setBounds(237, 189, 283, 20);
		addNewInvPriceTxt.setColumns(10);
		addNewItemPanel.add(addNewInvPriceTxt);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewInventoryWorker(e);
			}
		});
		btnNewButton.setBounds(431, 250, 89, 23);
		addNewItemPanel.add(btnNewButton);
		
		CancelAddingInventory = new JButton("Go Back");
		CancelAddingInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(false);
				newItemConfirm.setVisible(false);
			}
		});
		CancelAddingInventory.setBounds(313, 250, 89, 23);
		addNewItemPanel.add(CancelAddingInventory);
		
		ConfirmationMsg = new JPanel();
		ConfirmationMsg.setBounds(0, 0, 774, 598);
		frmPosSoftware.getContentPane().add(ConfirmationMsg);
		ConfirmationMsg.setLayout(null);
		
		JLabel lblEditSuccess = new JLabel("Operation Completed Successfully");
		lblEditSuccess.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEditSuccess.setBounds(222, 188, 351, 34);
		ConfirmationMsg.add(lblEditSuccess);
		
		JButton btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(false);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(false);
				newItemConfirm.setVisible(false);
			}
		});
		btnGoBack.setBounds(325, 260, 89, 23);
		ConfirmationMsg.add(btnGoBack);
		
		
	}

	

	// -------------------Button Action Functions ------------------------------

	
	//--------------------Add Item Button Worker Function------------------------
	
	
	public void addNewItemButtonAction(ActionEvent e) {

	//-----------Filtering inputs----------------------
		
		try{
			id = Integer.parseInt(ItemIDField.getText());
			quantity = Integer.parseInt(QtyField.getText());
			invalidInput = false;																//Checking whether the user entered valid number or not
			
		}catch(Exception e1){
			invalidInput = true;
			JOptionPane.showMessageDialog(frmPosSoftware,  "                Invalid input Format\nPlease Enter Valid ItemID & Quantity");
			ItemIDField.setText("");
			QtyField.setText("");
		}
		
		if(!saleInit){																			//Checking Sale status before adding item
			JOptionPane.showMessageDialog(frmPosSoftware,  "Please Start a Sale First");
			ItemIDField.setText("");
			QtyField.setText("");
		}
		else{					
			
			if(invalidInput)	
				return;
			
			else if (psc.getProductSpec(id) == null) {	
				
				error = true;	
				JOptionPane.showMessageDialog(frmPosSoftware,  "No Such Item Found!!!");		//Checking item's validity 
				ItemIDField.setText("");
				QtyField.setText("");
			}
		
			else {
				try {
					psc.addItem(id, quantity);
					ItemIDField.setText("");
					QtyField.setText("");
					error = false;
					noDiscount =false;
					emptyCart = false;
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
//------------------New Sale Button Worker Function-------------------------
	
	
	public void newSaleButtonAction(ActionEvent e) {
		try {
			psc = ProcessSaleController.getPscInstance();
			beep = new Beeper();																//initiating the beeper upon sale starting
			psc.makeNewSale();			
			this.register(psc);																	//Property Listener
			beep.register(psc);
			
			
			
			counter = 0;
			saleInit = true;																	//Marking the button action of "New Sale" button
			noDiscount = true;
			emptyCart = true;
			DateText.setText(psc.sale.getDate());
			

		} catch (Exception e2) {
			System.out.println("New Sale Button Exception");
		}

	}

//----------------------calculate Discount button action----------------------
	
	
	public void calculateDiscountButtonAction(ActionEvent e){
		
		
		
		if(selectIndex==1){
			psc.selectedPricingStrategy("SeniorCitizenDiscount");
		}
		
		
		if(selectIndex==2){
			 psc.selectedPricingStrategy("EidDiscount");
		}
		
		
		if(selectIndex==3){
			psc.selectedPricingStrategy("CompositeBestForStorePS");
		}
		
		if(selectIndex==4){
			psc.selectedPricingStrategy("CompositeBestForCustomerPS");
		}
		
		
		
	}

	//---------------------Registering the JFrame to Sale----------------------------------------
	
	public void register(ProcessSaleController psc){
		psc.sale.addPropertyListener(this);
	}

//---------------------------Updating total immediately upon sale's update-----------------------------------
	
	public void onPropertyEvent(Sale source, String name, int value) {
		 
		if(name.equals("sale.total")){		
			
			 
			 TotalCost.setText(Integer.toString(value));
			 
		 }
		
		if(name.equals("vat.Amount")){		 			
			
			
			 vatAmount.setText(Integer.toString(value));
			 
		 }
		
		
	}
	
	//----------------------Show Product Spec------------------------
	public void showProductSpecForEdit(ActionEvent e) {
		try{
			id = Integer.parseInt(EditItemID.getText());			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		if(ProcessSaleController.getPscInstance().getProductSpec(id)==null){
			JOptionPane.showMessageDialog(frmPosSoftware, "Item Not Found!!");
			EditItemID.setText("");
			return;
			}
		ps = ProcessSaleController.getPscInstance().getProductSpec(id);
		
		home.setVisible(false);
		panel.setVisible(false);
		confrimDialogue.setVisible(false);
		ConfirmationMsg.setVisible(false);
		psEnterIDMenu.setVisible(false);
		psLoadedItem.setVisible(true);
		addNewItemPanel.setVisible(false);
		newItemConfirm.setVisible(false);
		//-----------------Show Product Spec-----------------------
		
		ProductNameEditTxt.setText(ps.getName());
		productIDEditTxt.setText(String.valueOf(ps.getId()));
		UnitPriceEditTxt.setText(String.valueOf(ps.price));
		
		
	}
	
	//-------------Save Edit------------------------
	
	public void editItemSaveButtonWorker(ActionEvent e){
		String eName=null;
		String ePrice = null;
		String eID = null; 
		try {
			eName = ProductNameEditTxt.getText();
			eID = productIDEditTxt.getText();
			ePrice = UnitPriceEditTxt.getText();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		ps.setName(eName);
		ps.setId(Integer.parseInt(eID));
		ps.setPrice(Integer.parseInt(ePrice));
		ps.setSate(new OldDirtyState());
		
		//-------------------------
		home.setVisible(false);
		panel.setVisible(false);
		confrimDialogue.setVisible(true);
		ConfirmationMsg.setVisible(false);
		psEnterIDMenu.setVisible(false);
		psLoadedItem.setVisible(false);
		addNewItemPanel.setVisible(false);
		newItemConfirm.setVisible(false);
		//----------------Show Confirm Window------------
		confirmIDTxt.setText(eID);
		confirmNametxt.setText(eName);
		confirmPriceTxt.setText(ePrice);
		
		
		//ProcessSaleController.getPscInstance().saveEditData(ps);
		
	}
	
	public void editConfirmButtonWorker(ActionEvent e){
		ps.commit();
		//----------------------------
		home.setVisible(false);
		panel.setVisible(false);
		confrimDialogue.setVisible(false);
		ConfirmationMsg.setVisible(true);
		psEnterIDMenu.setVisible(false);
		psLoadedItem.setVisible(false);
		addNewItemPanel.setVisible(false);
		newItemConfirm.setVisible(false);
	}
	
	public void addNewInventoryWorker(ActionEvent e){
		String name = null;
		String price= null;
		try{
			name = addNewInventoryTxt.getText();
			price = addNewInvPriceTxt.getText();
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		ps = new ProductSpecification();
		ps.setName(name);
		ps.setPrice(Integer.parseInt(price));
		ps.setSate(new NewState());
		
		home.setVisible(false);
		panel.setVisible(false);
		confrimDialogue.setVisible(false);
		ConfirmationMsg.setVisible(false);
		psEnterIDMenu.setVisible(false);
		psLoadedItem.setVisible(false);
		addNewItemPanel.setVisible(false);
		newItemConfirm.setVisible(true);
		//-----------------Show Product Spec-----------------------
		
		newItemConNameTxt.setText(ps.getName());
		newItemConPriceTxt.setText(String.valueOf(ps.getPrice()));		
		
	}
	public void newInvetoryAddSaveWorker(ActionEvent e) {
		ps.commit();
		//---------------------------
		//----------------------------
				home.setVisible(false);
				panel.setVisible(false);
				confrimDialogue.setVisible(false);
				ConfirmationMsg.setVisible(true);
				psEnterIDMenu.setVisible(false);
				psLoadedItem.setVisible(false);
				addNewItemPanel.setVisible(false);
				newItemConfirm.setVisible(false);
	}
	
	public void deleteButtonActionWorker(ActionEvent e){
		ps.setSate(new OldDeleteState());
		//-----
		home.setVisible(false);
		panel.setVisible(false);
		confrimDialogue.setVisible(true);
		ConfirmationMsg.setVisible(false);
		psEnterIDMenu.setVisible(false);
		psLoadedItem.setVisible(false);
		addNewItemPanel.setVisible(false);
		newItemConfirm.setVisible(false);
		//----------------Show Confirm Window------------
		
		confirmIDTxt.setText(String.valueOf(ps.getId()));
		confirmNametxt.setText(ps.getName());
		confirmPriceTxt.setText(String.valueOf(ps.getPrice()));
		
	}
	
	public void rollbackButtonActionWorker(ActionEvent e){
		ps.rollback();
		//-----
		home.setVisible(false);
		panel.setVisible(false);
		confrimDialogue.setVisible(false);
		ConfirmationMsg.setVisible(true);
		psEnterIDMenu.setVisible(false);
		psLoadedItem.setVisible(false);
		addNewItemPanel.setVisible(false);
		newItemConfirm.setVisible(false);
		
		
		
	}
}
