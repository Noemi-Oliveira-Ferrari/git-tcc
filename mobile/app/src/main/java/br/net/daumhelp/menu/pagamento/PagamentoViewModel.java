package br.net.daumhelp.menu.pagamento;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class PagamentoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PagamentoViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}