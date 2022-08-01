package Login;

import MODELO.Base_De_Datos;
import CONTROLADOR.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TABLA extends javax.swing.JFrame {

    Base_De_Datos conexion = new Base_De_Datos();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel ContenidoTablaEmpleados;
    DefaultTableModel contenidoTablaDepartamentos;
    ComboBoxModel enumDepartamento;
    ComboBoxModel enumZona;
    ComboBoxModel enumTipoCalle;

    /**
     * ESTO ES MUY DIFICIL :[
     */
    public TABLA() {

        enumDepartamento = new DefaultComboBoxModel(EnumDepartamento.values());
        enumZona = new DefaultComboBoxModel(EnumZona.values());
        enumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
        initComponents();
        ListaDeEmpleados();
        ListaDeDepartamentos();
    }

    private void ListaDeDepartamentos() {

        String query = "SELECT nombreSucursal, nombreDepartamento CONCAT ('Zona ', zona, '. ', tipoCalle, ' ', numero1 ' No ', numero2, ' - ', numero3) AS direccioN FROM direccion INNER JOIN sucursal ON idDireccion = FK_idDireccion ORDER BY nombreDepartamento;";
        try {
            cn = conexion.getCConnection();
            st = cn.createStatement();
            rs = st.executeQuery(query);
            Object[] departamentos = new Object[3];
            contenidoTablaDepartamentos = (DefaultTableModel) tblDepartamentos.getModel();
            while (rs.next()) {
                departamentos[0] = rs.getString("nombreSucursal");
                departamentos[1] = rs.getString("nombreDepartamento");
                departamentos[2] = rs.getString("direccion");
                contenidoTablaDepartamentos.addRow(departamentos);
                System.out.println("SUCURSAL: " + departamentos[0] + "DEPARTAMENTO :" + departamentos[1]);
                tblDepartamentos.setModel(contenidoTablaDepartamentos);
            }
        } catch (SQLException e) {

            System.out.println("ERROR");

        }
    }

    private void ListaDeEmpleados() {

        String filtroBusqueda = txtSearch.getText();

        if (filtroBusqueda.isEmpty()) {

            String queryConsulta = "SELECT nombreSucursal, nombreDepartamento CONCAT ('Zona ', zona, '. ', tipoCalle, ' ', numero1 ' No ', numero2, ' - ', numero3) AS direccioN FROM direccion INNER JOIN sucursal WHERE idDireccion = FK_idDireccion AND nombreDepartamento LIKE '%%' ORDER BY nombreDepartamento;";
            try {
                cn = conexion.getCConnection();
                st = cn.createStatement();
                rs = st.executeQuery(queryConsulta);
                Object[] empleados = new Object[6];
                ContenidoTablaEmpleados = (DefaultTableModel) tblEmpledos.getModel();
                while (rs.next()) {
                    empleados[0] = rs.getString("nombreEmp");
                    empleados[1] = rs.getString("apellidos");
                    empleados[2] = rs.getString("tipoDocumento");
                    empleados[3] = rs.getString("documento");
                    empleados[4] = rs.getString("correo");
                    //int idSucursal = rs.getInt("FK_idSucursal"); 
                    //String nombreSucursal = "SELECT nombreSucursal FROM sucursal WHERE idSucursal = "+idSucursal+";";
                    empleados[5] = rs.getString("nombreSucursal");
                    ContenidoTablaEmpleados.addRow(empleados);
                    System.out.println("id : " + empleados[0] + ", nombre: " + empleados[1] + " " + empleados[2]
                            + ", documento: " + empleados[3] + " " + empleados[4] + ", correo: "
                            + empleados[5]);
                    tblEmpledos.setModel(ContenidoTablaEmpleados);

                }

            } catch (SQLException e) {
                System.out.println("ERROR");
            }

        } else {

            String queryConsulta = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM empleados INNER JOIN sucursal WHERE empleados.FK_idSucursal = sucursal.idSucursal AND nombreEmp LIKE '%" + filtroBusqueda + "%' OR apellidos LIKE '%" + filtroBusqueda + "%'";
            try {
                cn = conexion.getCConnection();
                st = cn.createStatement();
                rs = st.executeQuery(queryConsulta);
                Object[] empleados = new Object[6];
                ContenidoTablaEmpleados = (DefaultTableModel) tblEmpledos.getModel();
                while (rs.next()) {
                    empleados[0] = rs.getString("nombreEmp");
                    empleados[1] = rs.getString("apellidos");
                    empleados[2] = rs.getString("tipoDocumento");
                    empleados[3] = rs.getString("documento");
                    empleados[4] = rs.getString("correo");
                    empleados[5] = rs.getString("nombreSucursal");
                    ContenidoTablaEmpleados.addRow(empleados);
                    System.out.println("id : " + empleados[0] + ", nombre: " + empleados[1] + " " + empleados[2]
                            + ", documento: " + empleados[3] + " " + empleados[4] + ", correo: "
                            + empleados[5]);
                    tblEmpledos.setModel(ContenidoTablaEmpleados);

                }

            } catch (SQLException e) {
                System.out.println("ERROR");
            }else{
                    String query = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM empleados INNER JOIN sucursal WHERE empleados.FK_idSucursal = sucursal.idSucursal AND nombreEmp LIKE '%" + filtroBusqueda + "%' ORDER BY nombreDepartamento;";
                    System.out.println(query);
                    }
        }
    }

    private void BorrarDatosTabla() {

        for (int i = 0; i < tblEmpledos.getRowCount(); i++) {

            ContenidoTablaEmpleados.removeRow(i);

            i -= 1;

        }
    }

    private void BorrarDatosTablaDepartamentos() {

        for (int i = 0; i < tblEmpledos.getRowCount(); i++) {

            contenidoTablaDepartamentos.removeRow(i);

            i -= 1;

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpledos = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbZona = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbCalle = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtNumero1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNumero2 = new javax.swing.JTextField();
        txtNumero3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbDepartamento = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        txtSearch1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setBackground(new java.awt.Color(58, 100, 174));
        jButton1.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/retrato.png"))); // NOI18N
        jButton1.setText("AGREGAR EMPLEADO");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblEmpledos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "APELLIDO(S)", "TIPO DOCUMENTO", "DOCUMENTO", "CORREO", "SUCURSAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpledos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpledosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpledos);

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BUSCAR");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jButton1)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("EMPLEADOS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mision.png"))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ZONA:");

        cbZona.setBackground(new java.awt.Color(255, 255, 255));
        cbZona.setModel(enumZona);
        cbZona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("TIPO CALLE:");

        cbCalle.setBackground(new java.awt.Color(255, 255, 255));
        cbCalle.setModel(enumTipoCalle);
        cbCalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("DEPARTAMENTO:");

        txtNumero1.setBackground(new java.awt.Color(255, 255, 255));
        txtNumero1.setForeground(new java.awt.Color(0, 0, 0));
        txtNumero1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("#");

        txtNumero2.setBackground(new java.awt.Color(255, 255, 255));
        txtNumero2.setForeground(new java.awt.Color(0, 0, 0));

        txtNumero3.setBackground(new java.awt.Color(255, 255, 255));
        txtNumero3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("-");

        cbDepartamento.setBackground(new java.awt.Color(255, 255, 255));
        cbDepartamento.setModel(enumDepartamento);
        cbDepartamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnGuardar.setBackground(new java.awt.Color(239, 47, 98));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtSearch1.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("BUSCAR");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tienda-alt.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblDepartamentos.setBackground(new java.awt.Color(255, 255, 255));
        tblDepartamentos.setForeground(new java.awt.Color(0, 0, 0));
        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SUCURSAL", "DEPARTAMENTO", "DIRECCION"
            }
        ));
        tblDepartamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepartamentosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDepartamentos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar))
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar)
                    .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("SUCURSALES", jPanel1);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Formulario_Empleado G1 = new Formulario_Empleado(this, true);
        G1.setVisible(true);
        BorrarDatosTabla();
        ListaDeEmpleados();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        //NO PROGRAMADO
    }//GEN-LAST:event_jButton1MouseClicked

    private void tblEmpledosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpledosMouseClicked
        int row = tblEmpledos.getSelectedRow();
        System.out.println("FILA SELECCIONADA" + row);
        if (row < 0) {
            JOptionPane.showInputDialog(this, "SELECCIONE UN EMPLEADO", "", JOptionPane.WARNING_MESSAGE);
        } else {

            //int id = Integer.parseInt(tblEmpledos.getValueAt(row, 0).toString());
            String nombreEmp = tblEmpledos.getValueAt(row, 0).toString();
            String apellido = tblEmpledos.getValueAt(row, 1).toString();
            String TipoDeDocumento = tblEmpledos.getValueAt(row, 2).toString();
            String Documento = tblEmpledos.getValueAt(row, 3).toString();
            String Correo = tblEmpledos.getValueAt(row, 4).toString();
            String Sucursal = tblEmpledos.getValueAt(row, 5).toString();
            String nombreSucursal = "SELECT nombreSucursal FROM sucursal WHERE idSucursal = " + Sucursal + ";";

            System.out.println(", EMPLEADO: " + nombreEmp + " " + apellido + ", TIPO DE DOCUMENTO: " + TipoDeDocumento + ", NUMERO: " + Documento + ", CORREO: " + Correo);
            ShowUserForm showUserForm = new ShowUserForm(this, true);
            showUserForm.recibeDatos(nombreEmp, apellido, TipoDeDocumento, Correo, nombreSucursal);
            showUserForm.setVisible(true);

            BorrarDatosTabla();
            ListaDeEmpleados();

        }

    }//GEN-LAST:event_tblEmpledosMouseClicked


    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        BorrarDatosTabla();
        ListaDeEmpleados();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        //String nombreSucursal = txtSucursal.getText();
        String departamentoOption = cbDepartamento.getSelectedItem().toString();
        String zonaOption = cbZona.getSelectedItem().toString();
        String tipoCalleOption = cbCalle.getSelectedItem().toString();
        String numero1 = txtNumero1.getText();
        String numero2 = txtNumero2.getText();
        String numero3 = txtNumero3.getText();
        System.out.println("DEPARTAMENTO: " + departamentoOption + ", ZONA: " + zonaOption + ", TIPO DE CALLE: " + tipoCalleOption + ", NUMERO: " + numero1 + " - " + numero2 + " " + numero3);

        //String zonaOptionT = "";\\
        //String tipoCalleOptionT = "";\\
        String queryMunicipio = "INSERT INTO `municipio`(`nombreMunicipio`) VALUES ('" + departamentoOption + "')";
        String queryDireccion = "INSERT INTO `direccion`(`zona`, `tipoCalle`, `numero1`, `numero2`, `numero3`, `FK_idMunicipio`) VALUES ('" + zonaOption + "','" + tipoCalleOption + "','" + numero1 + "','" + numero2 + "','" + numero3 + "','" + departamentoOption + "');";
        System.out.println(queryDireccion);
        System.out.println(queryMunicipio);

        try {
            cn = conexion.getCConnection();
            st = cn.createStatement();
            st.executeUpdate(queryDireccion);
            st.executeUpdate(queryMunicipio);
            String queryIdDireccion = "SELECT idDireccion FROM 'direccion' WHERE nombreDepartamento = '" + departamentoOption + "' AND zona = '" + zonaOption + "' AND tipoCalle = '" + tipoCalleOption + "' AND numero1 = '" + numero1 + "' AND numero2 = '" + numero2 + "' AND numero3 = '" + numero3 + "';";
            try {
                rs = st.executeQuery(queryIdDireccion);
                SucursalForm sucursalForm = new SucursalForm(this, true);
                sucursalForm.setVisible(true);
                while (rs.next()) {
                    int idDireccion = rs.getInt("idDireccion");
                    sucursalForm.recibeIdDireccion(idDireccion);
                    System.out.println("ENVIA: " + idDireccion);

                }
                BorrarDatosTablaDepartamentos();
                ListaDeDepartamentos();

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "NO SE PUDO CREAR EL DEPARTAMENTO", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        
        this.BorrarDatosTablaDepartamentos();
        this.ListaDeDepartamentos();
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row = tblDepartamentos.getSelectedRow();
        System.out.println(row);
        String sucursal = tblDepartamentos.getValueAt(row, 1).toString();
        String querySucursal = "SELECT idSucursal From 'sucursal' WHERE nombreSucursal = '" + sucursal + "';";
        try {
            cn = conexion.getCConnection();
            st = cn.createStatement();
            st.executeUpdate(querySucursal);
            while (rs.next()) {
                int idSucursal = rs.getInt("idSucursal");
                EmpleadoForm A1 = new EmpleadoForm(this, true);
                A1.setVisible(true);
                A1.recibeIdSucursal(idSucursal);
            }
        } catch (SQLException e) {

            System.out.println(e);

        }
        System.out.println(querySucursal);
        EmpleadoForm A1 = new EmpleadoForm(this, true);
        A1.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblDepartamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepartamentosMouseClicked
        int row = tblDepartamentos.getSelectedRow();
        if (row > -1){
        String sucursal = tblDepartamentos.getValueAt(row, 0).toString();
        String departamentos = tblDepartamentos.getValueAt(row, 1).toString();
        String query = "SELECT idDireccion FROM 'direccion' INNER JOIN sucursal WHERE direccion.idDireccion = sucursal.FK_idDireccion AND sucursal.nombreSucursal '"+sucursal+"';";
        System.out.println(query);
        }
            try {
            cn = conexion.getCConnection();
            st = cn.createStatement();
            st.executeQuery(query);
            while (rs.next()){
            int idDireccion = rs.getInt("idDireccion");
            System.out.println("id CAPTURADO: " + idDireccion);
            String queryDireccion = "SELECT zona, nombreDepartamento, tipoCalle, numero1, numero2, numero3 FROM 'direccion' WHERE idDireccion = "+idDireccion+";";}
            try{
            rs = st.executeQuery(queryDireccion);
            while(rs.next()){
            String zona = rs.getString("zona");
            String TipoCalle = rs.getString("tipoCalle");
            String Numero1 = rs.getString("numero1");
            String Numero2 = rs.getString("numero2");
            String Numero3 = rs.getString("numero3");
            GestionarSucursalesForm A1 = new GestionarSucursalesForm(this, true);
            A1.setVisible(true);
            GestionarSucursalesForm.recibirDatosSucursal(idDireccion, Sucursal, Departamento, zona, Numero1, Numero2, Numero3);
            this.BorrarDatosTablaDepartamentos();
            this.ListaDeDepartamentos();
            }
            }catch(SQLException e){
                System.out.println(e);
            
            }
            }catch(SQLException e){
                System.out.println(e);
            }
    }//GEN-LAST:event_tblDepartamentosMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TABLA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbCalle;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblEmpledos;
    private javax.swing.JTextField txtNumero1;
    private javax.swing.JTextField txtNumero2;
    private javax.swing.JTextField txtNumero3;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables

}
