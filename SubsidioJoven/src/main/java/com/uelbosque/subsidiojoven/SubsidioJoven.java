/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.uelbosque.subsidiojoven;

import com.uelbosque.subsidiojoven.formulario.MainFrame;
import com.uelbosque.subsidiojoven.modelo.FuzzyController;
import com.uelbosque.subsidiojoven.modelo.ParametrosModelo;
import com.uelbosque.subsidiojoven.modelo.Probabilidad;

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
      
      FuzzyController controller = new FuzzyController();
      controller.cargarModelo();
      ParametrosModelo params = new ParametrosModelo("20", "2500000", "650", "130000000");
      Probabilidad resultado = controller.ejecutarModelo(params);
      System.out.println(resultado);
    }
}
