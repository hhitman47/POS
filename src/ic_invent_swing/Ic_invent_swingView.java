/*
 * Ic_invent_swingView.java
 */

package ic_invent_swing;

import com.mysql.jdbc.Statement;
import java.awt.RenderingHints.Key;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabSet;
import org.apache.commons.lang.NumberUtils;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The application's main frame.
 */
public class Ic_invent_swingView extends FrameView {


        String s= "";
    int count = 1;
    float aqt= 1;
    int tItems=0;
    float tPrice=0;
    float tq = 0;
    float tp =0;
    float td=0;
    String name = null ;
    float psale = 0;
    float dsale = 0;
    String d_db =null;
    int no;
    ResultSet bill_ID;
    private  int lastId;
    long test;
    double bdb=0;

  private final static int START_X = 8;
  private final static int START_Y = 41;
  private final static int ONE_PAGE_ITEMS = 25;
  private final static int SPACE_PER_ITEM = 15;

  private List toPrintList;
  private int pageValue;
  private List printBill;




     public int getLastId() {
		return lastId;
	}

	public void setLastId(int lastId) {
		this.lastId = lastId;
	}

    public ResultSet getBill_ID() {
		return bill_ID;
	}

	public void setBill_ID(ResultSet bill_ID) {
		this.bill_ID = bill_ID;
	}


     public static Connection getConnection() throws Exception {

    	String url = "jdbc:mysql://localhost:3306/ic_invent";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        Class.forName(driver);

        Connection conn = (Connection) DriverManager.getConnection(url, userName, password);
        return conn;
      }

    public Ic_invent_swingView(SingleFrameApplication app) {
        super(app);
//        sb = new StringBuilder();

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
               
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                     
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                   
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                   
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                   
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                   
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Ic_invent_swingApp.getApplication().getMainFrame();
            aboutBox = new Ic_invent_swingAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Ic_invent_swingApp.getApplication().show(aboutBox);
    }

