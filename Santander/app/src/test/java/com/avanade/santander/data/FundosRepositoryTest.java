package com.avanade.santander.data;

import com.avanade.santander.fundos.domain.model.Fundos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FundosRepositoryTest {


    private Fundos FUNDOS;

    private FundosRepository mFundosRepository;

    @Mock
    private FundosDataSource mFundosRemoteDataSource;

    @Mock
    private FundosDataSource.LoadFundosCallback mLoadFundosCallback;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<FundosDataSource.LoadFundosCallback> mFundosCallbackCaptor;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mFundosRepository = FundosRepository.getInstance(mFundosRemoteDataSource);
    }

    @Test
    public void testGetInstance() throws Exception {
        FundosRepository result = FundosRepository.getInstance(mFundosRemoteDataSource);
        Assert.assertEquals(FundosRepository.class, result.getClass());
    }

    @Test
    public void testGetFundos() throws Exception {
        // Given a setup Captor to capture callbacks
        // When fundos are requested from repository
        mFundosRepository.getFundos(mLoadFundosCallback); // First call to API

        // Verify the remote data source is queried
        verify(mFundosRemoteDataSource).getFundos(mFundosCallbackCaptor.capture());

        // Trigger callback so fundos are cached
        mFundosCallbackCaptor.getValue().onFundosLoaded(FUNDOS);

        // Then fundos were requested from Service API
        verify(mFundosRemoteDataSource).getFundos(any(FundosDataSource.LoadFundosCallback.class));
    }



}