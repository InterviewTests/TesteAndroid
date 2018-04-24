package com.aline.teste.MVP;


import com.aline.teste.Models.Cells;
import com.aline.teste.Models.Screen;

import java.util.List;

public interface MVP {

    public interface ModelCont{
        void callRetrofitContato();
    }

    public interface PresenterCont{
        void callNetworkContato();
        void updateCells(List<Cells> list);
    }

    public interface ViewContato{

    }

    public  interface ModelFund{
        void callRetrofitFund();
    }

    public interface PresenterFund{
        void callNetworkFund();
        void updateFund(Screen screenList);

    }
}