    private void billPrint(String itemName, double price, double quantity, int rowNumber) {

        System.out.println(rowNumber + " " + itemName + " " + quantity + " " + price );
        printBill = new ArrayList<String>();
        printBill.add(rowNumber + " " + itemName + " " + quantity + " " + price );

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labBarcode = new javax.swing.JLabel();
        txtMBarcode = new javax.swing.JTextField();
        labPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        labMItemName = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        panal2 = new javax.swing.JPanel();
        labAmount = new javax.swing.JLabel();
        labAmountShow = new javax.swing.JLabel();
        labItem = new javax.swing.JLabel();
        labItemShow = new javax.swing.JLabel();
        labTotalCash = new javax.swing.JLabel();
        labTotalCashShow = new javax.swing.JLabel();
        labbalance = new javax.swing.JLabel();
        labBalanceShow = new javax.swing.JLabel();
        labQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        labDiscount = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        labSalesTax = new javax.swing.JLabel();
        txtSalesTax = new javax.swing.JTextField();
        txtScannedBarcode = new javax.swing.JTextField();
        labScannedBarcode = new javax.swing.JLabel();
        labItemName = new javax.swing.JLabel();
        cboxItemName = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabItemList = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        cBoxPaymentType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        labLoanTO = new javax.swing.JLabel();
        txtTotalCash = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLoanTo = new javax.swing.JTextField();
        btnPritn = new javax.swing.JButton();
        btnTotal = new javax.swing.JButton();
        btnRemoveItem = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnErase = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ic_invent_swing.Ic_invent_swingApp.class).getContext().getResourceMap(Ic_invent_swingView.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel2KeyPressed(evt);
            }
        });

        labBarcode.setText(resourceMap.getString("labBarcode.text")); // NOI18N
        labBarcode.setName("labBarcode"); // NOI18N
        labBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labBarcodeKeyPressed(evt);
            }
        });

        txtMBarcode.setName("txtMBarcode"); // NOI18N
        txtMBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMBarcodeKeyPressed(evt);
            }
        });

        labPrice.setText(resourceMap.getString("labPrice.text")); // NOI18N
        labPrice.setName("labPrice"); // NOI18N
        labPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labPriceKeyPressed(evt);
            }
        });

        txtPrice.setName("txtPrice"); // NOI18N
        txtPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPriceMouseClicked(evt);
            }
        });
        txtPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPriceFocusLost(evt);
            }
        });
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
        });

        labMItemName.setText(resourceMap.getString("labMItemName.text")); // NOI18N
        labMItemName.setName("labMItemName"); // NOI18N
        labMItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labMItemNameKeyPressed(evt);
            }
        });

        txtItemName.setName("txtItemName"); // NOI18N
        txtItemName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtItemNameMouseClicked(evt);
            }
        });
        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });
        txtItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtItemNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtItemNameFocusLost(evt);
            }
        });
        txtItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemNameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labPrice)
                            .addComponent(labBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labMItemName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(txtMBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
                .addContainerGap(31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labBarcode)
                    .addComponent(txtMBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPrice)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labMItemName))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        panal2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panal2.setName("panal2"); // NOI18N
        panal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panal2KeyPressed(evt);
            }
        });

        labAmount.setText(resourceMap.getString("labAmount.text")); // NOI18N
        labAmount.setName("labAmount"); // NOI18N
        labAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labAmountKeyPressed(evt);
            }
        });

        labAmountShow.setFont(resourceMap.getFont("labAmountShow.font")); // NOI18N
        labAmountShow.setForeground(resourceMap.getColor("labAmountShow.foreground")); // NOI18N
        labAmountShow.setText(resourceMap.getString("labAmountShow.text")); // NOI18N
        labAmountShow.setName("labAmountShow"); // NOI18N

        labItem.setText(resourceMap.getString("labItem.text")); // NOI18N
        labItem.setName("labItem"); // NOI18N
        labItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labItemKeyPressed(evt);
            }
        });

        labItemShow.setFont(resourceMap.getFont("labItemShow.font")); // NOI18N
        labItemShow.setForeground(resourceMap.getColor("labItemShow.foreground")); // NOI18N
        labItemShow.setText(resourceMap.getString("labItemShow.text")); // NOI18N
        labItemShow.setName("labItemShow"); // NOI18N
        labItemShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labItemShowKeyPressed(evt);
            }
        });

        labTotalCash.setText(resourceMap.getString("labTotalCash.text")); // NOI18N
        labTotalCash.setName("labTotalCash"); // NOI18N
        labTotalCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labTotalCashKeyPressed(evt);
            }
        });

        labTotalCashShow.setFont(resourceMap.getFont("labTotalCashShow.font")); // NOI18N
        labTotalCashShow.setForeground(resourceMap.getColor("labTotalCashShow.foreground")); // NOI18N
        labTotalCashShow.setText(resourceMap.getString("labTotalCashShow.text")); // NOI18N
        labTotalCashShow.setName("labTotalCashShow"); // NOI18N
        labTotalCashShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labTotalCashShowKeyPressed(evt);
            }
        });

        labbalance.setText(resourceMap.getString("labbalance.text")); // NOI18N
        labbalance.setName("labbalance"); // NOI18N
        labbalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labbalanceKeyPressed(evt);
            }
        });

        labBalanceShow.setFont(resourceMap.getFont("labBalanceShow.font")); // NOI18N
        labBalanceShow.setForeground(resourceMap.getColor("labBalanceShow.foreground")); // NOI18N
        labBalanceShow.setText(resourceMap.getString("labBalanceShow.text")); // NOI18N
        labBalanceShow.setName("labBalanceShow"); // NOI18N
        labBalanceShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labBalanceShowKeyPressed(evt);
            }
        });

        labQuantity.setText(resourceMap.getString("labQuantity.text")); // NOI18N
        labQuantity.setName("labQuantity"); // NOI18N
        labQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labQuantityKeyPressed(evt);
            }
        });

        txtQuantity.setName("txtQuantity"); // NOI18N
        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantityFocusLost(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });

        labDiscount.setText(resourceMap.getString("labDiscount.text")); // NOI18N
        labDiscount.setName("labDiscount"); // NOI18N
        labDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labDiscountKeyPressed(evt);
            }
        });

        txtDiscount.setName("txtDiscount"); // NOI18N
        txtDiscount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiscountFocusLost(evt);
            }
        });
        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiscountKeyPressed(evt);
            }
        });

        labSalesTax.setText(resourceMap.getString("labSalesTax.text")); // NOI18N
        labSalesTax.setName("labSalesTax"); // NOI18N
        labSalesTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labSalesTaxKeyPressed(evt);
            }
        });

        txtSalesTax.setName("txtSalesTax"); // NOI18N
        txtSalesTax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSalesTaxFocusLost(evt);
            }
        });
        txtSalesTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSalesTaxKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panal2Layout = new javax.swing.GroupLayout(panal2);
        panal2.setLayout(panal2Layout);
        panal2Layout.setHorizontalGroup(
            panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panal2Layout.createSequentialGroup()
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panal2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labTotalCash, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labbalance, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labItem, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labAmount, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labItemShow, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labTotalCashShow, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(labAmountShow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(labBalanceShow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                    .addGroup(panal2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labDiscount)
                            .addComponent(labSalesTax)
                            .addComponent(labQuantity))
                        .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panal2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDiscount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panal2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSalesTax, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panal2Layout.setVerticalGroup(
            panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panal2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labAmount)
                    .addComponent(labAmountShow, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labItem)
                    .addComponent(labItemShow, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTotalCash)
                    .addComponent(labTotalCashShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labbalance)
                    .addComponent(labBalanceShow, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labQuantity))
                .addGap(8, 8, 8)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalesTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labSalesTax))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtScannedBarcode.setName("txtScannedBarcode"); // NOI18N
        txtScannedBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtScannedBarcodeActionPerformed(evt);
            }
        });
        txtScannedBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtScannedBarcodeKeyPressed(evt);
            }
        });

        labScannedBarcode.setText(resourceMap.getString("labScannedBarcode.text")); // NOI18N
        labScannedBarcode.setName("labScannedBarcode"); // NOI18N
        labScannedBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labScannedBarcodeKeyPressed(evt);
            }
        });

        labItemName.setText(resourceMap.getString("labItemName.text")); // NOI18N
        labItemName.setName("labItemName"); // NOI18N
        labItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labItemNameKeyPressed(evt);
            }
        });

        cboxItemName.setName("cboxItemName"); // NOI18N
        cboxItemName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboxItemNameMouseClicked(evt);
            }
        });
        cboxItemName.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboxItemNamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cboxItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboxItemNameFocusLost(evt);
            }
        });
        cboxItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboxItemNameKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tabItemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No #", "Item Name", "Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabItemList.setName("tabItemList"); // NOI18N
        tabItemList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabItemListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabItemList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnClear.setText(resourceMap.getString("btnClear.text")); // NOI18N
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        btnClear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnClearKeyPressed(evt);
            }
        });

        cBoxPaymentType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cash", "Credit Card", "Loan" }));
        cBoxPaymentType.setName("cBoxPaymentType"); // NOI18N
        cBoxPaymentType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cBoxPaymentTypeKeyPressed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel5KeyPressed(evt);
            }
        });

        labLoanTO.setText(resourceMap.getString("labLoanTO.text")); // NOI18N
        labLoanTO.setName("labLoanTO"); // NOI18N
        labLoanTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labLoanTOKeyPressed(evt);
            }
        });

        txtTotalCash.setName("txtTotalCash"); // NOI18N
        txtTotalCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalCashActionPerformed(evt);
            }
        });
        txtTotalCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalCashKeyPressed(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel7KeyPressed(evt);
            }
        });

        txtLoanTo.setName("txtLoanTo"); // NOI18N
        txtLoanTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoanToKeyPressed(evt);
            }
        });

        btnPritn.setText(resourceMap.getString("btnPritn.text")); // NOI18N
        btnPritn.setName("btnPritn"); // NOI18N
        btnPritn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPritnActionPerformed(evt);
            }
        });
        btnPritn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPritnKeyPressed(evt);
            }
        });

        btnTotal.setText(resourceMap.getString("btnTotal.text")); // NOI18N
        btnTotal.setName("btnTotal"); // NOI18N
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });
        btnTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTotalKeyPressed(evt);
            }
        });

        btnRemoveItem.setText(resourceMap.getString("btnRemoveItem.text")); // NOI18N
        btnRemoveItem.setName("btnRemoveItem"); // NOI18N
        btnRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemActionPerformed(evt);
            }
        });
        btnRemoveItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRemoveItemKeyPressed(evt);
            }
        });

        btnAdd.setText(resourceMap.getString("btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        btnAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddKeyPressed(evt);
            }
        });

        btnErase.setText(resourceMap.getString("btnErase.text")); // NOI18N
        btnErase.setName("btnErase"); // NOI18N
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });
        btnErase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEraseKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnErase, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(labScannedBarcode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtScannedBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labItemName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxItemName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(labLoanTO)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalCash, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLoanTo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPritn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(cBoxPaymentType, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labScannedBarcode)
                    .addComponent(txtScannedBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labItemName)
                    .addComponent(cboxItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear))
                .addGap(11, 11, 11)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cBoxPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLoanTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labLoanTO))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTotalCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoveItem))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnErase)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPritn))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ic_invent_swing.Ic_invent_swingApp.class).getContext().getActionMap(Ic_invent_swingView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyPressed
        // TODO add your handling code here:
       
}//GEN-LAST:event_jLabel2KeyPressed

    private void labBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labBarcodeKeyPressed
        // TODO add your handling code here:
        
}//GEN-LAST:event_labBarcodeKeyPressed

    private void txtMBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMBarcodeKeyPressed
        // TODO add your handling code here:
       
}//GEN-LAST:event_txtMBarcodeKeyPressed

    private void labPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labPriceKeyPressed
        // TODO add your handling code here:
       
}//GEN-LAST:event_labPriceKeyPressed

    private void txtPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPriceFocusLost

    //     if(txtPrice.getText().length()!=0 ){
            if(txtPrice.getText().length()!=0 && NumberUtils.isDigits(txtPrice.getText())== false){
                JOptionPane.showMessageDialog(null,"Invalid Number" );
                labPrice.setForeground(new java.awt.Color(255, 0, 0));
                txtPrice.setForeground(new java.awt.Color(255, 0, 0));
      //      }
         }

    }//GEN-LAST:event_txtPriceFocusLost

    private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtPriceKeyPressed

    private void labMItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labMItemNameKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labMItemNameKeyPressed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:

        btnAddActionPerformed(null);
       /* if(txtItemName.getText().length()!=0 && txtPrice.getText().length() !=0) {

            
                labMItemName.setForeground(new java.awt.Color(0, 0, 0));
                txtItemName.setForeground(new java.awt.Color(0, 0, 0));
                btnAddActionPerformed(evt);
            
        } else
            JOptionPane.showMessageDialog(null,"Please Enter the Price & Item Name " );
        * */
}//GEN-LAST:event_txtItemNameActionPerformed

    private void txtItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusLost
        // TODO add your handling code here:
        
         
}//GEN-LAST:event_txtItemNameFocusLost

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtItemNameKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_jPanel2KeyPressed

    private void labAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labAmountKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labAmountKeyPressed

    private void labItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labItemKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labItemKeyPressed

    private void labItemShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labItemShowKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labItemShowKeyPressed

    private void labTotalCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labTotalCashKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labTotalCashKeyPressed

    private void labTotalCashShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labTotalCashShowKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labTotalCashShowKeyPressed

    private void labbalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labbalanceKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labbalanceKeyPressed

    private void labBalanceShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labBalanceShowKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labBalanceShowKeyPressed

    private void labQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labQuantityKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labQuantityKeyPressed

       private int getkeys (java.awt.event.KeyEvent evt){

     //   System.out.println("Key Char  $  Key Code = "+evt.getKeyChar()+ "   &   "+evt.getKeyCode());

        return getValue(evt);

    }

    private int getValue (java.awt.event.KeyEvent evt){

        if(evt.getKeyCode()==27 || evt.getKeyCode()== 115 || evt.getKeyCode()== 120 || evt.getKeyCode()== 119 || evt.getKeyCode()== 118 || evt.getKeyCode()== 121){

                if(evt.getKeyCode() == 27){    // Esc for Clear
                   btnClearActionPerformed(null);
                    return 0;
                }
                if(evt.getKeyCode() == 115){   // F4 for Total
                   btnTotalActionPerformed(null);
                   return 0;
                }
                if(evt.getKeyCode() == 120){   // F9 for Print
                   btnPritnActionPerformed(null);
                   return 0;
                }
                if(evt.getKeyCode() == 119){   // F8 for Add Item
                   btnAddActionPerformed(null);
                   return 0;
                }
                if(evt.getKeyCode() == 118){   // F7 for Request Focus Quantity Filed
                   txtQuantity.requestFocus();
                   return 0;
                }
                if(evt.getKeyCode() == 121){   // F10 for Remove Item s
                   btnRemoveItemActionPerformed(null);
                   return 0;
                }

            }

        return 1;

    }
    
    private void txtQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantityFocusLost
        // TODO add your handling code here:
        
}//GEN-LAST:event_txtQuantityFocusLost

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtQuantityKeyPressed

    private void labDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labDiscountKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labDiscountKeyPressed

    private void txtDiscountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiscountFocusLost
        // TODO add your handling code here:
        
}//GEN-LAST:event_txtDiscountFocusLost

    private void txtDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtDiscountKeyPressed

    private void labSalesTaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labSalesTaxKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labSalesTaxKeyPressed

    private void txtSalesTaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalesTaxFocusLost
        // TODO add your handling code here:
       
}//GEN-LAST:event_txtSalesTaxFocusLost

    private void txtSalesTaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalesTaxKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtSalesTaxKeyPressed

    private void panal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panal2KeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_panal2KeyPressed

    private void txtScannedBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScannedBarcodeActionPerformed
        // TODO add your handling code here:
        float p = 100;
        float  d=0;
        float price =0;
        float s=0;
        float pdb=0;
        float qdb=0;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date da = new Date();

        d_db= dateFormat.format(da);

        String ss = txtSalesTax.getText();
        String qs = txtQuantity.getText();
        String ds = txtDiscount.getText();

        if(qs.length()> 0 && Float.parseFloat(txtQuantity.getText()) > 0  )
            aqt = Float.parseFloat(txtQuantity.getText());

        if(ds.length() > 0 && Float.parseFloat(txtDiscount.getText()) > 0)
            d = Float.parseFloat(txtDiscount.getText());

        if(ss.length()> 0 && Float.parseFloat(txtSalesTax.getText()) > 0 ) {
            s = Float.parseFloat(txtSalesTax.getText());
            s = (s/100)*p;
        }
        /////////////////////////////////
        // update items table
        Connection conn = null;
        PreparedStatement psp = null;
        PreparedStatement pstmtsales = null;


        try {
            conn = getConnection();
            System.out.println("Conn to BD now get Price");
            psp = (PreparedStatement) conn.prepareStatement("SELECT name,price,stock_quantity FROM items WHERE barcode= '"+txtScannedBarcode.getText()+"' ");
            // Statement stm = (Statement) conn.createStatement();

            ResultSet rsp ;
            rsp =psp.executeQuery();
            while ( rsp.next() ) {
                name=rsp.getString("name");
                pdb=rsp.getInt("price");
                qdb=rsp.getInt("stock_quantity");
            }
        } catch (Exception e) {
            e.printStackTrace(); }

        if(name!=null) {
            try {
                p = (float) pdb;
                System.out.println("P =" + p);
                price = (float) (d/100) * p;
                price = (float) p - price;
                price = (float) s + price;
                price = (float) aqt * price;
                System.out.println("qdb = " + qdb);
                if (aqt > 1) {
                    try {
                        float a = qdb - aqt;
                        System.out.println("A = " + a);
                        psp.executeUpdate("update items set stock_quantity  = " + a + " where items.barcode = '" + txtScannedBarcode.getText() + "'");
                    
                    } catch (SQLException ex) {
                        Logger.getLogger(Ic_invent_swingView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (aqt == 1) {
                        try {
                            float a = qdb - 1;
                            System.out.println("A = " + a);
                            psp.executeUpdate("update items set stock_quantity  = " + a + " where items.barcode = '" + txtScannedBarcode.getText() + "'");
                        } catch (SQLException ex) {
                            Logger.getLogger(Ic_invent_swingView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                psale = price;
                dsale = d;
                System.out.println("price =" + price);
                System.out.println("psale =" + psale);
                System.out.println("dsale =" + dsale);
                System.out.println("Time Date =" + d_db);
                no++;
                //no = tabItemList.getRowCount() + 1;
                System.out.println("Loan to = "+txtLoanTo.getText().toString());
                String INSERT_RECORD = "INSERT INTO sales(item_name,price,discount,quantity,date_time,number,barcode,payment_type,item_in_db,loan_to) VALUES ('" +name+ "','" +psale+ "','" + dsale+ "','" + aqt+ "','"+ d_db+"', '"+no +"','"+txtScannedBarcode.getText()+"','" + cBoxPaymentType.getSelectedItem()+ "','"+1+"','"+txtLoanTo.getText()+"')";

                billPrint((String)name,(double)psale,(double)aqt,(int)tabItemList.getRowCount());

                pstmtsales = (PreparedStatement) conn.prepareStatement(INSERT_RECORD);
                pstmtsales.execute();

                if (tabItemList.getRowCount() == 0) {
                    try {
                        pstmtsales = (PreparedStatement) conn.prepareStatement("SELECT last_insert_id() ");
                        bill_ID = pstmtsales.executeQuery();
                        while (bill_ID.next()) {
                            System.out.println("ID : " + bill_ID.getInt("last_insert_id()"));
                            setLastId(bill_ID.getInt("last_insert_id()"));
                        }
                        test = getLastId();
                        pstmtsales.execute("update sales set bill_id  = " + getLastId() + " where sales.id = '" + test + "'");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    test = test + 1;
                    pstmtsales.execute("update sales set bill_id  = " + getLastId() + " where sales.id = '" + test + "'");
                }
                ///
                psp.close();
                conn.close();
                DefaultTableModel m = (DefaultTableModel) tabItemList.getModel();
                tq = aqt + tq;
                tp = tp + price;
                m.insertRow(tabItemList.getRowCount(), new Object[]{tabItemList.getRowCount() + 1, name, "1 X " + aqt + "=" + aqt, (p + .01) + " X " + aqt + "=" + price});
                /////////////////////////////////////////
                /////////////////////////////////
                txtScannedBarcode.requestFocus();
                labItemShow.setText(Float.toString(tq));
                labAmountShow.setText(Integer.toString((int) tp));
                txtQuantity.setText(null);
                txtDiscount.setText(null);
                txtSalesTax.setText(null);
                txtLoanTo.setText(null);
                aqt = 1;
                ////////////////////////////////////////
                txtScannedBarcode.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(Ic_invent_swingView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }  /// if name != null close
        else {
            txtMBarcode.setText(txtScannedBarcode.getText());
            txtScannedBarcode.setText("");
        }
       
    }//GEN-LAST:event_txtScannedBarcodeActionPerformed

    private void txtScannedBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtScannedBarcodeKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtScannedBarcodeKeyPressed

    private void labScannedBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labScannedBarcodeKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labScannedBarcodeKeyPressed

    private void labItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labItemNameKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labItemNameKeyPressed

    private void cboxItemNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxItemNameMouseClicked
        // TODO add your handling code here:
        //sb = new StringBuilder("");
}//GEN-LAST:event_cboxItemNameMouseClicked

    private void cboxItemNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboxItemNamePopupMenuWillBecomeInvisible
        // TODO add your handling code here:

        System.out.println("Count in Invisible method = "+count );
        int temp = 0;
        if(cboxItemName.getSelectedItem()!=null) {

            float p = 100;
            float d=0;
            float price =0;
            float s=0;
            float pdb=0;
            float qdb=0;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date da = new Date();

            d_db= dateFormat.format(da);
            String ss = txtSalesTax.getText();
            String qs = txtQuantity.getText();
            String ds = txtDiscount.getText();

            if(qs.length()> 0 && Float.parseFloat(txtQuantity.getText()) > 0  )
                aqt = Float.parseFloat(txtQuantity.getText());

            if(ds.length() > 0 && Float.parseFloat(txtDiscount.getText()) > 0)
                d = Float.parseFloat(txtDiscount.getText());

            if(ss.length()> 0 && Float.parseFloat(txtSalesTax.getText()) > 0 ) {
                s = Float.parseFloat(txtSalesTax.getText());
                s = (s/100)*p;
            }

            /////////////////////////////////
            // update items table
            Connection conn = null;
            PreparedStatement psp = null;
            PreparedStatement stmtsales = null;

            try {
                conn = getConnection();
             //   System.out.println("Conn to BD now get Price");
                psp = (PreparedStatement) conn.prepareStatement("SELECT price,stock_quantity,barcode FROM items WHERE name= '"+cboxItemName.getSelectedItem()+"' ");
                Statement stm = (Statement) conn.createStatement();

                ResultSet rsp ;
                rsp =psp.executeQuery();
                while ( rsp.next() ) {
                    pdb=rsp.getInt("price");
                    qdb=rsp.getFloat("stock_quantity");
                    bdb=rsp.getDouble("barcode");
                }
                p	= (float) pdb ;
                price = (float) (d/100)*p;
                price = (float)p - price ;
                price = (float)s + price ;
                price = (float)  aqt*price;

                System.out.println("qdb = "+qdb);
                if(aqt > 1 ){
                    Float a= qdb-aqt;
                  //  System.out.println("A = "+a);
                    stm.executeUpdate("update items set stock_quantity  = " + a +" where items.name = '" + cboxItemName.getSelectedItem()+ "'");
                } else {
                    Float a= qdb-1;
                   // System.out.println("A = "+a);
                    stm.executeUpdate("update items set stock_quantity  = " + a +" where items.name = '" + cboxItemName.getSelectedItem()+ "'");
                }
                /// update  sales table
                psale = price;
                dsale = d;
                System.out.println("Time Date ="+d_db);

               // System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
                no++;
                //no = tabItemList.getRowCount()+1;
                //String insert_r ="INSERT INTO sales(item_name,price,discount,quantity,date_time,number,payment_type,item_in_db) VALUES ('" + jComboBox1.getSelectedItem()+ "','" +psale+ "','" + dsale+ "','" + aqt+ "','"+ d_db+"', '"+no +"','" + jComboBox2.getSelectedItem()+ "','"+1+"')";

                String insert_r ="INSERT INTO sales(item_name,price,discount,quantity,date_time,number,barcode,payment_type,item_in_db,loan_to) VALUES ('" + cboxItemName.getSelectedItem()+ "','" +psale+ "','" + dsale+ "','" + aqt+ "','"+ d_db+"', '"+no +"','"+bdb+"','" + cBoxPaymentType.getSelectedItem()+ "','"+1+"','"+txtLoanTo.getText()+"')";

                 billPrint((String)cboxItemName.getSelectedItem(),(double)psale,(double)aqt,(int)tabItemList.getRowCount());

                stmtsales = (PreparedStatement) conn.prepareStatement(insert_r);
                stmtsales.execute();
                ////////////////////////
                if(tabItemList.getRowCount()==0) {
                    try {
                        stmtsales = (PreparedStatement) conn.prepareStatement("SELECT last_insert_id() ");
                        bill_ID = stmtsales.executeQuery();
                        while (bill_ID.next() ) {
                            System.out.println("ID : " + bill_ID.getInt("last_insert_id()"));
                            setLastId(bill_ID.getInt("last_insert_id()"));
                        }
                        test = getLastId();
                        stmtsales.execute("update sales set bill_id  = " +getLastId() +" where sales.id = '"+ test+"'");

                    } catch (Exception e) {
                        e.printStackTrace(); }
                } else {
                    test= test+1;
                    stmtsales.execute("update sales set bill_id  = " +getLastId() +" where sales.id = '" +test+"'");
                }
                //////////////////////
                psp.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();}

            DefaultTableModel m = (DefaultTableModel)tabItemList.getModel();
            if(cboxItemName.getSelectedIndex()> -1){
                tq = aqt+tq;
                tp = tp+ price;
                m.insertRow(tabItemList.getRowCount() , new Object[] {tabItemList.getRowCount()+1,cboxItemName.getSelectedItem() ,"1 X "+aqt + "="+aqt ,(p+.01)+" X "+aqt+"="+ price} );

             //   sb = new StringBuilder("");
                txtScannedBarcode.requestFocus();
                cboxItemName.removeAllItems();
                labItemShow.setText(Float.toString(tq));
                labAmountShow.setText(Integer.toString((int)tp));
                txtQuantity.setText(null);
                txtDiscount.setText(null);
                txtSalesTax.setText(null);
                txtLoanTo.setText(null);
                aqt=1;
            }
        }
            System.out.println("SB Length = "+ sb.length());
        if (sb.length() > 0)
             temp = sb.length()-1;
        if(emptySB == true){
             sb.delete(0, sb.length()+1);
             System.out.println("Inside -------------");
        }
             emptySB=false;
        
}//GEN-LAST:event_cboxItemNamePopupMenuWillBecomeInvisible

    private void cboxItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboxItemNameFocusLost
        // TODO add your handling code here:

}//GEN-LAST:event_cboxItemNameFocusLost

    private void cboxItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboxItemNameKeyPressed
        // TODO add your handling code here:
        //   if(getkeys(evt)== 1){
        System.out.println("Key code = "+evt.getKeyCode());
        System.out.println("Key Text = "+evt.getKeyText(evt.getKeyCode()));
        System.out.println("Key char = "+evt.getKeyChar());
        javax.swing.DefaultComboBoxModel  dcbm = new javax.swing.DefaultComboBoxModel();


        if(evt.getKeyCode()==10) {
            emptySB = true;
            System.out.println("Enter Key hit" );
            cboxItemNamePopupMenuWillBecomeInvisible(null);
            
        }

        if(evt.getKeyCode() != 37 && evt.getKeyCode() != 38 && evt.getKeyCode() != 39 && evt.getKeyCode() != 40 && evt.getKeyCode()!=10 && evt.getKeyCode()!=8) {
            cboxItemName.setModel(dcbm);


                System.out.println("Count 2  ");

                sb.append(evt.getKeyChar());
                System.out.println("sb going into db = "+sb.toString());
                dcbm.addElement(sb.toString());
                Connection conn = null;
                PreparedStatement ps = null;

                try {
                    conn = getConnection();
                    String s = sb.toString() + "%";

                    System.out.println("Connected to the database" + s);
                    ps = (PreparedStatement) conn.prepareStatement("SELECT name FROM items WHERE name LIKE ?");

                    ps.setString(1, s);
                    ResultSet rs;
                    rs =ps.executeQuery();
                    cboxItemName.removeAllItems();
                    while ( rs.next() ) {
                        String name = rs.getString("name");
                        dcbm.addElement(name);
                    }
                    cboxItemName.showPopup();
                    ps.close();
                    conn.close();
                                 System.out.println("String S = "+s);
                    System.out.println("String Buffer SB = "+sb.toString());
                    s="";

                } catch (Exception e) {
                    e.printStackTrace();}

           // }
            //count++;

        }
        //   }
}//GEN-LAST:event_cboxItemNameKeyPressed

    private void tabItemListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabItemListKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_tabItemListKeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_jPanel1KeyPressed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel)tabItemList.getModel();
        int numrows = model.getRowCount();
        for(int i = numrows - 1; i >=0; i--){
            model.removeRow(i);
        }

        /////////////////////////////////
        aqt= 1;
        tItems=0;
        tPrice=0;
        tq = 0;
        tp =0;
        td=0;
        name = null ;
        psale = 0;
        dsale = 0;

        ////////////////////////////
        txtDiscount.setText("");
        txtItemName.setText("");
        txtLoanTo.setText("");
        txtMBarcode.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtSalesTax.setText("");
        txtScannedBarcode.setText("");
        txtTotalCash.setText("");

        labAmountShow.setText("0.0");
        labBalanceShow.setText("0.0");
        labTotalCashShow.setText("0.0");
        labItemShow.setText("0");

        sb.delete(0, sb.length()+1);
 
        no = 1;

           labPrice.setForeground(new java.awt.Color(0, 0, 0));
           txtPrice.setForeground(new java.awt.Color(0, 0, 0));
           // TODO add your handling code here:
           labMItemName.setForeground(new java.awt.Color(0, 0, 0));
           txtItemName.setForeground(new java.awt.Color(0, 0, 0));

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnClearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnClearKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_btnClearKeyPressed

    private void cBoxPaymentTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cBoxPaymentTypeKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_cBoxPaymentTypeKeyPressed

    private void jLabel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel5KeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_jLabel5KeyPressed

    private void labLoanTOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labLoanTOKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_labLoanTOKeyPressed

    private void txtTotalCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalCashActionPerformed
        // TODO add your handling code here:
        btnTotalActionPerformed(evt);
}//GEN-LAST:event_txtTotalCashActionPerformed

    private void txtTotalCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCashKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtTotalCashKeyPressed

    private void jLabel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel7KeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_jLabel7KeyPressed

    private void txtLoanToKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoanToKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_txtLoanToKeyPressed

    private void btnPritnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPritnActionPerformed
        // TODO add your handling code here:
        System.out.println("Print ");

        //--- Create a new PrinterJob object
        PrinterJob printJob = PrinterJob.getPrinterJob();

        //--- Create a new book to add pages to
        Book book = new Book();

        //--- Add the document page using a landscape page format
        PageFormat documentPageFormat = new PageFormat();

        documentPageFormat.setPaper(new CustomPaper());

        populateList();

        pageValue = (int) toPrintList.size() / ONE_PAGE_ITEMS;
        pageValue++;
        if(pageValue>-1){
            for(int i = 0; i < pageValue; i++){
                book.append(new OurPrintReceipt(), documentPageFormat);
            }
        }
        //--- Tell the printJob to use the book as the pageable object
        printJob.setPageable(book);

        try {
            printJob.print();
        } catch (Exception PrintException) {
            PrintException.printStackTrace();
        }

    }//GEN-LAST:event_btnPritnActionPerformed
   private class CustomPaper extends Paper {

      public CustomPaper() {
      super();
      setSize(400.0, 700.0);
      setImageableArea(12.0, 12.0, 280.0, 390.0);
      }
}

  private class OurPrintReceipt implements Printable {

    public int print(Graphics g, PageFormat pageFormat, int page) {


      //--- Create the Graphics2D object
      Graphics2D g2d = (Graphics2D) g;

      //--- Translate the origin to 0,0 for the top left corner
      g2d.translate(pageFormat.getImageableX(), pageFormat
          .getImageableY());

      //--- Set the default drawing color to black
      g2d.setPaint(Color.black);
      Calendar calendar = Calendar.getInstance();
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      //--- Print the title
      String titleText = "Your Receipt -- " +
dateFormat.format(calendar.getTime() );
      Font titleFont = new Font("helvetica", Font.BOLD, 10);
      g2d.setFont(titleFont);

      if(page > 0 && pageValue !=page) {
          modifyList(page);
      }
      for(int j = 0, i = START_Y; j < toPrintList.size() && i <
(ONE_PAGE_ITEMS * SPACE_PER_ITEM + 1); j++, i = i + SPACE_PER_ITEM){
          g2d.drawString(toPrintList.get(j).toString() + page,
                          START_X, i);
      }
      //--- Compute the horizontal center of the page
      FontMetrics fontMetrics = g2d.getFontMetrics();
      double titleX = (pageFormat.getImageableWidth() / 2)
          - (fontMetrics.stringWidth(titleText) / 2);
      double titleY = 2 * START_X;
      g2d.drawString(titleText, (int) titleX, (int) titleY);

      pageValue = page;
      return (PAGE_EXISTS);
    }

    private void modifyList(int page) {
        int a = page * ONE_PAGE_ITEMS;
        for(int i = 0; i < ONE_PAGE_ITEMS && i < toPrintList.size(); i++){
            toPrintList.remove(i);
        }
    }
  }
  public void populateList(){
      toPrintList = new ArrayList<String>();
      for(int i=0; i < 63; i++){
          toPrintList.add("This the content page of page:");
      }
  }
    private void btnPritnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPritnKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_btnPritnKeyPressed

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        // TODO add your handling code here:
        System.out.println("Total Button hit & Total Amount is = "+txtTotalCash.getText());
        labTotalCashShow.setText(txtTotalCash.getText());
        int tc;
        String tcs =txtTotalCash.getText() ;

         System.out.println("Sizr of print Bill List = "+ printBill.size() );

          for(int a = 0 ; a < printBill.size() ; a++  ){

            System.out.println("List of Print Bill = " + printBill.get(a));
        }

        if(tcs.length()!=0 && Integer.parseInt(txtTotalCash.getText()) >= tp) {
           tc = Integer.parseInt(txtTotalCash.getText());
            labBalanceShow.setText(Float.toString((float)tc-tp));
            
             if(printBill == null)
            System.out.println("list size " );
             printBill.add("Total: " + tp);
            printBill.add("Cash Tendered: " + txtTotalCash.getText());
            printBill.add("Balance: " + (tc-tp));
            printBill.add("Number of Items: " + labItemShow.getText());

            for(int i = 0;i < printBill.size(); i++) {
                System.out.println(printBill.get(i));
            }




            txtTotalCash.setText("");
            txtScannedBarcode.requestFocus();
           /*
            /if(printBill == null)
            System.out.println("list size " );
             printBill.add("Total: " + tp);
            printBill.add("Cash Tendered: " + txtTotalCash.getText());
            printBill.add("Balance: " + (tc-tp));
            printBill.add("Number of Items: " + tItems);

            for(int i = 0;i < printBill.size(); i++) {
                System.out.println(printBill.get(i));
            }
*/

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Total Cash");
            labTotalCashShow.setText("0.0");
        }

       
}//GEN-LAST:event_btnTotalActionPerformed

    private void btnTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTotalKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }

}//GEN-LAST:event_btnTotalKeyPressed

    private void btnRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemActionPerformed
        // TODO add your handling code here:
        Object objQty;
        Object objPrice;
        Object objName;
        Object objNo;
        float qdb=0;
        if(tabItemList.getSelectedRow()!= -1){
            objNo   =  tabItemList.getModel().getValueAt( tabItemList.getSelectedRow() , 0 );
            objName =  tabItemList.getModel().getValueAt( tabItemList.getSelectedRow() , 1 );
            objQty  =  tabItemList.getModel().getValueAt( tabItemList.getSelectedRow() , 2 );
            objPrice = tabItemList.getModel().getValueAt( tabItemList.getSelectedRow() , 3 );

            System.out.println("objNo = "+objNo);
            System.out.println("objName = "+objName);
            String n =  (String) objName;

            System.out.println("objQty = "+objQty);
            String s =  (String) objQty;
            String q[]= s.split("\\=");
            float oq = Float.parseFloat(q[1]) ;
            labItemShow.setText(Float.toString(tq-oq));
            tq=tq-oq;

            System.out.println("objPrice = "+objPrice);
            String p =  (String) objPrice;
            String z[]= p.split("\\=");
            System.out.println("Z = "+Float.parseFloat(z[1]));
            float op = Float.parseFloat(z[1]) ;
            System.out.println("op = "+op);

            labAmountShow.setText(Float.toString(tp-op));
            tp=tp-op;
            //////////////////////////
            Connection conn = null;
            PreparedStatement psp = null;
            PreparedStatement removeSales = null;
            try {
                conn = getConnection();
                System.out.println("Conn to BD now get Price");
                psp = (PreparedStatement) conn.prepareStatement("SELECT stock_quantity FROM items WHERE name= '"+ n +"' ");
                Statement stm = (Statement) conn.createStatement();
                removeSales = (PreparedStatement) conn.prepareStatement("DELETE FROM sales WHERE number = '"+ objNo + "'AND bill_id = '"+getLastId()+"' ");
                removeSales.execute();

                ResultSet rsp ;
                rsp =psp.executeQuery();
                while ( rsp.next() ) {
                    qdb = rsp.getFloat("stock_quantity");
                }
                float a = qdb + oq;

                System.out.println("qdb = "+qdb);
                System.out.println("oq = "+oq);
                System.out.println("A = "+a);

                stm.executeUpdate("update items set stock_quantity  = " + a +" where items.name = '" + n + "'");
                psp.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();}
            //////////////////////////
            DefaultTableModel model = (DefaultTableModel)tabItemList.getModel();
            model.removeRow(tabItemList.getSelectedRow());
            txtScannedBarcode.requestFocus();

        }
    }//GEN-LAST:event_btnRemoveItemActionPerformed

    private void btnRemoveItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRemoveItemKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_btnRemoveItemKeyPressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        String ps = txtPrice.getText();
        String ns = txtItemName.getText();
        String qs = txtQuantity.getText();
        String ds = txtDiscount.getText();
        String ss = txtSalesTax.getText();
        float p = 100;
        float d = 0;
        float price = 0;
        float s = 0;

      /*  if(cBoxPaymentType.getSelectedIndex()==2){

            if(txtLoanTo.getText().length()== 0 ){
                JOptionPane.showMessageDialog(null,"Invalid Text" );
                labLoanTO.setForeground(new java.awt.Color(255, 0, 0));
                txtLoanTo.setForeground(new java.awt.Color(255, 0, 0));
            } else {
                labLoanTO.setForeground(new java.awt.Color(0, 0, 0));
                txtLoanTo.setForeground(new java.awt.Color(0, 0, 0));
            }
        }
*/
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date da = new Date();

        if(qs.length()> 0 && Float.parseFloat(txtQuantity.getText()) > 0  )
            aqt = Float.parseFloat(txtQuantity.getText());

        if(ds.length() > 0 && Float.parseFloat(txtDiscount.getText()) > 0)
            d = Float.parseFloat(txtDiscount.getText());
        if(ss.length()> 0 && Float.parseFloat(txtSalesTax.getText()) > 0 ) {
            s = Float.parseFloat(txtSalesTax.getText());
            s = (s/100)*p;
        }
        if(ps.length()!=0 && NumberUtils.isNumber(ps)==true && ns.length()!=0  ) {
            DefaultTableModel m = (DefaultTableModel)tabItemList.getModel();
            p = Float.parseFloat(ps);

            price = (float) (d/100)*p;
            price =  (float)p - price ;
            price =  (float)s+price;
            price = (float)  aqt*price;

            labAmountShow.setText(Float.toString(tp+price));
            tp =tp+price;
            labItemShow.setText(Float.toString(tq+aqt));
            tq=tq+aqt;

            m.insertRow(tabItemList.getRowCount() , new Object[] {tabItemList.getRowCount()+1 ,txtItemName.getText(),"1 X "+aqt + "="+aqt,(p+.01)+" X "+aqt+"=  "+ price} );
            System.out.println("Row Count ="+tabItemList.getRowCount());

            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = getConnection();    System.out.println("Conn to BD now ");
                price = (float) (d/100)*p;
                price = (float)p - price ;
                price = (float)s + price ;
                price = (float)  aqt*price;


                /// update  sales table
                psale = price;
                dsale = d;
                d_db= dateFormat.format(da);

                System.out.println("Time Date ="+d_db);  System.out.println("ADD buttoon............");
                //no = tabItemList.getRowCount();
                String insert_r ;
                String barcode = txtMBarcode.getText();

                if(barcode.length()>0){
                    insert_r ="INSERT INTO sales(item_name,price,discount,quantity,date_time,number,barcode,payment_type,item_in_db)  VALUES ('" + txtItemName.getText()+ "','" +psale+ "','" + dsale+ "','" + aqt+ "','"+ d_db+"', '"+no +"','"+txtMBarcode.getText()+"' ,'" + cBoxPaymentType.getSelectedItem()+ "','"+0+"')";

                    
                    pstmt = (PreparedStatement) conn.prepareStatement(insert_r);
                    pstmt.executeUpdate();
                    /////
                    if(tabItemList.getRowCount()==1){
                        try {
                            pstmt  = (PreparedStatement) conn.prepareStatement("SELECT last_insert_id() ");
                            bill_ID = pstmt.executeQuery();
                            while (bill_ID.next() ){
                                setLastId(bill_ID.getInt("last_insert_id()"));
                            }
                            test = getLastId();
                            pstmt .execute("update sales set bill_id  = " +getLastId() +" where sales.id = '"+ test+"'");
                        } catch (Exception e) {
                            e.printStackTrace(); }
                    } else{
                        test= test+1;
                        pstmt.execute("update sales set bill_id  = " +getLastId() +" where sales.id = '" +test+"'");
                    }
                    ////
                    
                    pstmt.close();
                    conn.close();
                } else  {      System.out.println("TEST ");

                insert_r ="INSERT INTO sales(item_name,price,discount,quantity,date_time,number,payment_type,item_in_db,loan_to)  VALUES ('" + txtItemName.getText()+ "','" +psale+ "','" + dsale+ "','" + aqt+ "','"+ d_db+"', '"+no +"','" + cBoxPaymentType.getSelectedItem()+ "','"+0+"','"+txtLoanTo.getText()+"')";
                pstmt = (PreparedStatement) conn.prepareStatement(insert_r);
                pstmt.executeUpdate();
                ////
                if(tabItemList.getRowCount()==1){
                    try {
                        pstmt  = (PreparedStatement) conn.prepareStatement("SELECT last_insert_id() ");
                        bill_ID = pstmt.executeQuery();
                        while (bill_ID.next() )	{
                            setLastId(bill_ID.getInt("last_insert_id()"));
                        }
                        test = getLastId();
                        pstmt .execute("update sales set bill_id  = " +getLastId() +" where sales.id = '"+ test+"'");

                    } catch (Exception e) {
                        e.printStackTrace(); }
                } else {
                    test= test+1;
                    pstmt.execute("update sales set bill_id  = " +getLastId() +" where sales.id = '" +test+"'");
                }
                //////
                pstmt.close();
                conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
             System.out.print("calling method");
             
                    billPrint((String)txtItemName.getText(),(double)psale,(double)aqt,(int)tabItemList.getRowCount());
                   
            ///////////////////////////////////////////////////
            txtQuantity.setText("");
            txtDiscount.setText("");
            txtSalesTax.setText("");
            aqt=1;
        } else{
            JOptionPane.showMessageDialog(null,"Please Enter the Item Name & Price ");

            txtPrice.setToolTipText("You must enter Item Name & Price ");
            txtItemName.setToolTipText("You must enter Item Name & Price ");

            labPrice.setForeground(new java.awt.Color(255, 0, 0));
            txtPrice.setForeground(new java.awt.Color(255, 0, 0));

            labMItemName.setForeground(new java.awt.Color(255, 0, 0));
            txtItemName.setForeground(new java.awt.Color(255, 0, 0));

            txtDiscount.setText("");
            txtQuantity.setText("");
            txtSalesTax.setText("");
            aqt=1;

        }
        cBoxPaymentType.setSelectedIndex(0);
        btnEraseActionPerformed(evt);
        no++;

      
}//GEN-LAST:event_btnAddActionPerformed

    private void btnAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_btnAddKeyPressed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        // TODO add your handling code here:
        txtMBarcode.setText("");
        txtPrice.setText("");
        txtItemName.setText("");
        txtQuantity.setText("");
        txtDiscount.setText("");
        txtSalesTax.setText("");
        txtLoanTo.setText("");
        txtScannedBarcode.requestFocus();
         
}//GEN-LAST:event_btnEraseActionPerformed

    private void btnEraseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEraseKeyPressed
        // TODO add your handling code here:
        if(getkeys(evt)==1){
        }
}//GEN-LAST:event_btnEraseKeyPressed

    private void txtPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPriceMouseClicked
        // TODO add your handling code here:
                labPrice.setForeground(new java.awt.Color(0, 0, 0));
                txtPrice.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_txtPriceMouseClicked

    private void txtItemNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtItemNameMouseClicked
        // TODO add your handling code here:
           labMItemName.setForeground(new java.awt.Color(0, 0, 0));
           txtItemName.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_txtItemNameMouseClicked

    private void txtItemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusGained
        // TODO add your handling code here:
        // labMItemName.setForeground(new java.awt.Color(0, 0, 0));
         //  txtItemName.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_txtItemNameFocusGained

    /*private void billPrint(String name , double price, int quantity , int number ){

        System.out.println("Print the Bill");

    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnPritn;
    private javax.swing.JButton btnRemoveItem;
    private javax.swing.JButton btnTotal;
    private javax.swing.JComboBox cBoxPaymentType;
    private javax.swing.JComboBox cboxItemName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labAmount;
    private javax.swing.JLabel labAmountShow;
    private javax.swing.JLabel labBalanceShow;
    private javax.swing.JLabel labBarcode;
    private javax.swing.JLabel labDiscount;
    private javax.swing.JLabel labItem;
    private javax.swing.JLabel labItemName;
    private javax.swing.JLabel labItemShow;
    private javax.swing.JLabel labLoanTO;
    private javax.swing.JLabel labMItemName;
    private javax.swing.JLabel labPrice;
    private javax.swing.JLabel labQuantity;
    private javax.swing.JLabel labSalesTax;
    private javax.swing.JLabel labScannedBarcode;
    private javax.swing.JLabel labTotalCash;
    private javax.swing.JLabel labTotalCashShow;
    private javax.swing.JLabel labbalance;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panal2;
    private javax.swing.JTable tabItemList;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtLoanTo;
    private javax.swing.JTextField txtMBarcode;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSalesTax;
    private javax.swing.JTextField txtScannedBarcode;
    private javax.swing.JTextField txtTotalCash;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private StringBuffer sb = new StringBuffer("");
    private boolean emptySB = false;
    private JDialog aboutBox;
}
