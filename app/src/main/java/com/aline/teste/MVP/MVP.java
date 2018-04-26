package com.aline.teste.MVP;


import com.aline.teste.Models.Cells;
import com.aline.teste.Models.Screen;

import java.util.List;

public interface MVP {

     interface ModelCont{
        void callRetrofitContato();
    }

     interface PresenterCont{
        void callNetworkContato();

        void updateCells(List<Cells> list);
    }

     interface ModelFund{
        void callRetrofitFund();

    }

     interface PresenterFund{
        void callNetworkFund();
        void updateFund(Screen screenList);


    }
}
