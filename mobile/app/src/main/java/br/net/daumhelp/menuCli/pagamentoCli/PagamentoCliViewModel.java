package br.net.daumhelp.menuCli.pagamentoCli;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class PagamentoCliViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PagamentoCliViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}