package com.github.lwhite1.tablesaw.api.ml.regression;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.util.DoubleArrays;
import com.google.common.base.Strings;
import smile.regression.OLS;

/**
 *
 */
public class LeastSquares {

    private final OLS model;
    private final double[][] explanatoryVariables;
    private final int explanatoryVariableCount;
    private final double[] responseVarArray;
    private final String[] explanatoryVariableNames;


    public LeastSquares(DoubleColumn responseVariable, DoubleColumn... explanatoryVars) {
        this.explanatoryVariables = DoubleArrays.to2dArray(explanatoryVars);

        this.responseVarArray = responseVariable.toDoubleArray();
        this.model = new OLS(explanatoryVariables, responseVarArray);
        this.explanatoryVariableCount = explanatoryVars.length;
        this.explanatoryVariableNames = new String[explanatoryVariableCount];

        for (int i = 0; i < explanatoryVariableCount; i++) {
            explanatoryVariableNames[i] = explanatoryVars[i].name();
        }
    }

    public static LeastSquares train(DoubleColumn responseVar, DoubleColumn... explanatoryVars) {
        return new LeastSquares(responseVar, explanatoryVars);
    }

    @Override
    public String toString() {
        String result = model.toString();
        result = result.replace("Intercept", "(Intercept)");

        // TODO(lwhite): This hack needed because Smile doesn't name the vars in it's output; we do, by string
        // replacement.
        int maxNameLength = "(intercept)".length() - 1;
        for (int i = 0; i < explanatoryVariableCount; i++) {
            String replacement = explanatoryVariableNames[i];
            if (replacement.length() >= maxNameLength) {
                replacement = replacement.substring(0, maxNameLength);
            } else {
                replacement = Strings.padEnd(replacement, maxNameLength, ' ');
            }
            result = result.replaceFirst("Var " + (i + 1) + '\t', replacement);
        }
        return result;
    }

    public double[] residuals() {
        return model.residuals();
    }

    public double[] fitted() {
        double[] fitted = new double[explanatoryVariables.length];
        for (int i = 0; i < explanatoryVariables.length; i++) {
            double[] input = explanatoryVariables[i];
            fitted[i] = predict(input);
        }
        return fitted;
    }

    public double adjustedRSquared() {
        return model.adjustedRSquared();
    }

    public double df() {
        return model.df();
    }

    public double error() {
        return model.error();
    }

    public double ftest() {
        return model.ftest();
    }

    public double pValue() {
        return model.pvalue();
    }

    public double intercept() {
        return model.intercept();
    }

    public double RSquared() {
        return model.RSquared();
    }

    public double RSS() {
        return model.RSS();
    }

    public double[][] ttest() {
        return model.ttest();
    }

    public double predict(double[] x) {
        return model.predict(x);
    }

    public double[] coefficients() {
        return model.coefficients();
    }

    public double[] actuals() {
        return responseVarArray;
    }
}
