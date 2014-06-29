/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productupdater;

/**
 *
 * @author Amy
 */
public class UpdaterGUI extends javax.swing.JFrame {

	/**
	 * Creates new form UpdaterGUI
	 */
	public UpdaterGUI() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      openFileButton = new javax.swing.JButton();
      fileTextField = new javax.swing.JTextField();
      fileLabel = new javax.swing.JLabel();
      quitButton = new javax.swing.JButton();
      confirmButton = new javax.swing.JButton();
      jLabel1 = new javax.swing.JLabel();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      openFileButton.setText("Open File");
      openFileButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            openFileButtonActionPerformed(evt);
         }
      });

      fileTextField.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            fileTextFieldActionPerformed(evt);
         }
      });

      fileLabel.setText("File contents:");

      quitButton.setText("Quit");
      quitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            quitButtonActionPerformed(evt);
         }
      });

      confirmButton.setText("Confirm Update");
      confirmButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            confirmButtonActionPerformed(evt);
         }
      });

      jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
      jLabel1.setText("Olympic Pride Products Updater");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addGroup(layout.createSequentialGroup()
                     .addComponent(fileLabel)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(openFileButton))
                  .addGroup(layout.createSequentialGroup()
                     .addComponent(quitButton)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(confirmButton))
                  .addComponent(fileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(jLabel1))
            .addContainerGap(19, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jLabel1)
            .addGap(22, 22, 22)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(fileLabel)
               .addComponent(openFileButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(fileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(quitButton)
               .addComponent(confirmButton))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_openFileButtonActionPerformed

   private void fileTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileTextFieldActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_fileTextFieldActionPerformed

   private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
      System.exit(0);
   }//GEN-LAST:event_quitButtonActionPerformed

   private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
      String input = "ID,category,/nblah,/n";
		
		int startpoint = 0;
		int currentposition = 0;
		String item;
		
		//outermost is "until end of file" - SQL writes here
		//next is "until end of line" - save substrings to variable/array here
		//then "until comma found" - this generates the pointer positions to substring
		while(currentposition < input.length())
		{
			while(currentposition < (input.length() - 1) && input.charAt(currentposition) != '/')
			{
				while (currentposition < input.length() && input.charAt(currentposition) != ',')
				{
					currentposition++;
				} //end comma-finding loop
				
				//save substrings to variables/array/something
				//saving to single variable and printing for debug now
				item = input.substring(startpoint, currentposition);
				currentposition++;
				startpoint = currentposition;
				System.out.println(item);
			} //end newline-finding loop
			
			if (currentposition < (input.length()))
			{
				currentposition = currentposition + 2;
				startpoint = currentposition;			
			} //end if which handles skipping newlines
			
			//write SQL insert here
			
		} //end file-end finding loop
	
   }//GEN-LAST:event_confirmButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UpdaterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UpdaterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UpdaterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UpdaterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UpdaterGUI().setVisible(true);
			}
		});
	}

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton confirmButton;
   private javax.swing.JLabel fileLabel;
   private javax.swing.JTextField fileTextField;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JButton openFileButton;
   private javax.swing.JButton quitButton;
   // End of variables declaration//GEN-END:variables
}
