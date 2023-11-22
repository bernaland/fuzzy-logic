/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.uelbosque.subsidiojoven;

import com.uelbosque.subsidiojoven.formulario.MainFrame;

/**
 *
 * @author helen
 */
public class SubsidioJoven {

    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new MainFrame().setVisible(true);
         }
      });
    }
}
