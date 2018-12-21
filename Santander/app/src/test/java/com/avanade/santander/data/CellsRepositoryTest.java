package com.avanade.santander.data;

import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.data.local.CellsLocalDataSource;
import com.avanade.santander.data.remote.CellsRemoteDataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

public class CellsRepositoryTest {


    private Formulario FORMULARIO;

    private CellsRepository mCellsRepository;

    @Mock
    private CellsRemoteDataSource mCellsRemoteDataSource;

    @Mock
    private CellsLocalDataSource mCellsLocalDataSource;

    @Mock
    private CellsDataSource.LoadCellsCallback mLoadCellsCallback;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<CellsDataSource.LoadCellsCallback> mFormularioRemoteCallbackCaptor;
    @Captor
    private ArgumentCaptor<CellsDataSource.LoadCellsCallback> mFormularioLocalCallbackCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mCellsRepository = CellsRepository.getInstance(
                mCellsRemoteDataSource, mCellsLocalDataSource);
    }

    @Test
    public void testGetInstance() throws Exception {
        CellsRepository result = CellsRepository.getInstance(mCellsRemoteDataSource, mCellsLocalDataSource);
        Assert.assertEquals(CellsRepository.class, result.getClass());
    }

    @Test
    public void testGetCells() throws Exception {

        // When tasks are requested from repository
        mCellsRepository.getFormulario(mLoadCellsCallback); // First call to API

        // Use the Mockito Captor to capture the callback
        verify(mCellsRemoteDataSource).getFormulario(mFormularioRemoteCallbackCaptor.capture());

        // Local data source doesn't have data yet
        mFormularioRemoteCallbackCaptor.getValue().onDataNotAvailable();


        // Verify the remote data source is queried
        verify(mCellsLocalDataSource).getFormulario(mFormularioLocalCallbackCaptor.capture());

        // Trigger callback so tasks are cached
        mFormularioLocalCallbackCaptor.getValue().onCellsLoaded(FORMULARIO);

        mCellsRepository.getFormulario(mLoadCellsCallback); // Second call to API

        // Then tasks are loaded from the local data source
        verify(mCellsLocalDataSource).getFormulario(any(CellsDataSource.LoadCellsCallback.class));
        
    }

}