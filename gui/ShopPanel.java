package gui;

import shop.prodacts.Bird;
import shop.Shop;
import shop.stock.StockShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;


/**
 * Created by panasyuk on 01.11.2015.
 */
public class ShopPanel {
    private Shop shop;
    private JFrame frame;
    private JPanel panel;
    private TableMaker currentTable;
    private int pi = 0;

    public ShopPanel() {
        currentTable = new TableMaker();
        makeStartPanel();

    }


    public void makeStartPanel() {
        frame = new JFrame("Birds shop");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLocation(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = panelChoiceConnecting();
        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public JPanel panelChoiceConnecting() {
        JPanel startPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        Button button1 = new Button("Connect to DB (MySQL)");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop = new Shop();
                currentTable = new TableMaker(shop);
                frame.setContentPane(makeWorkPanel());

            }
        });
        Button button2 = new Button("Connect to virtual DB");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop = new Shop("i");
                currentTable = new TableMaker(shop);
                clearFrame(makeWorkPanel());
            }
        });
        Button button3 = new Button("Work with Collections");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop = new Shop(1);
                currentTable = new TableMaker(shop);
                clearFrame(makeWorkPanel());
            }
        });
        startPanel.add(button1);
        startPanel.add(button2);
        startPanel.add(button3);
        return startPanel;
    }


    public JPanel makeWorkPanel() {
        JPanel workPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuTransaction = new JMenu("Transaction");
        JMenuItem newTransaction = new JMenuItem("Create Transaction");
        menuTransaction.add(newTransaction);
        newTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(salesPanel());

            }
        });
        JMenuItem allTransaction = new JMenuItem("List transactions");
        menuTransaction.add(allTransaction);
        allTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(makeListTransactionsPanel());
            }
        });

        menuBar.add(menuTransaction);


        JMenu menuStock = new JMenu("Stock");
        JMenuItem currentStock = new JMenuItem("Current stock");
        menuStock.add(currentStock);
        currentStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.getStock().takeCurrentStock();
                clearFrame(makeStockPanel());
            }
        });


        JMenuItem addNewPosition = new JMenuItem("Add NEW type");
        menuStock.add(addNewPosition);
        addNewPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(createNewProdactPanel());
            }
        });


        JMenuItem updatePositionOnStock_Qty = new JMenuItem("Add birds on STOCK");
        menuStock.add(updatePositionOnStock_Qty);
        updatePositionOnStock_Qty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(updateInformationPanel("qnt."));
            }
        });
        JMenuItem updatePositionOnStock_Price = new JMenuItem("Setup NEW price");
        menuStock.add(updatePositionOnStock_Price);
        updatePositionOnStock_Price.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(updateInformationPanel("price_product"));
            }
        });
        menuBar.add(menuStock);

        JMenu menuReport = new JMenu("Reports");
        JMenuItem select = new JMenuItem("All reports");
        menuReport.add(select);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(reportStartPanel());
            }
        });
        menuBar.add(menuReport);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.pack();
        return workPanel;
    }

    public JPanel makeStockPanel() {
        JPanel stockPanel = new JPanel();
        JTable tabl = currentTable.stockTable();
        tabl.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabl);

        stockPanel.add(scrollPane);
        Button ok = new Button("Exit");
        stockPanel.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(makeWorkPanel());
            }
        });
        return stockPanel;

    }

    public JPanel makeListTransactionsPanel() {
        JPanel listTransaction = new JPanel();
        JTable tabl = currentTable.listTransaction();
        tabl.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabl);

        listTransaction.add(scrollPane);
        frame.add(listTransaction);
        Button ok = new Button("Exit");
        listTransaction.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(makeWorkPanel());
            }
        });

        return listTransaction;

    }

    public JPanel createNewProdactPanel() {
        JPanel newProdact = new JPanel();

        newProdact.setLayout(new GridBagLayout());

        JLabel nameProdact = new JLabel("Name NEW Product: ");
        JComboBox selectNewProdact = new JComboBox();
        selectNewProdact.setEditable(false);
        selectNewProdact.addItem("-- SELECT: --");
        List<Bird> l = shop.getStock().compare(shop.getStock().getStockList());
        for (int i = 0; i < l.size(); i++) {
            selectNewProdact.addItem(l.get(i).getNameBird());
        }


        newProdact.add(nameProdact, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        newProdact.add(selectNewProdact, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));

        JLabel qnt = new JLabel("Qnt. birds on stock: ");
        JTextField inputQnt = new JTextField("");
        newProdact.add(qnt, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        newProdact.add(inputQnt, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));

        JLabel price = new JLabel("Price bird: ");
        JTextField inputPrice = new JTextField("");
        newProdact.add(price, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        newProdact.add(inputPrice, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//
        JButton add = new JButton("Add");
        newProdact.add(add, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bird bird = null;
                for (int i = 0; i < l.size(); i++) {
                    if (l.get(i).getNameBird().equals(selectNewProdact.getSelectedItem())) {
                        bird = l.get(i);
                    }
                }
                int qntOnStock = Integer.valueOf((String) inputQnt.getText());
                double priceBird = Double.valueOf((String) inputPrice.getText());
                shop.getStock().addNewTypeProducts(bird.getId_bird(), bird.getNameBird(), qntOnStock, priceBird);

                clearFrame(makeStockPanel());
            }
        });
        JButton exit = new JButton("Exit");
        newProdact.add(exit, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(makeWorkPanel());
            }
        });
        return newProdact;

    }

    public JPanel reportStartPanel() {
        JPanel reports = new JPanel();
        reports.setLayout(new GridLayout(4, 1));
        JButton report1 = new JButton("Select customer's buy");
        report1.setPreferredSize(new Dimension(300, 25));
        reports.add(report1);
        report1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(report_select_on_customer());
            }
        });

        JButton report2 = new JButton("Who did buy '______' product?");
        report2.setPreferredSize(new Dimension(300, 25));
        reports.add(report2);
        report2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(report_select_on_prod());
            }
        });


        JButton report3 = new JButton("Total cash");
        report3.setPreferredSize(new Dimension(300, 25));
        reports.add(report3);
        report3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.getReportsShop().totalCash();
                clearFrame(reportCustomer());
            }
        });

        JButton report4 = new JButton("..........");
        report4.setPreferredSize(new Dimension(300, 25));
        reports.add(report4);
        reports.setVisible(true);

        return reports;
    }

    public JPanel report_select_on_customer() {
        JPanel report1 = new JPanel();
        JLabel head = new JLabel("Pleas, select customer:");
        JComboBox selectCustomer = new JComboBox();
        selectCustomer.setEditable(false);
        selectCustomer.addItem("-- CUSTOMER: --");
        for (int i = 0; i < shop.getCustomers().getCustomerShopList().size(); i++) {
            selectCustomer.addItem(shop.getCustomers().getCustomerShopList().get(i).getName_customer());
        }
        selectCustomer.addItem("-- ALL CUSTOMERS: --");
        report1.add(head);
        report1.add(selectCustomer);

        JButton confirm = new JButton("Confirm");
        report1.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.getReportsShop().selectProductsAccordingCustomer((String) selectCustomer.getSelectedItem());
                clearFrame(reportCustomer());
            }
        });
        return report1;
    }

    public JPanel report_select_on_prod() {
        JPanel report2 = new JPanel();
        JLabel head = new JLabel("Pleas, select product:");
        JComboBox selectBird = new JComboBox();
        selectBird.setEditable(false);
        selectBird.addItem("-- BIRDS: --");
        shop.getStock().takeCurrentStock();
        for (int i = 0; i < shop.getStock().getStockList().size(); i++) {
            currentTable.stockTable();
            selectBird.addItem(shop.getStock().getStockList().get(i).getBird());
            System.out.println(shop.getStock().getStockList().size());
        }
        selectBird.addItem("-- ALL BIRDS: --");
        report2.add(head);
        report2.add(selectBird);

        JButton confirm = new JButton("Confirm");
        report2.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.getReportsShop().selectCustomerAccordingProduct((String) selectBird.getSelectedItem());
                clearFrame(reportCustomer());

            }
        });
        return report2;
    }

    public JPanel updateInformationPanel(String wot) {
        JPanel updateProdact = new JPanel();

        updateProdact.setLayout(new GridBagLayout());

        JComboBox selectBird = new JComboBox();
        selectBird.setEditable(false);
        JLabel select = new JLabel("Select bird: ");
        updateProdact.add(select);
        updateProdact.add(selectBird);
        selectBird.addItem("            ");
        for (int i = 0; i < shop.getStock().getStockList().size(); i++) {
            selectBird.addItem(shop.getStock().getStockList().get(i).getBird());
        }
        JLabel newPrice = new JLabel("  Setup new " + wot + " : ");
        JTextField inputPrice = new JTextField("");
        inputPrice.setColumns(5);

        updateProdact.add(newPrice);
        updateProdact.add(inputPrice);

        JButton update = new JButton("Update");
        updateProdact.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select = (String) selectBird.getSelectedItem();

                shop.getStock().updatePosition(wot, select, (String) inputPrice.getText());
                shop.getStock().takeCurrentStock();
                clearFrame(makeStockPanel());
            }
        });

        return updateProdact;
    }

    public JPanel salesPanel() {
        JPanel salesForma = new JPanel();
        salesForma.setLayout(new GridBagLayout());

        JLabel nameCustumer = new JLabel("Name customer: ");
        JComboBox selectCustomer = new JComboBox();
        selectCustomer.setEditable(false);
        selectCustomer.addItem("-- CUSTOMER: --");
        JTextField inputName = new JTextField("");
        inputName.setColumns(25);

        for (int i = 0; i < shop.getCustomers().getCustomerShopList().size(); i++) {
            selectCustomer.addItem(shop.getCustomers().getCustomerShopList().get(i).getName_customer());
        }


        salesForma.add(nameCustumer, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        salesForma.add(selectCustomer, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));


        JLabel berds = new JLabel("Birds: ");
        shop.getStock().takeCurrentStock();
        List<StockShop> lp = shop.getStock().getStockList();
        ButtonGroup bg = new ButtonGroup();


        JPanel prod = new JPanel();
        ActionListener rbl = new RBListener();
        prod.setLayout(new GridLayout(Math.max(lp.size(),1), 0));
        prod.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JRadioButton rb;
        for (int i = 0; i < lp.size(); i++) {
            StockShop p = lp.get(i);

            rb = new JRadioButton(p.getBird() + ", Price: " + p.getPrice() + "$, In Stock: " + p.getOnStock() + " pcs.");
            rb.setActionCommand(String.valueOf(i));
            rb.addActionListener(rbl);
            if (i == 0) {
                rb.setSelected(false);
            }
            bg.add(rb);
            prod.add(rb);
        }

        salesForma.add(berds, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 0, new Insets(0, 0, 5, 0), 0, 0));
        salesForma.add(prod, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));


        JLabel number = new JLabel("Count: ");
        salesForma.add(number, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        NumberFormat nf = NumberFormat.getNumberInstance();
        JFormattedTextField fildNumber = new JFormattedTextField(nf);
        fildNumber.setColumns(2);
        fildNumber.setValue(0);
        salesForma.add(fildNumber, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));

        Button bt = new Button("Buy!");
        bt.setLocation(100, 100);
        salesForma.add(bt, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customer = (String) selectCustomer.getSelectedItem();
                if (customer == "-- CUSTOMER: --") {

                    errorPanel("Select name customer's!!!");
                }
                int b = Integer.parseInt(fildNumber.getText());
                if (b < 1) {
                    errorPanel("Enter a quantity birds!");
                }
                String name_birds = lp.get(pi).getBird();

                int rezult = shop.getTransactionShop().createTransaction(customer, name_birds, b);
                if (rezult == 0) {
                    errorPanel("This quantity birds is absent!");
                } else {
                    clearFrame(makeListTransactionsPanel());
                }


            }
        });
        JLabel newCustomer = new JLabel("The customer is absent? ... ");
        JButton addNewCustomer = new JButton("New Customer");
        salesForma.add(newCustomer, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        salesForma.add(addNewCustomer, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        addNewCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(creatNewCustomer());
            }
        });
        return salesForma;
    }

    public JPanel creatNewCustomer() {

        JPanel creatNewCustomer = new JPanel();
        creatNewCustomer.setLayout(new GridBagLayout());

        JLabel nameCustumer = new JLabel("Name NEW Customer: ");
        JTextField inputName = new JTextField("");
        inputName.setColumns(25);

        creatNewCustomer.add(nameCustumer, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 5, 0), 0, 0));
        creatNewCustomer.add(inputName, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));

        JButton add = new JButton("Add customer");
        creatNewCustomer.add(add, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shop.getCustomers().addNewCustomer((String) inputName.getText());
                clearFrame(salesPanel());
            }
        });


        return creatNewCustomer;


    }

    public JPanel reportCustomer() {

        JPanel reportCustomer = new JPanel();
        JTable tabl = currentTable.reportTable();
        tabl.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabl);
        reportCustomer.add(scrollPane);
        frame.add(reportCustomer);

        Button ok = new Button("Exit");
        reportCustomer.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame(reportStartPanel());
            }
        });


        return reportCustomer;
    }

    public void clearFrame(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(makeWorkPanel());
        frame.repaint();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }


    private class RBListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pi = Integer.parseInt(e.getActionCommand());
        }
    }

    public void errorPanel(String message) {
        JFrame error = new JFrame("ERROR");


        error.setMinimumSize(new Dimension(250, 150));
        error.setLocation(500, 250);
        JPanel mes = new JPanel();

        mes.setLayout(new GridBagLayout());
        JLabel txt = new JLabel(message);
        JButton ok = new JButton("Ok");
        mes.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.setVisible(false);

            }
        });
        mes.add(txt, new GridBagConstraints(0, 0, 0, 1, 1, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 5, 0), 0, 0));
        mes.add(ok, new GridBagConstraints(0, 1, 1, 0, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 5, 0), 0, 0));
        error.setContentPane(mes);


        error.setVisible(true);
        error.pack();


    }
}
