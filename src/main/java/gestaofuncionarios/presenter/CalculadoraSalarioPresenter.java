package gestaofuncionarios.presenter;

import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.model.HistoricoBonus;
import gestaofuncionarios.view.CalculadoraSalarioView;

public class CalculadoraSalarioPresenter {
  private CalculadoraSalarioView view;
  private List<HistoricoBonus> historicoBonusList =  new ArrayList<>();

  public CalculadoraSalarioPresenter() {
    this.view = new CalculadoraSalarioView();
  }

  public CalculadoraSalarioView getView() {
    return this.view;
  }
}
