package com.uelbosque.subsidiojoven.modelo;

import java.io.File;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.LinguisticTerm;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyController {
    private FIS modeloActual;

    public void cargarModelo() {
        String fileName = "SubsidioJoven.fcl";
        
        File modelFile = new File(fileName);
        FIS fis = FIS.load(modelFile.getAbsolutePath(),true);

        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Show 
        // JFuzzyChart.get().chart(fis);
        modeloActual = fis;
    }

    public Probabilidad ejecutarModelo(ParametrosModelo params) {
        if (params.esValido()) {
            System.out.println(params);
            modeloActual.setVariable("ingresos", params.getIngresos());
            modeloActual.setVariable("puntaje_crediticio", params.getPuntajeCrediticio());
            modeloActual.setVariable("valor_inmueble", params.getValorInmueble());

            modeloActual.evaluate();

            // FunctionBlock block = modeloActual.getFunctionBlock("subsidioJoven");
            Variable outVar = modeloActual.getVariable("favorabilidad");

            // JFuzzyChart.get().chart(outVar, outVar.getDefuzzifier(), true);

            double outValue = outVar.getValue();

            // Show each rule (and degree of support)
            // for( Rule r : block.getFuzzyRuleBlock("asignacion").getRules() )
            //     System.out.println(r);

            return outValue >= 0.6 ? Probabilidad.Alta 
                : (outValue >= 0.4 ? Probabilidad.Media : Probabilidad.Baja);
        }
        return Probabilidad.Ninguna;
    }
}
