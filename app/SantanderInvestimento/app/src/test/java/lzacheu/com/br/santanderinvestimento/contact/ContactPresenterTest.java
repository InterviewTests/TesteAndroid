package lzacheu.com.br.santanderinvestimento.contact;

import android.support.design.widget.TextInputLayout;
import android.view.View;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import lzacheu.com.br.santanderinvestimento.data.ContactDataSource;
import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.model.contact.InputField;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by luiszacheu on 6/21/18.
 */

public class ContactPresenterTest {

    private static List<InputField> INPUT_FIELDS = new ArrayList<>();
    private static List<InputField> INPUT_FIELDS_EMPTY = new ArrayList<>(0);

    private static List<View> VIEWS = new ArrayList<>(0);


    private ContactContract.Presenter contactPresenter;

    @Mock
    private ContactContract.View contactView;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private TextInputLayout textInputLayout;

    @Captor
    private ArgumentCaptor<ContactDataSource.LoadCellsCallback> loadCellsCallback;

    @Before
    public void setUpContactPresenter() {
//        Inicização dos Mocks
        MockitoAnnotations.initMocks(this);
        contactPresenter = new ContactPresenter(contactRepository, contactView);
        INPUT_FIELDS.add(new InputField(1, 2, "Nome", null, false, 60, null, true ));
        INPUT_FIELDS.add(new InputField(2, 2, "E-mai", null, false, 60, null, true ));
    }

    @Test
    public void fetchFields_FromRepositoryAndRenderForm(){
        contactPresenter.loadFields();

        //A callback é capturadan atraves do ArgumentCaptor e adicionamos a lista de inputFields mockada
        verify(contactRepository).getCells(loadCellsCallback.capture());
        loadCellsCallback.getValue().onCellsLoaded(INPUT_FIELDS);

        //Verifica se os campos foram para a chamada da view.
        verify(contactView).renderForm(INPUT_FIELDS);
    }

    @Test
    public void clickSendContact(){
        //Dado tap no button enviar, verificamos se a tela de mensagem é chamda
        contactPresenter.sendMessage();
        verify(contactView).showSendMessageView();
    }

    @Test
    public void validateFieldsIsEmpty(){
        //Dado uma lista de Views vazia, o metodo showErros nunca será chamado, pois não há campos
        //na tela
        Assert.assertFalse(contactPresenter.validFields(VIEWS));
        contactView.setPresenter(contactPresenter);
        verify(contactView, never()).showErrorsMessage(textInputLayout);
    }



}
