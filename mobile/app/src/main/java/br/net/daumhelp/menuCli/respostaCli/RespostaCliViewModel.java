package br.net.daumhelp.menuCli.respostaCli;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RespostaCliViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RespostaCliViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

}
