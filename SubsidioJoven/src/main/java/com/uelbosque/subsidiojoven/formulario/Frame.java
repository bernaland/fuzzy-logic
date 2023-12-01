package com.uelbosque.subsidiojoven.formulario;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.uelbosque.subsidiojoven.modelo.FuzzyController;
import com.uelbosque.subsidiojoven.modelo.ParametrosModelo;
import com.uelbosque.subsidiojoven.modelo.Probabilidad;

public class Frame extends JFrame {
    private JTextField ageTextField;
    private JTextField salaryTextField;
    private JTextField scoreTextField;
    private JTextField priceTextField;
    private JCheckBox receivedHelpCheckBox;
    private JCheckBox hasPropertiesCheckBox;
    private JButton submitButton;
    private Font frameBoldFont = new Font("TimesNewRoman", Font.BOLD, 16);
    private Font regularFont = new Font("TimesNewRoman", Font.PLAIN, 16);

    public Frame() {
        super();
        
        this.initComponents();
    }

    private void initComponents() {
        this.setSize(600, 500);
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints frameConstraints = new GridBagConstraints();

        JPanel headerPanel = new JPanel();
        GridBagLayout headerLayout = new GridBagLayout();
        headerPanel.setLayout(headerLayout);

        GridBagConstraints headerConstraints = new GridBagConstraints();
        headerConstraints.anchor = GridBagConstraints.CENTER;
        headerConstraints.gridx = 1;
        headerConstraints.gridy = 0;

        JLabel titleLabel = new JLabel("Subsidio de Vivienda Joven", SwingConstants.CENTER);
        titleLabel.setFont(this.frameBoldFont);
        headerPanel.add(titleLabel, headerConstraints);

        frameConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frameConstraints.weighty = 0.6;
        this.add(headerPanel, frameConstraints);

        JPanel formPanel = buildFormPanel();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 1;
        frameConstraints.weighty = 1.0;
        frameConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(formPanel, frameConstraints);

        this.submitButton = new JButton("Validar");
        this.submitButton.setFont(this.frameBoldFont);
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 2;
        this.submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButton1ActionPerformed(evt);
            }
        });
        this.add(this.submitButton, frameConstraints);
    }

    private JPanel buildFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints formConstraints = new GridBagConstraints();

        JLabel ageLabel = new JLabel("Edad:");
        ageLabel.setFont(this.frameBoldFont);
        this.ageTextField = new JTextField();
        this.ageTextField.setColumns(15);
        this.ageTextField.setFont(this.regularFont);

        formConstraints.gridx = 0;
        formConstraints.gridy = 0;
        formConstraints.insets = new Insets(5, 5, 5, 3);
        formPanel.add(ageLabel, formConstraints);

        formConstraints.gridx = 1;
        formConstraints.gridy = 0;
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formConstraints.insets = new Insets(5, 2, 5, 5);
        formPanel.add(this.ageTextField, formConstraints);

        JLabel salaryLabel = new JLabel("Salario:");
        salaryLabel.setFont(this.frameBoldFont);
        this.salaryTextField = new JTextField(15);
        this.salaryTextField.setFont(this.regularFont);

        formConstraints.gridx = 2;
        formConstraints.gridy = 0;
        formPanel.add(salaryLabel, formConstraints);

        formConstraints.gridx = 3;
        formConstraints.gridy = 0;
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.salaryTextField, formConstraints);

        JLabel scoreLabel = new JLabel("Puntaje:");
        scoreLabel.setFont(this.frameBoldFont);
        this.scoreTextField = new JTextField(15);
        this.scoreTextField.setFont(this.regularFont);

        formConstraints.gridx = 0;
        formConstraints.gridy = 1;
        formPanel.add(scoreLabel, formConstraints);

        formConstraints.gridx = 1;
        formConstraints.gridy = 1;
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.scoreTextField, formConstraints);

        JLabel priceLabel = new JLabel("Precio:");
        priceLabel.setFont(this.frameBoldFont);
        this.priceTextField = new JTextField(15);
        this.priceTextField.setFont(this.regularFont);

        formConstraints.gridx = 2;
        formConstraints.gridy = 1;
        formPanel.add(priceLabel, formConstraints);

        formConstraints.gridx = 3;
        formConstraints.gridy = 1;
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.priceTextField, formConstraints);

        this.receivedHelpCheckBox = new JCheckBox("Ha recibido subsidio?");
        this.receivedHelpCheckBox.setFont(this.frameBoldFont);
        this.receivedHelpCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
        formConstraints.gridx = 0;
        formConstraints.gridy = 2;
        formConstraints.gridwidth = 2;
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.receivedHelpCheckBox, formConstraints);

        this.hasPropertiesCheckBox = new JCheckBox("Tiene otro inmueble?");
        this.hasPropertiesCheckBox.setFont(this.frameBoldFont);
        this.hasPropertiesCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
        formConstraints.gridx = 0;
        formConstraints.gridy = 3;
        formConstraints.gridwidth = 2;
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formConstraints.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.hasPropertiesCheckBox, formConstraints);

        return formPanel;
    }

    private void submitButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        ParametrosModelo params = new ParametrosModelo(
            this.ageTextField.getText(), 
            this.salaryTextField.getText(), 
            this.scoreTextField.getText(), 
            this.priceTextField.getText(),
            this.receivedHelpCheckBox.isSelected(),
            this.hasPropertiesCheckBox.isSelected());
        try {
            params.validarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }

        FuzzyController controller = new FuzzyController();
        controller.cargarModelo();
        Probabilidad resultado = controller.ejecutarModelo(params);
        String mensaje = resultado != Probabilidad.Ninguna ? 
            "Tiene una favorabilidad " +  resultado + "  de obtener el subsidio de vivienda joven" :
            "No tiene posibilidades de obtener el subsidio de vivienda joven";
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
