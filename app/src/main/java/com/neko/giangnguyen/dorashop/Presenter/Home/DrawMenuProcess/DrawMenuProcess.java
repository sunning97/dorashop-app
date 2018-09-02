package com.neko.giangnguyen.dorashop.Presenter.Home.DrawMenuProcess;

import android.util.Log;

import com.neko.giangnguyen.dorashop.ConnectAPI.DownloadJson;
import com.neko.giangnguyen.dorashop.Model.MenuJsonDataProcess.MenuJsonProcess;
import com.neko.giangnguyen.dorashop.Model.ObjectClass.Category;
import com.neko.giangnguyen.dorashop.View.Home.IShowMenuProcess;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DrawMenuProcess implements IDrawMenuProcess{
    private IShowMenuProcess iShowMenu;
    private MenuJsonProcess menuJsonProcess;
    private List<Category> list;


    public DrawMenuProcess(IShowMenuProcess iShowMenu){
        this.iShowMenu = iShowMenu;
    }

    @Override
    public void getDataMenu() {
        String dataJson = "";

        DownloadJson downloadJson = new DownloadJson("http://192.168.1.101:8000/api/categories");
        downloadJson.execute();

        try {
            dataJson = downloadJson.get();
            this.menuJsonProcess = new MenuJsonProcess();
            this.list = this.menuJsonProcess.parserJsonParent(dataJson);
            this.iShowMenu.showMenu(this.list);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
