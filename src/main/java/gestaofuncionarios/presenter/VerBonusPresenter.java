package gestaofuncionarios.presenter;

import gestaofuncionarios.view.VerBonusView;

public class VerBonusPresenter {
    
    private VerBonusView view;

    public VerBonusPresenter() {
       inicializa();
    }
    
     public void inicializa() {
        this.view = new VerBonusView();
        this.view.setVisible(true);
    }

    public VerBonusView getView() {
        return view;
    }

    public void setView(VerBonusView view) {
        this.view = view;
    }

}
