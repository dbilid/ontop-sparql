/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf.unibz.it.obda.gui.swing.dataquery.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URI;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import org.obda.query.domain.CQIE;


/*
 * SelectPrefixDialog.java
 *
 * Created on 22-mar-2011, 10.54.46
 */

/**
 *
 * @author Manfred Gerstgrasser
 */
public class SelectPrefixDialog extends javax.swing.JPanel{

	private Map<String, URI> prefixMap = null;
	private JDialog parent = null;
	private JTextPane querypane = null;
	private Vector<JCheckBox> checkboxes = null;
	private String base = null;
    /**
	 * 
	 */
	private static final long serialVersionUID = -8277829841902027620L;
	/** Creates new form SelectPrefixDialog */
    public SelectPrefixDialog(Map<String, URI> prefixes, JTextPane pane, String base) {
        super();
        this.prefixMap = prefixes;
        this.querypane = pane;
        this.base = base;
        initComponents();
        drawCheckBoxes();
    }
    
    public void show(){
    	parent = new JDialog();
    	parent.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	parent.setContentPane(this);
    	parent.setSize(350, 580);
    	parent.setLocationRelativeTo(querypane);   	
    	parent.setVisible(true);
    }
    
    private void drawCheckBoxes(){
    
    	checkboxes = new Vector<JCheckBox>();
    	int i = 0;
    	Iterator<String> it =  prefixMap.keySet().iterator();
    	while(it.hasNext()){
    		String key = it.next();
    		if(!key.equals("version")){
	    		 jCheckBox1 = new JCheckBox();
		    	 jCheckBox1.setText(key);
		         java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		         gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		         gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		         gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
		         gridBagConstraints.gridy = i;
		         jPanel2.add(jCheckBox1, gridBagConstraints);
		         checkboxes.add(jCheckBox1);
		         i++;
		         if(key.equals("owl") || key.equals("rdf") || key.equals("rdfs")){
		        	 jCheckBox1.setSelected(true);
		         }
    		}
    	}
    	
    	java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = i+1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);
        
        jButtonCancel.setToolTipText("Cancel the attachment of prefixes. (ESCAPE)");
        jButtonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
        jButtonSelectAll.setToolTipText("Select all shown prefixes. (CTRL+A)");
        jButtonSelectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectAll();
			}
		});
        jButtonSelectAll.setToolTipText("Unselect all shown prefixes. (CTRL+N)");
        jButtonSelectNone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectNone();
			}
		});
        jButtonAccept.setToolTipText("Add selected prefixes to query. (ENTER)");
        jButtonAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
        
        ActionListener actionListenerCancel = new ActionListener() {
        	  public void actionPerformed(ActionEvent actionEvent) {
        	     cancel();
        	  }
        	};
        KeyStroke ks_ecape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
        this.registerKeyboardAction(actionListenerCancel, ks_ecape, JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        ActionListener actionListenerAccept = new ActionListener() {
      	  public void actionPerformed(ActionEvent actionEvent) {
      	     accept();
      	  }
      	};
      KeyStroke ks_enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
      this.registerKeyboardAction(actionListenerAccept, ks_enter, JComponent.WHEN_IN_FOCUSED_WINDOW);
      
      ActionListener actionListenerSelectAll = new ActionListener() {
      	  public void actionPerformed(ActionEvent actionEvent) {
      	     selectAll();
      	  }
      	};
      KeyStroke ks_ctrl_A = KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_DOWN_MASK );
      this.registerKeyboardAction(actionListenerSelectAll, ks_ctrl_A, JComponent.WHEN_IN_FOCUSED_WINDOW);
      
      ActionListener actionListenerSelectNone = new ActionListener() {
      	  public void actionPerformed(ActionEvent actionEvent) {
      	     selectNone();
      	  }
      	};
      KeyStroke ks_ctrl_N = KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK);
      this.registerKeyboardAction(actionListenerSelectNone, ks_ctrl_N, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void selectAll(){
    	for (JCheckBox box : checkboxes) {
			box.setSelected(true);
		}
    }
    
    private void selectNone(){
    	for (JCheckBox box : checkboxes) {
			box.setSelected(false);
		}
    }
    
    private void accept(){
    	StringBuffer prefix = new StringBuffer();
//    	prefix.append("BASE ");
//    	prefix.append(": <");
//		prefix.append(base);
//		prefix.append(">\n");
    	prefix.append("PREFIX ");
    	prefix.append(": <");
		prefix.append(base);
		prefix.append("#");
		prefix.append(">\n");
		for (JCheckBox box : checkboxes) {
			if(box.isSelected()){
				prefix.append("PREFIX ");
				prefix.append(box.getText());
				prefix.append(": <");
				prefix.append(prefixMap.get(box.getText()));
				prefix.append(">\n");
			}
		}
		String currentcontent = querypane.getText();
		String newContent = prefix+currentcontent;
		querypane.setText(newContent);
		parent.setVisible(false);
		parent.dispose();
    }
    
    private void cancel(){
    	parent.setVisible(false);
		parent.dispose();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabelHeader = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAccept = new javax.swing.JButton();
        jButtonSelectAll = new javax.swing.JButton();
        jButtonSelectNone = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(100, 100));
        setLayout(new java.awt.GridBagLayout());

        jLabelHeader.setText("Select the relevant prefixes for your query:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabelHeader, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAccept.setText("Accept");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jButtonAccept, gridBagConstraints);

        jButtonSelectAll.setText("Select All");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jButtonSelectAll, gridBagConstraints);

        jButtonSelectNone.setText("Select None");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jButtonSelectNone, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel2, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccept;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSelectAll;
    private javax.swing.JButton jButtonSelectNone;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabelHeader;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}