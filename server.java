/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;


/**
 *
 * @author KOTRESHA
 */
public class server extends javax.swing.JFrame {
    ServerSocket ss;
    HashMap clientColl=new HashMap();
    /**
     * Creates new form server
     */
    public server() {
        try{
        initComponents();
                //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ss=new ServerSocket(2089);
        this.jLabel2.setText("server started");
        new clientaccept().start();
        }
        catch(Exception e){e.printStackTrace();}
    }
class clientaccept extends Thread{
    public void run(){
        while(true){
            try{
                Socket s=ss.accept();
                String i=new DataInputStream(s.getInputStream()).readUTF();
                if(clientColl.containsKey(i)){
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                    dout.writeUTF("you are already registered...!");
                }else{
                    clientColl.put(i,s);
                    msgBox.append(i+" joined!\n");
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                    dout.writeUTF("");
                    new msgread(s,i).start();
                    new prepareclientlist().start();
                }
            }catch(Exception e){}
        }
    }
}
class msgread extends Thread{
    Socket s;
    String ID;
    private msgread(Socket s,String ID)
    {this.s=s;
    this.ID=ID;
    }
    public void run(){
        while(!clientColl.isEmpty()){
            try{
                String i=new DataInputStream(s.getInputStream()).readUTF();
                if(i.equals("mkoihgteazdcvgyhujb096765542AXTY")){
                clientColl.remove(ID);
                msgBox.append(ID+": removed!\n");
                new prepareclientlist().start();
                Set k=clientColl.keySet();
                Iterator itr=k.iterator();
                while(itr.hasNext()){
                    String key=(String)itr.next();
                if(!key.equalsIgnoreCase(ID)){
                    try{
                        new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF(ID);
                            
                        }catch(Exception e){
                        clientColl.remove(key);
                        msgBox.append(key+":removed!");
                        new prepareclientlist().start();
                        }
                    }
                }
                }
                    else if(i.contains("$4344554@@@@@67667@@")){
                        i=i.substring(20);
                        StringTokenizer st=new StringTokenizer(i,":");
                        String id=st.nextToken();
                        i=st.nextToken();
                        try{new DataOutputStream(((Socket)clientColl.get(id)).getOutputStream()).writeUTF("< "+ID+" to "+id+" >"+i);
                            
                        }catch(Exception e){
                            clientColl.remove(id);
                            msgBox.append(id+" removed!");
                            new prepareclientlist().start();
                        }
                    }
                    else
                    {
                        Set k=clientColl.keySet();
                        Iterator itr;
                        itr = k.iterator();
                        while(itr.hasNext()){
                            String key=(String)itr.next();
                            if(!key.equalsIgnoreCase(ID)){
                                try{
                                    new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF("< "+ID+" to all >"+i);
 
                                }catch(Exception e){
                                    clientColl.remove(key);
                                    msgBox.append(key+": removed!");
                                    new prepareclientlist().start();
                                }
                            }
                        }
                        
                    }
                
            }catch(Exception e){}
        }
    }
    
}
class prepareclientlist extends Thread{
    public void run(){
        try{
            String ids="";
            Set k=clientColl.keySet();
            Iterator itr=k.iterator();
            while(itr.hasNext()){
                String key=(String)itr.next();
                ids+=key+",";
            }
            if(ids.length()!=0)
                ids=ids.substring(0,ids.length()-1);
            
            itr=k.iterator();
            while(itr.hasNext())
            {
                String key=(String)itr.next();
                try{
                    new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF(";:.,/="+ids);
                    }catch(Exception e){
                    clientColl.remove(key);
                    msgBox.append(key+":removed!");
                    }
                }
            
        }catch(Exception e){}
    }
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 0));

        msgBox.setEditable(false);
        msgBox.setColumns(20);
        msgBox.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1.setText("SERVER STATUS");

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel2.setText("......................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgBox;
    // End of variables declaration//GEN-END:variables
}
