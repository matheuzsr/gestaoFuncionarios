package gestaofuncionarios.presenter.funcionariopresenter;

import gestaofuncionarios.dados.dao.FuncionarioDAO;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.funcionariopresenter.state.FuncionarioPresenterState;
import gestaofuncionarios.presenter.funcionariopresenter.state.FuncionarioVisualizacaoState;
import gestaofuncionarios.presenter.funcionariopresenter.state.FuncionariosAddState;
import gestaofuncionarios.view.FuncionarioView;
import java.text.DecimalFormat;

public final class FuncionarioPresenter {

    private FuncionarioPresenterState estado;
    private final Funcionario funcionario;
    private final FuncionarioView view;
    private final FuncionarioDAO dao;
    private final DecimalFormat format;

    public FuncionarioPresenter(FuncionarioDAO dao, Funcionario funcionario) {
        this.dao = dao;
        this.funcionario = funcionario;
        this.view = new FuncionarioView();
        this.format = new DecimalFormat("0.00");

        if (funcionario == null) {
            this.setEstado(new FuncionariosAddState(this));
        } else {
            this.setEstado(new FuncionarioVisualizacaoState(this));
        }
    }

    public FuncionarioPresenterState getEstado() {
        return estado;
    }

    public void setEstado(FuncionarioPresenterState estado) {
        this.estado = estado;
    }

    public FuncionarioView getView() {
        return view;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public DecimalFormat getFormat() {
        return format;
    }

    public FuncionarioDAO getDao() {
        return dao;
    }

}
