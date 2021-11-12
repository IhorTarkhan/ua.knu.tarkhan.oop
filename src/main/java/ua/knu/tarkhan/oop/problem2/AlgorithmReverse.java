package ua.knu.tarkhan.oop.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmReverse extends Thread {
    private final List<Double> _a;
    private final List<Double> _b;
    private final List<Double> _c;
    private final List<Double> _f;

    private final List<Double> _alpha;
    private final List<Double> _beta;

    private final List<Double> _x;
    int start;
    int finish;

    public AlgorithmReverse(List<Double> a, List<Double> b, List<Double> c,
                            List<Double> f, List<Double> res, int start, int finish) {
        _a = a;
        _b = b;
        _c = c;
        _f = f;
        _x = res;
        _alpha = new ArrayList<>(Collections.nCopies(_b.size() - 1, 0.0));
        _beta = new ArrayList<>(Collections.nCopies(_b.size() - 1, 0.0));
        this.start = start;
        this.finish = finish;
    }

    private void calculateCoefficients() {
        _alpha.add(_a.get(_a.size() - 1) * (-1) / _c.get(_c.size() - 1));
        _beta.add(_f.get(_f.size() - 1) / _c.get(_c.size() - 1));
        for (int i = _b.size() - 1; i > 0; i--) {
            _alpha.set(i - 1, (-1) * _a.get(i - 1) / (_b.get(i) * _alpha.get(i) + _c.get(i)));
            _beta.set(i - 1, (_f.get(i) - _b.get(i) * _beta.get(i)) / (_b.get(i) * _alpha.get(i) + _c.get(i)));
        }
    }

    public void run() {
        calculateCoefficients();
        Double temp1 = (_f.get(0) - _b.get(0) * _beta.get(0));
        Double temp2 = (_b.get(0) * _alpha.get(0) + _c.get(0));
        _x.set(start, temp1 / temp2);
        for (int i = start + 1; i < finish; i++) {
            _x.set(i, (_x.get(i - 1) * _alpha.get(i - 1)) + _beta.get(i - 1));
        }
    }

    public List<Double> getResult() {
        return _x;
    }
}





