/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf.unibz.it.obda.gui.swing.datasource.panels;

import inf.unibz.it.obda.api.controller.APIController;
import inf.unibz.it.obda.api.controller.DatasourcesController;
import inf.unibz.it.obda.domain.DataSource;
import inf.unibz.it.obda.gui.IconLoader;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 *
 * @author obda
 */
public class DataSourceSelectionPanel extends javax.swing.JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8124338850871507250L;
	private DatasourcesController dscontroller = null;
	private DatasourceSelector selector = null;
	
    /** Creates new form DataSourceSelectionPanel */
    public DataSourceSelectionPanel(APIController apiController) {
		
		this.dscontroller = apiController.getDatasourcesController();
        initComponents();
        init();
    }
    
    public DatasourceSelector getDataSourceSelector(){
    	return selector;
    }
    
    private void init(){
    	
    	Vector<DataSource> sources = new Vector<DataSource>();
    	sources.addAll(dscontroller.getAllSources().values());
    	selector = new DatasourceSelector(sources);
    	
    	GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(selector, gridBagConstraints);
        dscontroller.addDatasourceControllerListener(selector);
    	
    	jButtonAdd.setIcon(IconLoader.getImageIcon("images/plus.png"));
    	jButtonAdd.setToolTipText("Add a new datasource");
        jButtonAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAdd.setContentAreaFilled(false);
        jButtonAdd.setIconTextGap(0);
        jButtonAdd.setMaximumSize(new java.awt.Dimension(25, 25));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(25, 25));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(25, 25));
    	jButtonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = (String) JOptionPane.showInputDialog("Indicate the ID of the new data source", null);
				if ((name != null) && (!name.trim().equals(""))) {
					dscontroller.addDataSource(name.trim());
				}
				
			}
		});
    	
    	jButtonRemove.setIcon(IconLoader.getImageIcon("images/minus.png"));
    	jButtonRemove.setToolTipText("Remove the selected datasource");
        jButtonRemove.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRemove.setContentAreaFilled(false);
        jButtonRemove.setIconTextGap(0);
        jButtonRemove.setMaximumSize(new java.awt.Dimension(25, 25));
        jButtonRemove.setMinimumSize(new java.awt.Dimension(25, 25));
        jButtonRemove.setPreferredSize(new java.awt.Dimension(25, 25));
    	jButtonRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataSource ds = selector.getSelectedDataSource();
				if (ds != null) {
					dscontroller.removeDataSource(ds.getSourceID());
				}
			}
		});
    	
    	jLabeltitle.setBackground(new java.awt.Color(153, 153, 153));
    	jLabeltitle.setFont(new java.awt.Font("Arial", 1, 11));
    	jLabeltitle.setForeground(new java.awt.Color(153, 153, 153));
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

        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jLabeltitle = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Datasource Selection"));
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jButtonAdd, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jButtonRemove, gridBagConstraints);

        jLabeltitle.setText("Select Datasource:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        add(jLabeltitle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JLabel jLabeltitle;
//    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}